package com.example.quang.orderfood.activities;

import android.app.Application;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.quang.orderfood.R;
import com.github.nkzawa.emitter.Emitter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

import adapter.GridviewTableAdapter;
import consts.Constants;
import de.hdodenhof.circleimageview.CircleImageView;
import objects.HistoryBill;
import objects.Table;
import objects.User;
import singleton.Singleton;

public class MainForWaiterActivity extends AppCompatActivity implements  AdapterView.OnItemClickListener, SearchView.OnQueryTextListener, View.OnClickListener {

    private static String SERVER_SEND_LIST_TABLE = "SERVER_SEND_LIST_TABLE";
    private static String CLIENT_SEND_REQUEST_TABLE = "CLIENT_SEND_REQUEST_TABLE";
    private final String CLIENT_SEND_REQUEST_LOGIN = "CLIENT_SEND_REQUEST_LOGIN";
    private static String LOG_OUT = "LOG_OUT";
    private static String REQUEST_BOOK = "REQUEST_BOOK";
    private boolean changeArray=false;
    public static boolean CHECK_TABLE = false;
    public static String ID_USER = "ID_USER";
    private  boolean kiemTraTrangThaiApp=true;
    Emitter.Listener onListTable;
    private static  String KET_NOI_LAI="KET_NOI_LAI";

    {
        onListTable = new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                getListTable(args[0]);
            }
        };
    }

    public static User user;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    private GridView gridView;
    private GridviewTableAdapter gridviewTableAdapter;
    private ArrayList<Table> arrTable;
    private ArrayList<Table> arrTable1;

    private SearchView searchView;

    private Animation animationButton;

    private Button btnShowListFree;
    private Button btnShowListBooked;
    private Button btnShowAll;

    private View line1;
    private View line2;
    private View line3;

    private CircleImageView avatar;
    private TextView tvName, txtBanDaDat, txtBanTrong;
    private Button btnProfile;
    private Button btnListBill;
    private Button btnLogOut;
    private Button btnBangTin;

    private Dialog dialogPeople, dilogQuitApp;
    private int number;
    private String people;
    private int banDangChon;// kiểm tra tránh tình trạng 2 người cùng nhập số bàn cùng 1 lúc


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_for_waiter);

        Toast.makeText(this,"ok",Toast.LENGTH_SHORT).show();
        initSockets();
        getUser();
        findId();
        initViews();
        getData();
        initDialogPeople();
    }

    private void ketNoiLai(){
        SharedPreferences sharedPreferences =getSharedPreferences(KET_NOI_LAI,MODE_PRIVATE);
        String userName=sharedPreferences.getString("userName",null);
        String userPass=sharedPreferences.getString("userPass",null);
        Singleton.Instance().getmSocket().emit(CLIENT_SEND_REQUEST_LOGIN,userName+"-"+userPass);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (kiemTraTrangThaiApp==true){
            return;
        }else {
            kiemTraTrangThaiApp=true;
            ketNoiLai();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Singleton.Instance().getmSocket().emit(LOG_OUT,user.getId());
        Log.e("destroy","ok");
    }

    private void initDialogPeople() {
        dialogPeople = new Dialog(this,android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth);
        dialogPeople.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogPeople.setContentView(R.layout.dialog_check_number);
        dialogPeople.setCancelable(false);
        final EditText edtPeople = dialogPeople.findViewById(R.id.edtPeopleDialog);
        final Button btnExit = dialogPeople.findViewById(R.id.btnExitDialog);
        final Button btnDone = dialogPeople.findViewById(R.id.btnDoneDialog);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnExit.startAnimation(animationButton);
                dialogPeople.dismiss();
            }
        });
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnDone.startAnimation(animationButton);
                people = edtPeople.getText().toString();
                if (people.isEmpty()){
                    Snackbar snackbar = Snackbar
                            .make(edtPeople, "Chưa nhập số bàn!", Snackbar.LENGTH_SHORT);
                    snackbar.setActionTextColor(Color.WHITE);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(Color.DKGRAY);
                    snackbar.show();
                }else {
                    Singleton.Instance().getmSocket().emit(REQUEST_BOOK,number);
                    Intent intent = new Intent(MainForWaiterActivity.this, MenuActivity.class);
                    intent.putExtra("numPeo",number+"-"+people);
                    startActivity(intent);
                    dialogPeople.dismiss();
                }
            }
        });
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

    private void initSockets() {
        Singleton.Instance().getmSocket().emit(REQUEST_BOOK,"-1");
        Singleton.Instance().getmSocket().on(SERVER_SEND_LIST_TABLE,onListTable);
        Singleton.Instance().getmSocket().emit("CLIENT_SEND_REQUEST_LIST_STAFF","123");

    }

    private void getData() {
        arrTable = new ArrayList<>();
        arrTable1 = new ArrayList<>();
        if (changeArray==false){
            gridviewTableAdapter = new GridviewTableAdapter(this,R.layout.item_gridview,arrTable);
        }else {
            gridviewTableAdapter = new GridviewTableAdapter(this,R.layout.item_gridview,arrTable1);
        }


        gridView.setAdapter(gridviewTableAdapter);
        gridviewTableAdapter.notifyDataSetChanged();
        gridView.setOnItemClickListener(this);
        btnShowAll.setOnClickListener(this);
        btnShowListFree.setOnClickListener(this);
        btnShowListBooked.setOnClickListener(this);
        btnProfile.setOnClickListener(this);
        btnLogOut.setOnClickListener(this);
        btnBangTin.setOnClickListener(this);
        btnListBill.setOnClickListener(this);
        Glide.with(this).load(Constants.PORT+MainForWaiterActivity.user.getImage())
                .into(avatar);
        tvName.setText(user.getName());
    }

    private void getUser()
    {
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra(Constants.KEY_PUSH_USER);
        ID_USER = user.getId();
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
                for (int i=0;i<arrTable.size()-1;i++)
                {
                    if (arrTable.get(i).getCheck() == 1)
                    {
                        if (banDangChon==i){
                            dialogPeople.dismiss();
                        }
                    }
                }
                txtBanTrong.setText(dem+""); //dem so ban trong
                txtBanDaDat.setText(arrTable.size()-dem+""); // dem so ban da dat
                gridviewTableAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initViews() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,0,0);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        toggle.getDrawerArrowDrawable().setColor(Color.WHITE);

        line3.setVisibility(View.VISIBLE);
        btnShowAll.setTextColor(Color.parseColor("#ef4b4c"));

    }

    private void findId() {
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        gridView = findViewById(R.id.gridview);
        btnShowAll = findViewById(R.id.btnShowListTable);
        btnShowListFree = findViewById(R.id.btnShowListTableFree);
        btnShowListBooked = findViewById(R.id.btnShowListTableBooked);

        animationButton = AnimationUtils.loadAnimation(MainForWaiterActivity.this,R.anim.button_apha);

        line1 = findViewById(R.id.line1);
        line2 = findViewById(R.id.line2);
        line3 = findViewById(R.id.line3);

        avatar = findViewById(R.id.profile_image);
        tvName = findViewById(R.id.tvNameStaff);
        txtBanDaDat =findViewById(R.id.txtBanDaDat);
        txtBanTrong =findViewById(R.id.txtBanTrong);
        btnProfile = findViewById(R.id.btnProfile);
        btnListBill = findViewById(R.id.btnListHistoryBill);
        btnLogOut = findViewById(R.id.btnLogOut);
        btnBangTin = findViewById(R.id.btnBangTin);
    }

    private void setLine(View v, Button b)
    {
        line1.setVisibility(View.INVISIBLE);
        line2.setVisibility(View.INVISIBLE);
        line3.setVisibility(View.INVISIBLE);

        btnShowAll.setTextColor(Color.parseColor("#90000000"));
        btnShowListBooked.setTextColor(Color.parseColor("#90000000"));
        btnShowListFree.setTextColor(Color.parseColor("#90000000"));

        v.setVisibility(View.VISIBLE);
        b.setTextColor(Color.parseColor("#ef4b4c"));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId())
        {
            case R.id.gridview:
                if (changeArray==false){
                    banDangChon=i;
                    Table table = arrTable.get(i);
                    int check = table.getCheck();
                    number = table.getNumber();
                    if (check == 0)
                    {
                        MainForWaiterActivity.CHECK_TABLE = false;
                        dialogPeople.show();
                    }
                    else if (check == 1)
                    {
                        MainForWaiterActivity.CHECK_TABLE = true;
                        Intent intent = new Intent(MainForWaiterActivity.this,BillActivity.class);
                        intent.putExtra("table",number+"");
                        startActivity(intent);
                    }
                }
                else if (changeArray==true){
                    Table table1 = arrTable1.get(i);
                    int check1 = table1.getCheck();
                    number = table1.getNumber();
                    if (check1 == 0)
                    {
                        MainForWaiterActivity.CHECK_TABLE = false;
                        dialogPeople.show();
                    }
                    else if (check1 == 1)
                    {
                        MainForWaiterActivity.CHECK_TABLE = true;
                        Intent intent = new Intent(MainForWaiterActivity.this,BillActivity.class);
                        intent.putExtra("table",number+"");
                        startActivity(intent);
                    }
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_view, menu);
        MenuItem itemSearch = menu.findItem(R.id.search_view);
        searchView = (SearchView) itemSearch.getActionView();
        //set OnQueryTextListener cho search view để thực hiện search theo text
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)){
            gridviewTableAdapter = new GridviewTableAdapter(this,R.layout.item_gridview,arrTable);
            gridView.setAdapter(gridviewTableAdapter);
            gridviewTableAdapter.notifyDataSetChanged();

        }else if (newText.equalsIgnoreCase("all")){
            gridviewTableAdapter = new GridviewTableAdapter(this,R.layout.item_gridview,arrTable);
            gridView.setAdapter(gridviewTableAdapter);
            gridviewTableAdapter.notifyDataSetChanged();
        }
        else if (newText.equalsIgnoreCase("free"))
        {
            ArrayList<Table> tables = new ArrayList<>();
            for (Table t: arrTable)
            {
                if (t.getCheck() == 0)
                {
                    tables.add(t);
                }
            }
            gridviewTableAdapter = new GridviewTableAdapter(this,R.layout.item_gridview,tables);
            gridView.setAdapter(gridviewTableAdapter);
            gridviewTableAdapter.notifyDataSetChanged();
        }
        else if (newText.equalsIgnoreCase("book"))
        {
            ArrayList<Table> tables = new ArrayList<>();
            for (Table t: arrTable)
            {
                if (t.getCheck() == 1)
                {
                    tables.add(t);
                }
            }
            gridviewTableAdapter = new GridviewTableAdapter(this,R.layout.item_gridview,tables);
            gridView.setAdapter(gridviewTableAdapter);
            gridviewTableAdapter.notifyDataSetChanged();
        }
        else
        {
            ArrayList<Table> tables = new ArrayList<>();
            for (Table t: arrTable)
            {
                if (String.valueOf(t.getNumber()).contains(newText) == true)
                {
                    tables.add(t);
                }
            }
            gridviewTableAdapter = new GridviewTableAdapter(this,R.layout.item_gridview,tables);
            gridView.setAdapter(gridviewTableAdapter);
            gridviewTableAdapter.notifyDataSetChanged();
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnShowListTable:
                btnShowAll.startAnimation(animationButton);
                changeArray =false;
                gridviewTableAdapter = new GridviewTableAdapter(this,R.layout.item_gridview,arrTable);
                gridView.setAdapter(gridviewTableAdapter);
                gridviewTableAdapter.notifyDataSetChanged();
                setLine(line3,btnShowAll);
                break;
            case R.id.btnShowListTableFree:
                btnShowListFree.startAnimation(animationButton);
                changeArray=true;
                arrTable1.clear();
                for (Table t: arrTable)
                {
                    if (t.getCheck() == 0)
                    {
                        arrTable1.add(t);
                    }
                }
                gridviewTableAdapter = new GridviewTableAdapter(this,R.layout.item_gridview,arrTable1);
                gridView.setAdapter(gridviewTableAdapter);
                gridviewTableAdapter.notifyDataSetChanged();
                setLine(line1,btnShowListFree);
                break;
            case R.id.btnShowListTableBooked:
                btnShowListBooked.startAnimation(animationButton);
                changeArray=true;
                arrTable1.clear();
                for (Table t: arrTable)
                {
                    if (t.getCheck() == 1)
                    {
                        arrTable1.add(t);
                    }
                }
                gridviewTableAdapter = new GridviewTableAdapter(this,R.layout.item_gridview,arrTable1);
                gridView.setAdapter(gridviewTableAdapter);
                gridviewTableAdapter.notifyDataSetChanged();
                setLine(line2,btnShowListBooked);
                break;
            case R.id.btnProfile:
                btnProfile.startAnimation(animationButton);
                drawerLayout.closeDrawers();
                Intent intent = new Intent(MainForWaiterActivity.this,ProfileActivity.class);
                intent.putExtra("key","w");
                startActivity(intent);
                break;
            case R.id.btnLogOut:
                btnLogOut.startAnimation(animationButton);
                drawerLayout.closeDrawers();
                Singleton.Instance().getmSocket().emit(LOG_OUT,user.getId());
                Intent intent1 = new Intent(MainForWaiterActivity.this,LoginActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.btnListHistoryBill:
                btnListBill.startAnimation(animationButton);
                drawerLayout.closeDrawers();
                Intent in = new Intent(MainForWaiterActivity.this, HistoryBillActivity.class);
                in.putExtra("u",user);
                startActivity(in);
                break;
            case R.id.btnBangTin:
                drawerLayout.closeDrawers();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        //finish();
        //Singleton.Instance().getmSocket().disconnect();
        initDialogQuitApp();
        dilogQuitApp.show();
        //super.onBackPressed();
    }
}
