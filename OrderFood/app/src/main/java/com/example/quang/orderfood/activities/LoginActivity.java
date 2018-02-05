package com.example.quang.orderfood.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quang.orderfood.R;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import consts.Constants;
import objects.User;
import singleton.Singleton;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private final String CLIENT_SEND_REQUEST_LOGIN = "CLIENT_SEND_REQUEST_LOGIN";
    private final String SERVER_SEND_RESULT = "SERVER_SEND_RESULT";
    private CheckBox checkBox;
    private SharedPreferences.Editor editor;
    Socket mSocket;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;
    Emitter.Listener onResult;
    private String MY_PREFS_NAME="oderfood";

    {
        try {
            mSocket = IO.socket(Constants.PORT);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        onResult = new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                handleResultFromServer(args[0]);
            }
        };
    }

    private Animation buttonAnimationApha;
    private EditText edtUser;
    private EditText edtPass;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initSockets();

        findId();

        initViews();

        Singleton.Instance().setmSocket(mSocket);
        Singleton.Instance().setOnResult(onResult);
        loadDangNhapDilog();
    }

    private void loadDangNhapDilog(){
        builder =new AlertDialog.Builder(this);
        View view =getLayoutInflater().inflate(R.layout.load_dang_nhap_dilog,null);
        builder.setView(view);
        alertDialog=builder.create();
        //alertDialog.show();
    }

    private void handleResultFromServer(final Object arg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                alertDialog.dismiss();
                JSONArray data = (JSONArray) arg;
                if (data.length() == 0)
                {
                    Snackbar snackbar = Snackbar
                            .make(edtPass, "Tài khoản hoặc mật khẩu chưa chính xác!", Snackbar.LENGTH_SHORT);
                    snackbar.setActionTextColor(Color.WHITE);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(Color.DKGRAY);
                    snackbar.show();
                }
                else {
                    try {
                        JSONObject object = data.getJSONObject(0);
                        String id = object.getString("idNhanVien");
                        String name = object.getString("tenNhanVien");
                        String sex = object.getString("gioiTinh");
                        String dateOfBirth = object.getString("ngaySinh");
                        String address = object.getString("queQuan");
                        String phone = object.getString("soDienThoai");
                        String position = object.getString("chucVu");
                        String dateOfStart = object.getString("ngayVao");
                        String salary = object.getString("luongNgay");
                        String up = object.getString("userPass");
                        String image = object.getString("anhDaiDien");

                        User user = new User(id,name,sex,dateOfBirth,address
                                ,phone,position,dateOfStart,salary,up,image);

                        if (user.getPosition().equalsIgnoreCase("QL"))
                        {
                            Intent intent = new Intent(LoginActivity.this,MainForManagerActivity.class);
                            intent.putExtra(Constants.KEY_PUSH_USER,user);
                            startActivity(intent);
                            //overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
                            //mSocket.disconnect();
                            //Singleton.Instance().getmSocket().emit("CLIENT_SEND_REQUEST_LIST_STAFF","123");

                            finish();
                        }else if (user.getPosition().equalsIgnoreCase("BB")){
                            Intent intent = new Intent(LoginActivity.this,MainForWaiterActivity.class);
                            intent.putExtra(Constants.KEY_PUSH_USER,user);
                            startActivity(intent);
                            //overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
                            //mSocket.disconnect();
                            //Singleton.Instance().getmSocket().emit("CLIENT_SEND_REQUEST_LIST_STAFF","123");

                            finish();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void initSockets() {
        mSocket.connect();
        mSocket.on(SERVER_SEND_RESULT,onResult);
    }

    private void initViews() {
        btnLogin.setOnClickListener(this);
        checkBox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (checkBox.isChecked()) {
                    Snackbar snackbar = Snackbar
                            .make(checkBox, "Nhớ mật khẩu!", Snackbar.LENGTH_SHORT);
                    snackbar.setActionTextColor(Color.WHITE);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(Color.DKGRAY);
                    snackbar.show();
                    SharedPreferences sharedPreferences1 = getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences1.edit();
                    editor.putBoolean("checked", true);
                    editor.commit();
                }else {
                    Snackbar snackbar = Snackbar
                            .make(checkBox, "Bỏ nhớ mật khẩu!", Snackbar.LENGTH_SHORT);
                    snackbar.setActionTextColor(Color.WHITE);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(Color.DKGRAY);
                    snackbar.show();
                    SharedPreferences sharedPreferences1 = getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences1.edit();
                    editor.putBoolean("checked", false);
                    editor.commit();
                }
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putString("Taikhoan",edtUser.getText()+"");
                    editor.putString("Matkhau",edtPass.getText()+"");
                    editor.apply();
                }else {
                    editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putString("Taikhoan",edtUser.getText()+"");
                    editor.putString("Matkhau","");
                    editor.apply();

                }

            }
        });
    }

    private void findId() {
        edtUser = findViewById(R.id.edtUser);
        edtPass = findViewById(R.id.edtPass);
        checkBox =findViewById(R.id.ckNhoMatKhau);
        btnLogin = findViewById(R.id.btnLogin);
        buttonAnimationApha = AnimationUtils.loadAnimation(LoginActivity.this,R.anim.button_apha);
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        edtUser.setText(prefs.getString("Taikhoan",""));
        edtPass.setText(prefs.getString("Matkhau",""));
    }

    @Override
    public void onClick(View view) {
        //alertDialog.show();
        btnLogin.startAnimation(buttonAnimationApha);
        String user = edtUser.getText().toString();
        String pass = edtPass.getText().toString();
        if (user.isEmpty() || pass.isEmpty())
        {
            Snackbar snackbar = Snackbar
                    .make(edtPass, "Bạn cần nhập đủ thông tin!", Snackbar.LENGTH_SHORT);
            snackbar.setActionTextColor(Color.WHITE);
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(Color.DKGRAY);
            snackbar.show();
            return;
        }else {
            if (checkBox.isChecked()==true){
                editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("Taikhoan",edtUser.getText()+"");
                editor.putString("Matkhau",edtPass.getText()+"");
                editor.apply();
            }else {
                editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("Taikhoan",edtUser.getText()+"");
                editor.putString("Matkhau","");
                editor.apply();
            }
            mSocket.emit(CLIENT_SEND_REQUEST_LOGIN,user+"-"+pass);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        save1(checkBox.isChecked());

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (checkBox.isChecked()){
            editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("Taikhoan",edtUser.getText()+"");
            editor.putString("Matkhau",edtPass.getText()+"");
            editor.apply();
        }else {
            editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("Taikhoan",edtUser.getText()+"");
            editor.putString("Matkhau","");
            editor.apply();

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkBox.setChecked(load1());
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkBox.setChecked(load1());
    }

    private boolean load1() {
        SharedPreferences sharedPreferences1 = getPreferences(Context.MODE_PRIVATE);
        return sharedPreferences1.getBoolean("checked", false);
    }

    private void save1(final boolean isChecked) {
        SharedPreferences sharedPreferences1 = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences1.edit();
        editor.putBoolean("checked", isChecked);
        editor.commit();
    }
}
