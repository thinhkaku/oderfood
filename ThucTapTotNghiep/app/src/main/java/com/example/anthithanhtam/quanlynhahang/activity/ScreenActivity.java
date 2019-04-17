package com.example.anthithanhtam.quanlynhahang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.anthithanhtam.quanlynhahang.R;

public class ScreenActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int layoutID() {
        return 0;
    }

    @Override
    protected void initView() {
        Intent intent = new Intent(this, SwichBeginAppActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void addEvent() {

    }


}
