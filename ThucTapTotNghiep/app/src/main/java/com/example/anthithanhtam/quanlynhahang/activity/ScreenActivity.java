package com.example.anthithanhtam.quanlynhahang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.anthithanhtam.quanlynhahang.R;
import com.example.anthithanhtam.quanlynhahang.ServiceMyTable;

public class ScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i=new Intent(this, ServiceMyTable.class);
        startService(i);
        Intent intent = new Intent(this, SwichBeginAppActivity.class);
        startActivity(intent);
        finish();
    }
}
