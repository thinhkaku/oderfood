package com.example.quang.orderfood.activities;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.quang.orderfood.R;
import com.github.nkzawa.emitter.Emitter;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;

import objects.News;
import objects.User;
import singleton.Singleton;

/**
 * Created by Administrator on 3/9/2018.
 */

public class DangTinActivity extends AppCompatActivity {
    private Button btnDangTin;
    private User user;
    private ImageButton btnBackDangTin;
    private EditText edtNoiDungTin;
    private String KEY_PUSH_USER_DATA="KEY_PUSH_USER_DATA";
    private String CLIEND_REQUEST_DANG_TIN="CLIEND_REQUEST_DANG_TIN";
    private Emitter.Listener onResult;
    private String SERVER_REQUEST_RESULT_DANGTIN="SERVER_REQUEST_RESULT_DANGTIN";
    private java.lang.String CLIENT_SEND_NEWS="CLIENT_SEND_NEWS";
    private Animation animationButton;

    {
        onResult=new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                getResult(args[0]);
            }
        };
    }

    private void getResult(final Object arg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String kp =(String)arg;
                if (kp.equals("1")){
                    Toast.makeText(DangTinActivity.this,"Tải lên thành công",Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_tin);
        initSocket();
        getUser();
        initView();
        addEvent();
    }

    private void initSocket() {
        Singleton.Instance().getmSocket().on(SERVER_REQUEST_RESULT_DANGTIN,onResult);
    }

    private void getUser() {
        Gson gson = new Gson();
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(this.getApplicationContext());
        String json = appSharedPrefs.getString(KEY_PUSH_USER_DATA, "");
        user = gson.fromJson(json, User.class);
    }


    private void initView() {
        btnBackDangTin=findViewById(R.id.btnBackDangTin);
        btnDangTin=findViewById(R.id.btnDangTin);
        edtNoiDungTin=findViewById(R.id.edtNoiDungTin);
        animationButton= AnimationUtils.loadAnimation(DangTinActivity.this,R.anim.button_apha);
    }

    private void addEvent() {
        btnBackDangTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnBackDangTin.startAnimation(animationButton);
                finish();
            }
        });
        btnDangTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnDangTin.startAnimation(animationButton);
                String noiDung=edtNoiDungTin.getText().toString();
                String ten=user.getName();
                String hinhAnh=user.getImage();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String currentDateandTime = sdf.format(new Date());
                String query="INSERT INTO `bangtin` VALUES ('null','"+ten+"','"+noiDung+"','"+hinhAnh+"','"+currentDateandTime+"')";
                if (noiDung.isEmpty()){
                    Snackbar snackbar = Snackbar
                            .make(btnDangTin, "Chưa nhập nội dung", Snackbar.LENGTH_SHORT);
                    snackbar.setActionTextColor(Color.WHITE);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(Color.DKGRAY);
                    snackbar.show();
                }else {
                    Singleton.Instance().getmSocket().emit(CLIEND_REQUEST_DANG_TIN,query);
                    Singleton.Instance().getmSocket().emit(CLIENT_SEND_NEWS,123);
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finish();
    }
}
