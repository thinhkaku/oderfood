package com.example.quang.orderfood.activities;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
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

public class SplashScreenActivity extends AppCompatActivity {

    private String MY_PREFS_NAME="oderfood";
    private Socket mSocket;
    private boolean ktSocket=false;
    private Dialog dilogQuitApp;
    private Animation animationButton;
    private TextView txtScreen;
    private Emitter.Listener onResult;
    private java.lang.String CLIENT_SEND_REQUEST_LOGIN="CLIENT_SEND_REQUEST_LOGIN";
    private String SERVER_SEND_RESULT="SERVER_SEND_RESULT";
    private AlertDialog.Builder builder;
    private Animation txtCreenAnimation;
    private AlertDialog alertDialog;

    BroadcastReceiver ktKetnoi=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager.getActiveNetworkInfo()!=null){
                CountDownTimer countDownTimer =new CountDownTimer(1000,2000) {
                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        thongBaoKetNoiDilog();
                        alertDialog.dismiss();
                        countDownSwitch();
                    }
                }.start();
            }else {
                thongBaoKetNoiDilog();
                alertDialog.show();
            }
        }
    };

    {
        try {
            mSocket = IO.socket(Constants.PORT);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        onResult = new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                if (ktSocket==false){
                    handleResultFromServerSplashScreenActivity(args[0]);
                }

            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        initSockets();
        Singleton.Instance().setmSocket(mSocket);
        Singleton.Instance().setOnResult(onResult);
        txtCreenAnimation = AnimationUtils.loadAnimation(this,R.anim.txt_creen_apha);
        animationButton =AnimationUtils.loadAnimation(SplashScreenActivity.this,R.anim.button_apha);
        txtScreen=findViewById(R.id.txtScreen);
        txtScreen.startAnimation(txtCreenAnimation);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter =new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(ktKetnoi,intentFilter);
    }

    private void initDialogQuitApp() {
        dilogQuitApp = new Dialog(this,android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth);
        dilogQuitApp.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dilogQuitApp.setContentView(R.layout.quit_app_dilog);
        dilogQuitApp.setCancelable(false);
        final Button btnHuy = dilogQuitApp.findViewById(R.id.btnHuyExit);
        final Button btnThoat = dilogQuitApp.findViewById(R.id.btnThoatDialog);

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnHuy.startAnimation(animationButton);
                dilogQuitApp.dismiss();
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnThoat.startAnimation(animationButton);
                dilogQuitApp.dismiss();
//                Intent intent = new Intent(Intent.ACTION_MAIN);
//                intent.addCategory(Intent.CATEGORY_HOME);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
                finishAffinity();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (ktKetnoi!=null){
            unregisterReceiver(ktKetnoi);
        }
    }

    private void handleResultFromServerSplashScreenActivity(final Object arg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ktSocket=true;
                JSONArray data = (JSONArray) arg;
                if (data.length() == 0)
                {
                    Intent intent = new Intent(SplashScreenActivity.this,LoginActivity.class);
                    startActivity(intent);
                    // trong anim ta tạo hai file rồi gọi ở đây để tạo animation khi chuyển activity
                    overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
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
                            Intent intent = new Intent(SplashScreenActivity.this,MainForManagerActivity.class);
                            intent.putExtra(Constants.KEY_PUSH_USER,user);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
                            finish();
                        }else if (user.getPosition().equalsIgnoreCase("BB")){
                            Intent intent = new Intent(SplashScreenActivity.this,MainForWaiterActivity.class);
                            intent.putExtra(Constants.KEY_PUSH_USER,user);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
                            finish();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                finish();
            }
        });
    }

    private void checkInternet()
    {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        //3G check
        boolean is3g = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                .isConnectedOrConnecting();
        //WiFi Check
        boolean isWifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .isConnectedOrConnecting();

        if (!is3g && !isWifi)
        {
            dialog();
            return;
        }
        else
        {
            countDownSwitch();
        }
    }

    private void thongBaoKetNoiDilog(){
        builder =new AlertDialog.Builder(this);
        builder.setMessage("Vui lòng kiểm tra kết nối mạng của bạn!");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i){
                alertDialog.dismiss();
            }
        });
        alertDialog=builder.create();

    }

    private void dialog()
    {

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        // khởi tạo dialog
        alertDialogBuilder.setMessage("Vui lòng kiểm tra kết nối mạng của bạn.");
        // thiết lập nội dung cho dialog
        alertDialogBuilder.setPositiveButton("Thử lại", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                checkInternet();

            }
        });

        alertDialogBuilder.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        // tạo dialog
        alertDialog.show();
        // hiển thị dialog
    }


    // đếm ngược thời gian để chuyển màn hình
    private void countDownSwitch() {
        CountDownTimer countDownTimer = new CountDownTimer(3000,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
//                Intent intent = new Intent(SplashScreenActivity.this,LoginActivity.class);
//                startActivity(intent);
//                // trong anim ta tạo hai file rồi gọi ở đây để tạo animation khi chuyển activity
//                overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
//                finish();
                begin();
            }
        }.start();
    }

    private void begin() {
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String taiKhoan = prefs.getString("Taikhoan", "");
        String matKhau = prefs.getString("Matkhau", "");
        if (matKhau==null){
                            Intent intent = new Intent(SplashScreenActivity.this,LoginActivity.class);
                startActivity(intent);
                // trong anim ta tạo hai file rồi gọi ở đây để tạo animation khi chuyển activity
                overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
                finish();
        }else {
            mSocket.emit(CLIENT_SEND_REQUEST_LOGIN,taiKhoan+"-"+matKhau);
        }
    }
    private void initSockets() {
        mSocket.connect();
        mSocket.on(SERVER_SEND_RESULT,onResult);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        initDialogQuitApp();
        dilogQuitApp.show();
    }
}
