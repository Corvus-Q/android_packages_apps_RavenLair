/*
 * Copyright (C) 2014-2016 The Dirty Unicorns Project
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

package com.raven.lair;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.ViewPager;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.FragmentPagerAdapter;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.internal.logging.nano.MetricsProto;

import com.raven.lair.fragments.Team;
import com.raven.lair.tabs.Lockscreen;
import com.raven.lair.tabs.Hardware;
import com.raven.lair.tabs.Statusbar;
import com.raven.lair.tabs.System;
import com.raven.lair.bottomnav.BubbleNavigationConstraintView;
import com.raven.lair.bottomnav.BubbleNavigationChangeListener;

public class RavenLair extends SettingsPreferenceFragment  {    
	private MenuItem mMenuItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        
        View view = inflater.inflate(R.layout.ravenlair, container, false);
        getActivity().setTitle(R.string.ravenlair_title);

        final BubbleNavigationConstraintView bubbleNavigationConstraintView =  (BubbleNavigationConstraintView) view.findViewById(R.id.bottom_navigation_view_constraint);
        final ViewPager viewPager = view.findViewById(R.id.viewpager);
        PagerAdapter mPagerAdapter = new PagerAdapter(getFragmentManager());
        viewPager.setAdapter(mPagerAdapter);

        bubbleNavigationConstraintView.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
			int id = view.getId();
				if (id == R.id.system) {
					viewPager.setCurrentItem(position, true);
				} else if (id == R.id.lockscreen) {
					viewPager.setCurrentItem(position, true);
				} else if (id == R.id.statusbar) {
					viewPager.setCurrentItem(position, true);
				} else if (id == R.id.hardware) {
					viewPager.setCurrentItem(position, true);
				}
				
			    }
	    
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                bubbleNavigationConstraintView.setCurrentActiveItem(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });

        setHasOptionsMenu(true);
        return view;
    }

    class PagerAdapter extends FragmentPagerAdapter {

        String titles[] = getTitles();
        private Fragment frags[] = new Fragment[titles.length];

        PagerAdapter(FragmentManager fm) {
            super(fm);
            frags[0] = new System();
            frags[1] = new Lockscreen();
            frags[2] = new Statusbar();
            frags[3] = new Hardware();
        }

        @Override
        public Fragment getItem(int position) {
            return frags[position];
        }

        @Override
        public int getCount() {
            return frags.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

    private String[] getTitles() {
        String titleString[];
        titleString = new String[]{
                getString(R.string.bottom_nav_system_title),
                getString(R.string.bottom_nav_lockscreen_title),
                getString(R.string.bottom_nav_statusbar_title),
                getString(R.string.bottom_nav_hardware_title)};

        return titleString;
    }

    @Override
    public int getMetricsCategory() {
        return MetricsProto.MetricsEvent.CORVUS;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.add(0, 0, 0, R.string.dialog_team_title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                final Team dialog = new Team();
                showDialog(this, dialog);
                return true;
            default:
                return false;
        }
    }

    private static void showDialog(Fragment context, DialogFragment dialog) {
        FragmentTransaction ft = context.getChildFragmentManager().beginTransaction();
        Fragment prev = context.getChildFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        dialog.show(ft, "dialog");
    }
 }
