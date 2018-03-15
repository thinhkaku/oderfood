package com.example.quang.orderfood.activities;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.quang.orderfood.R;
import com.github.nkzawa.emitter.Emitter;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import adapter.GridviewTableAdapter;
import adapter.MenuManagementAdapterPager;
import consts.Constants;
import de.hdodenhof.circleimageview.CircleImageView;
import fragment.BanDaDatFragment;
import fragment.BanTrongFragment;
import fragment.TatCaCacBanFragment;
import objects.PagerTitle;
import objects.Table;
import objects.User;
import singleton.Singleton;

/**
 * Created by Administrator on 3/13/2018.
 */

public class MainForWaiterActivity1 extends AppCompatActivity implements View.OnClickListener {
    private MenuManagementAdapterPager menuManagementAdapterPager;
    private ArrayList<PagerTitle>arrPager;
    private Animation animationButton;
    private ViewPager viewPager;
    public static String ID_USER = "ID_USER";
    private Toolbar toolbar;
    public static boolean CHECK_TABLE1 = false;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    public static User user;
    private ArrayList<Table>arrTable;
    private BanDaDatFragment banDaDatFragment;
    private BanTrongFragment banTrongFragment;
    private TatCaCacBanFragment tatCaCacBanFragment;
    private String KEY_PUSH_USER_DATA="KEY_PUSH_USER_DATA";
    private CircleImageView avatar;
    private TextView tvName,txtBanDaDat,txtBanTrong;
    private Button btnProfile,btnListBill,btnLogOut,btnBangTin;
    private java.lang.String REQUEST_BOOK="REQUEST_BOOK";
    private String SERVER_SEND_LIST_TABLE="SERVER_SEND_LIST_TABLE";
    private Emitter.Listener onListTable;
    private Dialog dilogQuitApp;
    private java.lang.String LOG_OUT="LOG_OUT";

    {
        onListTable = new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                getListTable(args[0]);
            }
        };
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_for_waiter1);
        initSocket();
        getUser();
        getToolBar();
        initView();
        getData();
    }

    private void getListTable(final Object arg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                JSONArray data = (JSONArray) arg;
                arrTable.clear();
                for (int i=0; i<data.length(); i++)
                {
                    try {
                        JSONObject object = data.getJSONObject(i);
                        int num = object.getInt("tenBan");
                        String position = object.getString("viTri");
                        int numOfChair = object.getInt("soGhe");
                        String note = object.getString("ghiChu");
                        int check = object.getInt("tinhTrang");
                        Table table = new Table(num,position,numOfChair,check,note);
                        arrTable.add(table);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                int dem=0;
                for (Table t: arrTable)
                {
                    if (t.getCheck() == 0)
                    {
                        dem=dem+1;
                    }
                }

                txtBanTrong.setText(dem+""); //dem so ban trong
                txtBanDaDat.setText(arrTable.size()-dem+""); // dem so ban da dat
                //gridviewTableAdapter.notifyDataSetChanged();
            }
        });
    }


    private void getData() {
        arrTable=new ArrayList<>();
        btnProfile.setOnClickListener(this);
        btnLogOut.setOnClickListener(this);
        btnBangTin.setOnClickListener(this);
        btnListBill.setOnClickListener(this);
        Glide.with(this).load(Constants.PORT+MainForWaiterActivity1.user.getImage())
                .into(avatar);
        tvName.setText(user.getName());
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getToolBar() {
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,0,0);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        toggle.getDrawerArrowDrawable().setColor(Color.WHITE);
    }

    private void getUser() {
        Gson gson = new Gson();
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(this.getApplicationContext());
        String json = appSharedPrefs.getString(KEY_PUSH_USER_DATA, "");
        user = gson.fromJson(json, User.class);
//        Intent intent = getIntent();
//        user = (User) intent.getSerializableExtra(Constants.KEY_PUSH_USER);
        ID_USER = user.getId();
    }

    private void initView() {
        avatar = findViewById(R.id.profile_image);
        tvName = findViewById(R.id.tvNameStaff);
        txtBanDaDat =findViewById(R.id.txtBanDaDat);
        txtBanTrong =findViewById(R.id.txtBanTrong);
        btnProfile = findViewById(R.id.btnProfile);
        btnListBill = findViewById(R.id.btnListHistoryBill);
        btnLogOut = findViewById(R.id.btnLogOut);
        btnBangTin = findViewById(R.id.btnBangTin);
        arrPager=new ArrayList<>();
        banDaDatFragment=new BanDaDatFragment();
        banTrongFragment =new BanTrongFragment();
        tatCaCacBanFragment =new TatCaCacBanFragment();
        arrPager.add(new PagerTitle("Tất Cả",tatCaCacBanFragment));
        arrPager.add(new PagerTitle("Bàn đã đặt",banDaDatFragment));
        arrPager.add(new PagerTitle("Bàn trống",banTrongFragment));
        viewPager=findViewById(R.id.viewPagerWaiter);
        menuManagementAdapterPager=new MenuManagementAdapterPager(getSupportFragmentManager(),arrPager);
        viewPager.setAdapter(menuManagementAdapterPager);
        menuManagementAdapterPager.notifyDataSetChanged();
        animationButton = AnimationUtils.loadAnimation(this,R.anim.button_apha);
    }

    private void initSocket() {
        Singleton.Instance().getmSocket().emit(REQUEST_BOOK,"-1");
        Singleton.Instance().getmSocket().on(SERVER_SEND_LIST_TABLE,onListTable);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnProfile:
                btnProfile.startAnimation(animationButton);
                drawerLayout.closeDrawers();
                Intent intent = new Intent(MainForWaiterActivity1.this,ProfileActivity.class);
                intent.putExtra("key","w");
                startActivity(intent);
                break;
            case R.id.btnListHistoryBill:
                btnListBill.startAnimation(animationButton);
                drawerLayout.closeDrawers();
                Intent in = new Intent(MainForWaiterActivity1.this, HistoryBillActivity.class);
                in.putExtra("u",user);
                startActivity(in);
                break;
            case R.id.btnLogOut:
                btnLogOut.startAnimation(animationButton);
                drawerLayout.closeDrawers();
                Singleton.Instance().getmSocket().emit(LOG_OUT,user.getId());
                Intent intent1 = new Intent(MainForWaiterActivity1.this,LoginActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.btnBangTin:
                drawerLayout.closeDrawers();
                Intent intent2 =new Intent(MainForWaiterActivity1.this,NewsActivity.class);
                startActivity(intent2);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        initDialogQuitApp();
        dilogQuitApp.show();
    }
}
