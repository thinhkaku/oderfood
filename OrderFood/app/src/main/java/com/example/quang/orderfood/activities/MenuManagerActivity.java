package com.example.quang.orderfood.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.quang.orderfood.R;
import com.github.nkzawa.emitter.Emitter;

import java.util.ArrayList;

import singleton.Singleton;

public class MenuManagerActivity extends AppCompatActivity implements View.OnClickListener {

    private final String CLIENT_SEND_REQUEST_ALL_GROUP = "CLIENT_SEND_REQUEST_ALL_GROUP";
    private final String CLIENT_SEND_REQUEST_ALL_FOOD = "CLIENT_SEND_REQUEST_ALL_FOOD";

    private final String SERVER_SEND_ALL_GROUP = "SERVER_SEND_ALL_GROUP";
    private final String SERVER_SEND_ALL_FOOD = "SERVER_SEND_ALL_FOOD";

    Emitter.Listener onAllGroup;
    Emitter.Listener onAllFood;
    {
        onAllGroup = new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                getAllGroup(args[0]);
            }
        };
        onAllFood = new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                getAllFood(args[0]);
            }
        };
    }

    private void getAllFood(Object arg) {

    }

    private void getAllGroup(Object arg) {

    }

    private Toolbar toolbar;
    private ImageButton btnBack;
    private Spinner spinner;
    private ListView listView;
    private ArrayList<String> arrGroup;
    private ArrayList<String> arrItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_manager);

        initSockets();
        findId();
        initViews();
        clickEvents();
    }

    private void initSockets() {
        Singleton.Instance().getmSocket().emit(CLIENT_SEND_REQUEST_ALL_GROUP,"123");
        Singleton.Instance().getmSocket().emit(CLIENT_SEND_REQUEST_ALL_FOOD,"123");
        Singleton.Instance().getmSocket().on(SERVER_SEND_ALL_GROUP,onAllGroup);
        Singleton.Instance().getmSocket().on(SERVER_SEND_ALL_FOOD,onAllFood);
    }

    private void clickEvents() {
        btnBack.setOnClickListener(this);
    }

    private void initViews() {
        setSupportActionBar(toolbar);
    }

    private void findId() {
        toolbar = findViewById(R.id.toolbarMenuManager);
        btnBack = findViewById(R.id.btnBackMenuManager);
        spinner = findViewById(R.id.spinnerMenuManager);
        listView = findViewById(R.id.lvItemMenuManager);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnBackMenuManager:
                finish();
                break;
        }
    }
}
