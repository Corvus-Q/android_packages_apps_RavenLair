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
        setTeamMember("Saikiran", getString(R.string.developer_title)
                + " / " + getString(R.string.maintainer_title), "saikiran2001", "saikiran008",
                R.drawable.saikiran);
        setTeamMember("Samuel", getString(R.string.developer_title)
                + " / " + getString(R.string.maintainer_title), "DeadmanxXD", "deadmanxxd",
                R.drawable.deadmanxxd);
        setTeamMember("Taran", getString(R.string.developer_title)
                + " / " + getString(R.string.maintainer_title), "taranhora", "taran107",
                R.drawable.taran);
        setTeamMember("Xawlw", getString(R.string.developer_title), "xawlw", "xawlw", 
                R.drawable.xawlw);
        setTeamMember("Jughead", getString(R.string.developer_title), "jughead069", "jughead069",
                R.drawable.jughead);
        setTeamMember("Reignz", getString(R.string.developer_title), "PainKiller3", "Reignz3",
                R.drawable.reignz);
        setTeamMember("Astile Kuriakose", getString(R.string.maintainer_title), "Astile97", "Astile97",
                R.drawable.astile97);
        setTeamMember("Ronald Santos", getString(R.string.maintainer_title), "ronald-b", "RonaldSt",
                R.drawable.ronald);
        setTeamMember("Shivam Kumar", getString(R.string.maintainer_title), "ShivamKumar2002", "ShivamKumar2002",
                R.drawable.shivam);
        setTeamMember("Takeshiro", getString(R.string.maintainer_title), "Takeshiro04", "Takeshiro04",
                R.drawable.takeshiro);
        setTeamMember("Zeeshan", getString(R.string.maintainer_title), "HeartStealer786", "NAHSEEZ",
                R.drawable.zeeshan);
        setTeamMember("Mahmoud", getString(R.string.maintainer_title), "MahmoudK1000", "MahmoudK1000",
                R.drawable.mahmoud);
        setTeamMember("Azaharuddin", getString(R.string.maintainer_title), "aza786", "Azahar123",
                R.drawable.azahar);
        setTeamMember("Shekhar", getString(R.string.maintainer_title), "Flamefusion", "Flamefusion",
                R.drawable.shekhar);
        setTeamMember("Sujit", getString(R.string.maintainer_title), "kumarsujita6", "kumarsujita6",
                R.drawable.sujit);
        setTeamMember("Rashed", getString(R.string.maintainer_title), "rashedsahaji", "RashedSahaji",
                R.drawable.rashed);
        setTeamMember("Victor", getString(R.string.maintainer_title), "merser2005", "merser2005",
                R.drawable.merser);
        setTeamMember("ᴀꜱʜᴡᴀᴛᴛʜᴀᴍᴀ", getString(R.string.maintainer_title), "sai4041412", "saisamy95",
                R.drawable.sai);
        setTeamMember("Jullian", getString(R.string.maintainer_title), "jullian14", "jullian14",
                R.drawable.julian);
        setTeamMember("RDS", getString(R.string.maintainer_title), "raman07-dev", "RDS_07",
                R.drawable.rds);
        setTeamMember("Aditya", getString(R.string.maintainer_title), "meetaditya", "meetaditya",
                R.drawable.aditya);
        setTeamMember("HardRock", getString(R.string.maintainer_title), "rakeshraimca", "Hard_rock83",
                R.drawable.hardrock);
        setTeamMember("Aashil", getString(R.string.maintainer_title), "aashil123", "tempyourdaddy",
                R.drawable.ashil);
        setTeamMember("Khalakuzzaman", getString(R.string.maintainer_title), "Apon77", "Apon77",
                R.drawable.apon);
        setTeamMember("Jubayer", getString(R.string.maintainer_title), "jrhimel", "jubayerhimel",
                R.drawable.jubayer);
        setTeamMember("Coughy", getString(R.string.maintainer_title), "farcough2", "Coughy",
                R.drawable.coughy);
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
