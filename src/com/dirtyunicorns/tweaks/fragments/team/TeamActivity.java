/*
 * Copyright (C) 2020 The Dirty Unicorns Project
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

package com.dirtyunicorns.tweaks.fragments.team;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.settings.R;

import java.util.ArrayList;
import java.util.List;

public class TeamActivity extends Activity {

    private List<DevInfoAdapter> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_recyclerview);

        initTeam();

        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
    }
    private void initTeam(){
        RecyclerView mRecycleview = findViewById(R.id.listView);

        setTeamMember("Ritzz", getString(R.string.developer_title)
                + " / " + getString(R.string.maintainer_title), "riteshm321", "ritzz97", 
                R.drawable.ritzz);
       setTeamMember("Trishiraj", getString(R.string.core_developer_title)
                + " / " + getString(R.string.maintainer_title), "StarkDroid", "ZuijinR6",
                R.drawable.trishiraj);
        setTeamMember("Victor", getString(R.string.manager_title)
                + " / " + getString(R.string.maintainer_title), "merser2005", "victor10520",
                R.drawable.merser);
        setTeamMember("Samuel", getString(R.string.ui_developer_title)
                + " / " + getString(R.string.maintainer_title), "DeadmanxXD", "deadmanxxd",
                R.drawable.deadmanxxd);
        setTeamMember("Jughead", getString(R.string.manager_title), "jughead069", "jughead069",
                R.drawable.jughead);
       setTeamMember("Zeeshan", getString(R.string.core_developer_title)
                + " / " + getString(R.string.maintainer_title), "Zaviyar786", "NAHSEEZ", 
                R.drawable.zeeshan);
        setTeamMember("Ajay Bhojani AB", getString(R.string.graphics_title), "AjayBhojaniAB", "Ajay_Bhojani",
                R.drawable.ajay);
	setTeamMember("Ashwatthama", getString(R.string.maintainer_title), "sai4041412", "saisamy95", 
                R.drawable.sai);
        setTeamMember("Introdructor", getString(R.string.maintainer_title), "Introdructor", "Introdructor",
                R.drawable.Introdructor);
        setTeamMember("PKM774", getString(R.string.maintainer_title), "pkm774", "pkm774",
                R.drawable.pkm);
        setTeamMember("Sambit", getString(R.string.maintainer_title), "thepsambit", "thepsambit",
                R.drawable.sambit);
        setTeamMember("John Paul Patigas", getString(R.string.maintainer_title), "johnpaulpatigas", "johnpaulpatigas",
                R.drawable.johnpaulpatigas);
        setTeamMember("Kader Bava", getString(R.string.maintainer_title), "kaderbava", "Bava7325",
                R.drawable.kader);
        setTeamMember("Tipz Team", getString(R.string.maintainer_title), "TipzTeam", "TipzTeam1",
                R.drawable.tipz);
        setTeamMember("Sushmit", getString(R.string.maintainer_title), "sushmit1", "uchihasushmit",
                R.drawable.sushmit);
        setTeamMember("H3M3L", getString(R.string.maintainer_title), "H3M3L", "H3M3L",
                R.drawable.H3M3L);
        setTeamMember("Tamizh Arasan", getString(R.string.maintainer_title), "no1opensourcelover", "fosslover",
                R.drawable.fosslover);
        setTeamMember("RoXoR", getString(R.string.maintainer_title), "Roxor-007", "RoXoR07",
                R.drawable.roxor);
        setTeamMember("Sebastián Sariego Benítez", getString(R.string.maintainer_title), "segfault2k", "sewa2k",
                R.drawable.segfault);
        setTeamMember("Fahim Ahmad", getString(R.string.maintainer_title), "devil-black-786", "I_am_pronoob",
                R.drawable.noob);
        setTeamMember("TheTablaster", getString(R.string.maintainer_title), "Blaster4385", "V3NK4135H",
                R.drawable.tab);
        setTeamMember("Mohit Yadav", getString(R.string.maintainer_title), "YadavMohit19", "YadavMohit19",
                R.drawable.mohit);
        setTeamMember("Himanshu Tyagi", getString(R.string.maintainer_title), "ManshuTyagi", "ManshuTyagi",
                R.drawable.himanshu);
        setTeamMember("Chintu", getString(R.string.maintainer_title), "Jrchintu", "Jrchintu",
                R.drawable.chintu);
        setTeamMember("Marcelo", getString(R.string.maintainer_title), "YukkiZx", "yukki159",
                R.drawable.marcelo);
        setTeamMember("Manavjit Singh", getString(R.string.maintainer_title), "PeacE-Boi", "Kuruin",
                R.drawable.manav);
        setTeamMember("Sahil Khatkar", getString(R.string.moderator_title), "SahilKhatkar11", "SahilKhatkar11",
                R.drawable.sahil);

        ListAdapter mAdapter = new ListAdapter(mList);
        mRecycleview.setAdapter(mAdapter);
        mRecycleview.setLayoutManager(new LinearLayoutManager(this));
        mAdapter.notifyDataSetChanged();
    }

    private void setTeamMember(String devName, String devTitle,
                               String githubLink, String telegram, int devImage) {
        DevInfoAdapter adapter;

        adapter = new DevInfoAdapter();
        adapter.setImage(devImage);
        adapter.setDevName(devName);
        adapter.setDevTitle(devTitle);
        adapter.setGithubName(githubLink);
        adapter.setTelegramName(telegram);
        mList.add(adapter);
    }
}
