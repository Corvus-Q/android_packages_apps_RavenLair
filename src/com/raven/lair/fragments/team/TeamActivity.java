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

package com.raven.lair.fragments.team;

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
        setTeamMember("Jughead", getString(R.string.developer_title), "jughead069", "jughead069",
                R.drawable.jughead);
        setTeamMember("Samuel", getString(R.string.developer_title)
                + " / " + getString(R.string.maintainer_title), "DeadmanxXD", "deadmanxxd",
                R.drawable.deadmanxxd);
       setTeamMember("Zeeshan", getString(R.string.developer_title)
                + " / " + getString(R.string.maintainer_title), "HeartStealer786", "NAHSEEZ", 
                R.drawable.zeeshan);
       setTeamMember("Aashil", getString(R.string.developer_title), "aashil123", "tempyourdaddy",
                R.drawable.ashil);       
        setTeamMember("Ronald Santos", getString(R.string.maintainer_title), "ronald-b", "RonaldSt",
                R.drawable.ronald);
        setTeamMember("Shivam Kumar", getString(R.string.maintainer_title), "ShivamKumar2002", "ShivamKumar2002",
                R.drawable.shivam);
        setTeamMember("Azaharuddin", getString(R.string.maintainer_title), "aza786", "Azahar123",
                R.drawable.azahar);
        setTeamMember("Victor", getString(R.string.maintainer_title), "merser2005", "merser2005",
                R.drawable.merser);
        setTeamMember("ᴀꜱʜᴡᴀᴛᴛʜᴀᴍᴀ", getString(R.string.maintainer_title), "sai4041412", "saisamy95",
                R.drawable.sai);
        setTeamMember("Aditya", getString(R.string.maintainer_title), "meetaditya", "meetaditya",
                R.drawable.aditya);
        setTeamMember("PKM774", getString(R.string.maintainer_title), "pkm774", "pkm774",
                R.drawable.pkm);
        setTeamMember("Introdructor", getString(R.string.maintainer_title), "Introdructor", "Introdructor",
                R.drawable.Introdructor);

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
