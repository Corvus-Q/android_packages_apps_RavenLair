/*
 * Copyright (C) 2017-2019 The Dirty Unicorns Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dirtyunicorns.tweaks.fragments.system;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.SELinux;
import android.os.UserHandle;
import android.provider.SearchIndexableResource;
import android.provider.Settings;
import androidx.preference.*;
import android.os.UserHandle;
import android.os.SystemProperties;

import com.android.internal.logging.nano.MetricsProto;

import com.android.settings.R;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settingslib.search.SearchIndexable;
import android.util.Log;
import com.android.settings.Utils;
import android.text.TextUtils;

import com.dirtyunicorns.support.preferences.SystemSettingMasterSwitchPreference;

import com.dirtyunicorns.utils.SuTask;
import com.dirtyunicorns.utils.SuShell;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.HashSet;
import java.util.Arrays;

@SearchIndexable
public class Miscellaneous extends SettingsPreferenceFragment
        implements Preference.OnPreferenceChangeListener, Indexable {

    private static final String TAG = "Miscellaneous";

    private static final String GAMING_MODE_ENABLED = "gaming_mode_enabled";
    private static final String KEY_SCREEN_OFF_ANIMATION = "screen_off_animation";
    private static final String SCROLLINGCACHE_PREF = "pref_scrollingcache";
    private static final String SCROLLINGCACHE_PERSIST_PROP = "persist.sys.scrollingcache";
    private static final String SCROLLINGCACHE_DEFAULT = "2";
    private static final String PREF_KEY_CUTOUT = "cutout_settings";
    private static final String KEY_RINGTONE_FOCUS_MODE_V2 = "ringtone_focus_mode_v2";
    private static final String SCREEN_STATE_TOGGLES_ENABLE = "screen_state_toggles_enable_key";
    private static final String SELINUX_CATEGORY = "selinux";
    private static final String PREF_SELINUX_MODE = "selinux_mode";
    private static final String PREF_SELINUX_PERSISTENCE = "selinux_persistence";

    private SystemSettingMasterSwitchPreference mGamingMode;
    private SystemSettingMasterSwitchPreference mEnableScreenStateToggles;
    private ListPreference mScrollingCachePref;
    private ListPreference mScreenOffAnimation;
    private ListPreference mRingtoneFocusMode;
    private SwitchPreference mSelinuxMode;
    private SwitchPreference mSelinuxPersistence;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.miscellaneous);

        final PreferenceScreen prefScreen = getPreferenceScreen();
        final ContentResolver resolver = getActivity().getContentResolver();
        Resources res = getResources();

        mGamingMode = (SystemSettingMasterSwitchPreference) findPreference(GAMING_MODE_ENABLED);
        mGamingMode.setChecked((Settings.System.getInt(resolver,
                Settings.System.GAMING_MODE_ENABLED, 0) == 1));
        mGamingMode.setOnPreferenceChangeListener(this);

        mScreenOffAnimation = (ListPreference) findPreference(KEY_SCREEN_OFF_ANIMATION);
        int screenOffAnimation = Settings.System.getInt(getContentResolver(),
                Settings.System.SCREEN_OFF_ANIMATION, 0);
        mScreenOffAnimation.setValue(Integer.toString(screenOffAnimation));
        mScreenOffAnimation.setSummary(mScreenOffAnimation.getEntry());
        mScreenOffAnimation.setOnPreferenceChangeListener(this);

        mScrollingCachePref = (ListPreference) findPreference(SCROLLINGCACHE_PREF);
        mScrollingCachePref.setValue(SystemProperties.get(SCROLLINGCACHE_PERSIST_PROP,
                SystemProperties.get(SCROLLINGCACHE_PERSIST_PROP, SCROLLINGCACHE_DEFAULT)));
        mScrollingCachePref.setOnPreferenceChangeListener(this);

        Preference mCutoutPref = (Preference) findPreference(PREF_KEY_CUTOUT);
        if (!hasPhysicalDisplayCutout(getContext())) {
            prefScreen.removePreference(mCutoutPref);
        }

        mRingtoneFocusMode = (ListPreference) findPreference(KEY_RINGTONE_FOCUS_MODE_V2);
        if (!res.getBoolean(com.android.internal.R.bool.config_deviceRingtoneFocusMode)) {
            prefScreen.removePreference(mRingtoneFocusMode);
        }
 
        mEnableScreenStateToggles = (SystemSettingMasterSwitchPreference) findPreference(SCREEN_STATE_TOGGLES_ENABLE);
        int enabled = Settings.System.getIntForUser(getContentResolver(),
                Settings.System.START_SCREEN_STATE_SERVICE, 0, UserHandle.USER_CURRENT);
        mEnableScreenStateToggles.setChecked(enabled != 0);
        mEnableScreenStateToggles.setOnPreferenceChangeListener(this);

      // SELinux
      Preference selinuxCategory = findPreference(SELINUX_CATEGORY);
      mSelinuxMode = (SwitchPreference) findPreference(PREF_SELINUX_MODE);
      mSelinuxMode.setChecked(SELinux.isSELinuxEnforced());
      mSelinuxMode.setOnPreferenceChangeListener(this);

      mSelinuxPersistence =
          (SwitchPreference) findPreference(PREF_SELINUX_PERSISTENCE);
      mSelinuxPersistence.setOnPreferenceChangeListener(this);
      mSelinuxPersistence.setChecked(getContext()
          .getSharedPreferences("selinux_pref", Context.MODE_PRIVATE)
          .contains(PREF_SELINUX_MODE));
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        final ContentResolver resolver = getActivity().getContentResolver();
        if (preference == mGamingMode) {
            boolean value = (Boolean) newValue;
            Settings.System.putInt(resolver,
                    Settings.System.GAMING_MODE_ENABLED, value ? 1 : 0);
            return true;
        } else if (preference == mScreenOffAnimation) {
            int value = Integer.valueOf((String) newValue);
            int index = mScreenOffAnimation.findIndexOfValue((String) newValue);
            mScreenOffAnimation.setSummary(mScreenOffAnimation.getEntries()[index]);
            Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_OFF_ANIMATION, value);
            return true;
        } else if (preference == mScrollingCachePref) {
            if (newValue != null) {
                SystemProperties.set(SCROLLINGCACHE_PERSIST_PROP, (String) newValue);
            }
            return true;
        } else if (preference == mEnableScreenStateToggles) {
            boolean value = (Boolean) newValue;
            Settings.System.putIntForUser(getContentResolver(),
                    Settings.System.START_SCREEN_STATE_SERVICE, value ? 1 : 0, UserHandle.USER_CURRENT);
            Intent service = (new Intent())
                .setClassName("com.android.systemui", "com.android.systemui.du.screenstate.ScreenStateService");
            if (value) {
                getActivity().stopService(service);
                getActivity().startService(service);
            } else {
                getActivity().stopService(service);
            }
            return true;
        } else if (preference == mSelinuxMode) {
            boolean enabled = (Boolean) newValue;
            new SwitchSelinuxTask(getActivity()).execute(enabled);
            setSelinuxEnabled(enabled, mSelinuxPersistence.isChecked());
            return true;
        } else if (preference == mSelinuxPersistence) {
            setSelinuxEnabled(mSelinuxMode.isChecked(), (Boolean) newValue);
            return true;
        }
            return false;
    }

    private static boolean hasPhysicalDisplayCutout(Context context) {
        return context.getResources().getBoolean(
                com.android.internal.R.bool.config_physicalDisplayCutout);
    }

    @Override
    public int getMetricsCategory() {
        return MetricsProto.MetricsEvent.DIRTYTWEAKS;
    }

      private void setSelinuxEnabled(boolean status, boolean persistent) {
      SharedPreferences.Editor editor = getContext()
          .getSharedPreferences("selinux_pref", Context.MODE_PRIVATE).edit();
      if (persistent) {
        editor.putBoolean(PREF_SELINUX_MODE, status);
      } else {
        editor.remove(PREF_SELINUX_MODE);
      }
      editor.apply();
      mSelinuxMode.setChecked(status);
    }

    private class SwitchSelinuxTask extends SuTask<Boolean> {
      public SwitchSelinuxTask(Context context) {
        super(context);
      }
      @Override
      protected void sudoInBackground(Boolean... params) throws SuShell.SuDeniedException {
        if (params.length != 1) {
          Log.e(TAG, "SwitchSelinuxTask: invalid params count");
          return;
        }
        if (params[0]) {
          SuShell.runWithSuCheck("setenforce 1");
        } else {
          SuShell.runWithSuCheck("setenforce 0");
        }
      }

      @Override
      protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        if (!result) {
          // Did not work, so restore actual value
          setSelinuxEnabled(SELinux.isSELinuxEnforced(), mSelinuxPersistence.isChecked());
        }
      }
    }
    public static final SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER =
            new BaseSearchIndexProvider() {
                @Override
                public List<SearchIndexableResource> getXmlResourcesToIndex(Context context,
                        boolean enabled) {
                    ArrayList<SearchIndexableResource> result =
                            new ArrayList<SearchIndexableResource>();

                    SearchIndexableResource sir = new SearchIndexableResource(context);
                    sir.xmlResId = R.xml.miscellaneous;
                    result.add(sir);
                    return result;
                }

                @Override
                public List<String> getNonIndexableKeys(Context context) {
                    List<String> keys = super.getNonIndexableKeys(context);
                    final Resources res = context.getResources();
                    if (!res.getBoolean(com.android.internal.R.bool.config_deviceRingtoneFocusMode)) {
                        keys.add(KEY_RINGTONE_FOCUS_MODE_V2);
                    }
                    return keys;
        }
    };
}
