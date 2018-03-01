package com.example.quang.orderfood.activities;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.quang.orderfood.R;
import com.github.nkzawa.emitter.Emitter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import adapter.ListviewStaffAdapter;
import consts.Constants;
import de.hdodenhof.circleimageview.CircleImageView;
import objects.Staff;
import objects.User;
import singleton.Singleton;

public class MainForManagerActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, SearchView.OnQueryTextListener {

    private final String CLIENT_SEND_REQUEST_LIST_STAFF = "CLIENT_SEND_REQUEST_LIST_STAFF";
    private final String SERVER_SEND_LIST_STAFF = "SERVER_SEND_LIST_STAFF";
    private final String CLIENT_SEND_REQUEST_DELETE_STAFF = "CLIENT_SEND_REQUEST_DELETE_STAFF";
    private final String CLIENT_SEND_REQUEST_EDIT_STAFF = "CLIENT_SEND_REQUEST_EDIT_STAFF";
    private  boolean changArray = false;
    private  boolean changArray1 = false;
    private static String LOG_OUT = "LOG_OUT";
    Emitter.Listener onListStaff;
    {
        onListStaff = new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                getListStaff(args[0]);
            }
        };
    }



    public static User user;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private SearchView searchView;

    private ListView lvStaff;
    private ArrayList<Staff> arrStaff;
    private ArrayList<Staff> arrStaff1;
    private ArrayList<Staff> arrStaff2;
    private ArrayList<String>arrTenNhanVien;
    private ListviewStaffAdapter adap;

    private View line1;
    private View line2;
    private View line3;

    private Button btnShowAllStaff;
    private Button btnShowStaffOnline;
    private Button btnShowStaffOffline;

    private Button btnStaffManager;
    private Button btnMenuManager;
    private Button btnLogOut;
    private Button btnDangThongBao;
    private ImageView imAddStaff;
    private TextView txtSoNguoiOnline, txtSoNguoiOffline, txtTongSoNhanVien;

    private CircleImageView avatar;
    private TextView tvName;
    private Dialog editStaff;
    private Dialog dilogQuitApp;
    private Animation animationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_for_manager);

        initSockets();
        findId();
        getUser();
        initViews();
        getData();
        clickEvents();
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

    private void initSockets() {
        Singleton.Instance().getmSocket().emit(CLIENT_SEND_REQUEST_LIST_STAFF,"123");
        Singleton.Instance().getmSocket().on(SERVER_SEND_LIST_STAFF,onListStaff);
    }

    private void getListStaff(final Object arg) {
        arrStaff =new ArrayList<>();
        arrStaff1 =new ArrayList<>();
        arrStaff2 =new ArrayList<>();
        //arrStaff = new ArrayList<>();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                JSONArray data = (JSONArray) arg;
                arrStaff.clear();
                arrStaff1.clear();
                arrStaff2.clear();
                for (int i=0; i<data.length(); i++)
                {
                    try {
                        JSONObject object = data.getJSONObject(i);
                        String id = object.getString("idNhanVien");
                        String name = object.getString("tenNhanVien");
                        String sex = object.getString("gioiTinh");
                        String dateOfBirth = object.getString("ngaySinh");
                        String address = object.getString("queQuan");
                        String phone = object.getString("soDienThoai");
                        String dateStart = object.getString("ngayVao");
                        String image = object.getString("anhDaiDien");
                        int check = object.getInt("online");
                        String salary = object.getString("luongNgay");
                        String userPass = object.getString("userPass");
                        String position = object.getString("chucVu");
                        Staff staff = new Staff(name,id,sex,dateOfBirth,address
                                ,phone,dateStart,image,check,salary);
                        staff.setUserPass(userPass);
                        staff.setPosition(position);
                        if (check == 1)
                        {
                            arrStaff.add(0,staff);
                        }
                        else {
                            arrStaff.add(staff);
                        }

                        if (changArray ==false){
                            adap = new ListviewStaffAdapter(MainForManagerActivity.this,R.layout.item_listview_staff,arrStaff);

                        }else {
                            arrStaff1.clear();
                            arrStaff2.clear();
                            for (Staff t: arrStaff)
                            {
                                if (t.getCheckOnline() == 0)
                                {
                                    arrStaff1.add(t);
                                }else {
                                    arrStaff2.add(t);
                                }
                            }
                            if (changArray1==true){
                                adap =new ListviewStaffAdapter(MainForManagerActivity.this,R.layout.item_listview_staff,arrStaff2);
                            }else {
                                adap =new ListviewStaffAdapter(MainForManagerActivity.this,R.layout.item_listview_staff,arrStaff1);
                            }


                        }
                        lvStaff.setAdapter(adap);
                        adap.notifyDataSetChanged();
                        int dem=0;
                        for (int j=0;j<arrStaff.size()-1;j++){
                            if (arrStaff.get(j).getCheckOnline()==1){
                                dem++;
                            }
                        }
                        txtSoNguoiOnline.setText(dem+"");
                        txtSoNguoiOffline.setText(arrStaff.size()-dem+"");
                        txtTongSoNhanVien.setText(arrStaff.size()+"");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                //adap.notifyDataSetChanged();
                //Singleton.Instance().getmSocket().emit(CLIENT_SEND_REQUEST_TABLE,"123");
            }
        });
    }

    private void findId() {
        arrTenNhanVien=new ArrayList<>();
        toolbar = findViewById(R.id.toolbarManager);
        drawerLayout = findViewById(R.id.drawerLayoutManager);
        lvStaff = findViewById(R.id.lvStaff);
        imAddStaff = findViewById(R.id.addStaff);
        btnShowAllStaff = findViewById(R.id.btnShowAllStaff);
        btnShowStaffOffline = findViewById(R.id.btnShowListStaffOffline);
        btnShowStaffOnline = findViewById(R.id.btnShowListStaffOnline);
        txtSoNguoiOnline =findViewById(R.id.txtSoNguoiOnline);
        txtSoNguoiOffline =findViewById(R.id.txtSoNguoiOffline);
        txtTongSoNhanVien =findViewById(R.id.txtTongSoNhanVien);
        animationButton= AnimationUtils.loadAnimation(MainForManagerActivity.this,R.anim.button_apha);

        line1 = findViewById(R.id.line1Manager);
        line2 = findViewById(R.id.line2Manager);
        line3 = findViewById(R.id.line3Manager);

        btnStaffManager = findViewById(R.id.btnStaffManager);
        btnMenuManager = findViewById(R.id.btnMenuManager);
        btnLogOut = findViewById(R.id.btnLogOutManager);
        btnDangThongBao = findViewById(R.id.btnDangThongBao);
    }

    private void getUser() {
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra(Constants.KEY_PUSH_USER);
    }

    private void getData() {
        tvName.setText(MainForManagerActivity.user.getName());
        Glide.with(this).load(Constants.PORT+MainForManagerActivity.user.getImage())
                .into(avatar);

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
        btnShowAllStaff.setTextColor(Color.parseColor("#ef4b4c"));

        avatar = findViewById(R.id.profile_imageManager);
        tvName = findViewById(R.id.tvNameManager);

    }
    private void clickEvents() {
        btnShowAllStaff.setOnClickListener(this);
        btnShowStaffOffline.setOnClickListener(this);
        btnShowStaffOnline.setOnClickListener(this);
        imAddStaff.setOnClickListener(this);

       lvStaff.setOnItemClickListener(this);
        lvStaff.setOnItemLongClickListener(this);

        btnLogOut.setOnClickListener(this);
        btnMenuManager.setOnClickListener(this);
        btnStaffManager.setOnClickListener(this);
        btnDangThongBao.setOnClickListener(this);
    }


    private void setLine(View v, Button b)
    {
        line1.setVisibility(View.INVISIBLE);
        line2.setVisibility(View.INVISIBLE);
        line3.setVisibility(View.INVISIBLE);

        btnShowAllStaff.setTextColor(Color.parseColor("#90000000"));
        btnShowStaffOnline.setTextColor(Color.parseColor("#90000000"));
        btnShowStaffOffline.setTextColor(Color.parseColor("#90000000"));

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
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnShowAllStaff:
                btnShowAllStaff.startAnimation(animationButton);
                changArray=false;
                adap = new ListviewStaffAdapter(this,R.layout.item_listview_staff,arrStaff);
                lvStaff.setAdapter(adap);
                adap.notifyDataSetChanged();
                setLine(line3,btnShowAllStaff);
                break;
            case R.id.btnShowListStaffOffline:
                btnShowStaffOffline.startAnimation(animationButton);
                changArray=true;
                changArray1=false;
                arrStaff2.clear();
                for (Staff t: arrStaff)
                {
                    if (t.getCheckOnline() == 0)
                    {
                        arrStaff2.add(t);
                    }
                }
                adap = new ListviewStaffAdapter(this,R.layout.item_listview_staff,arrStaff2);
                lvStaff.setAdapter(adap);
                adap.notifyDataSetChanged();
                setLine(line2,btnShowStaffOffline);
                break;
            case R.id.btnShowListStaffOnline:
                btnShowStaffOnline.startAnimation(animationButton);
                changArray=true;
                changArray1=true;
                arrStaff1.clear();
                for (Staff t: arrStaff)
                {
                    if (t.getCheckOnline() == 1)
                    {
                        arrStaff1.add(t);
                    }
                }
                adap = new ListviewStaffAdapter(this,R.layout.item_listview_staff,arrStaff1);
                lvStaff.setAdapter(adap);
                adap.notifyDataSetChanged();
                setLine(line1,btnShowStaffOnline);
                break;
            case R.id.btnStaffManager:
                Intent intent =new Intent(MainForManagerActivity.this,MenuManagementActivity.class);
                startActivity(intent);
                btnStaffManager.startAnimation(animationButton);
                drawerLayout.closeDrawers();
                break;
            case R.id.btnMenuManager:
                btnMenuManager.startAnimation(animationButton);
                drawerLayout.closeDrawers();
                break;
            case R.id.btnLogOutManager:
                btnLogOut.startAnimation(animationButton);
                drawerLayout.closeDrawers();
                Singleton.Instance().getmSocket().emit(LOG_OUT,user.getId());
                Intent intent1 = new Intent(MainForManagerActivity.this,LoginActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.addStaff:
                imAddStaff.startAnimation(animationButton);
                Intent intent2 = new Intent(MainForManagerActivity.this,AddStaffActivity.class);
                startActivity(intent2);
                break;
            case R.id.btnDangThongBao:
                drawerLayout.closeDrawers();
                break;
        }
    }
    //su kien trong listView
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (changArray==false){
            Intent intent = new Intent(MainForManagerActivity.this,ProfileActivity.class);
            intent.putExtra("key","m");
            intent.putExtra("staff",arrStaff.get(i));
            startActivity(intent);
        }else {
            if (changArray1==true){
                Intent intent = new Intent(MainForManagerActivity.this,ProfileActivity.class);
                intent.putExtra("key","m");
                intent.putExtra("staff",arrStaff1.get(i));
                startActivity(intent);
            }else {
                Intent intent = new Intent(MainForManagerActivity.this,ProfileActivity.class);
                intent.putExtra("key","m");
                intent.putExtra("staff",arrStaff2.get(i));
                startActivity(intent);
            }

        }

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
        //Creating the instance of PopupMenu
        final PopupMenu popup = new PopupMenu(MainForManagerActivity.this, view);
        //Inflating the Popup using xml file
        popup.getMenuInflater()
                .inflate(R.menu.popup_long_click_staff, popup.getMenu());

        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId())
                    {
                        case R.id.edit:
                            initDialogEdit(arrStaff.get(i));
                            editStaff.show();
                            break;
                        case R.id.delete:
                            String id = arrStaff.get(i).getId();
                            String query = "DELETE FROM `nhanvien` WHERE `idNhanVien` = '"+id+"'";
                            Singleton.Instance().getmSocket().emit(CLIENT_SEND_REQUEST_DELETE_STAFF,query);
                            break;
                    }
                return true;
            }
        });

        popup.show(); //showing popup menu
        return true;
    }

    private void initDialogEdit(Staff staff) {
        editStaff = new Dialog(this,android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth);
        editStaff.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        editStaff.setContentView(R.layout.dialog_edit_info_staff);
        editStaff.setCancelable(false);

        final EditText edtId = editStaff.findViewById(R.id.edtIdStaffEditStaff);
        final EditText edtName = editStaff.findViewById(R.id.edtNameStaffEditStaff);
        final EditText edtAddress = editStaff.findViewById(R.id.edtAddressStaffEditStaff);
        final EditText edtPhone = editStaff.findViewById(R.id.edtPhoneStaffEditStaff);
        final EditText edtSalary = editStaff.findViewById(R.id.edtSalaryStaffEditStaff);
        final EditText edtUser = editStaff.findViewById(R.id.edtUserStaffEditStaff);
        final EditText edtPassword = editStaff.findViewById(R.id.edtPassStaffEditStaff);
        final EditText edtDateOfBirth = editStaff.findViewById(R.id.edtDateBirthStaffEditStaff);
        final EditText edtDateStart = editStaff.findViewById(R.id.edtDateStartStaffEditStaff);
        final RadioGroup groupSex = editStaff.findViewById(R.id.groupSexEditStaff);
        final Button btnDone = editStaff.findViewById(R.id.btnDoneEditStaff);
        final Button btnExit = editStaff.findViewById(R.id.btnExitEditStaff);
        RadioButton radioButton_male = editStaff.findViewById(R.id.radioButton_male_EditStaff);
        RadioButton radioButton_female = editStaff.findViewById(R.id.radioButton_female_EditStaff);
        final RadioGroup groupPosition = editStaff.findViewById(R.id.groupPositionEditStaff);
        RadioButton radioButton_BB = editStaff.findViewById(R.id.radioButton_BB_EditStaff);
        RadioButton radioButton_QL = editStaff.findViewById(R.id.radioButton_QL_EditStaff);
        ImageView imAvatar = editStaff.findViewById(R.id.imAvatarEditStaff);

        edtId.setText(staff.getId());
        edtName.setText(staff.getName());
        edtAddress.setText(staff.getAddress());
        edtPhone.setText(staff.getPhone());
        edtSalary.setText(staff.getSalary());
        String[] str = staff.getUserPass().split("-");
        edtUser.setText(str[0]);
        edtPassword.setText(str[1]);
        String[] arr = staff.getDateOfBirth().split("T");
        String[] a = arr[0].split("-");
        edtDateOfBirth.setText((Integer.parseInt(a[2])+1) + "-" + a[1] +"-"+ a[0]);
        String[] arr1 = staff.getDateStart().split("T");
        String[] b = arr1[0].split("-");
        edtDateStart.setText((Integer.parseInt(b[2])+1) + "-" + b[1] +"-"+ b[0]);
        if (staff.getSex().equalsIgnoreCase("Nam"))
        {
            radioButton_male.setChecked(true);
            radioButton_female.setChecked(false);
        }
        else {
            radioButton_female.setChecked(true);
            radioButton_male.setChecked(false);
        }

        if (staff.getPosition().equalsIgnoreCase("BB"))
        {
            radioButton_BB.setChecked(true);
            radioButton_QL.setChecked(false);
        }
        else {
            radioButton_BB.setChecked(false);
            radioButton_QL.setChecked(true);
        }
        Glide.with(this).load(Constants.PORT+staff.getImage()).into(imAvatar);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnExit.startAnimation(animationButton);
                editStaff.dismiss();
            }
        });


        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnDone.startAnimation(animationButton);

                RadioButton radioSexButton;
                RadioButton radioPositionButton;
                String id = edtId.getText().toString();
                String name = edtName.getText().toString();
                String address = edtAddress.getText().toString();
                String phone = edtPhone.getText().toString();
                String salary = edtSalary.getText().toString();
                String user = edtUser.getText().toString();
                String pass = edtPassword.getText().toString();
                String birth = edtDateOfBirth.getText().toString();
                String dateStart = edtDateStart.getText().toString();

                String[] a1 = birth.trim().split("-");
                String dateBirth = a1[2]+"-"+a1[1]+"-"+a1[0];
                String[] b1 = dateStart.trim().split("-");
                String dateStart1 = b1[2]+"-"+b1[1]+"-"+b1[0];
                int isCheckedSex = groupSex.getCheckedRadioButtonId();
                radioSexButton = editStaff.findViewById(isCheckedSex);
                String sex = radioSexButton.getText().toString();
                int isCheckedPosition = groupPosition.getCheckedRadioButtonId();
                radioPositionButton = editStaff.findViewById(isCheckedPosition);
                String position = radioPositionButton.getText().toString();

                final String query = "UPDATE `nhanvien` SET `tenNhanVien`= '"+name+"',`gioiTinh`= '"+sex+"',`ngaySinh`= '"+dateBirth+"',`queQuan`= '"+address+"',`soDienThoai`= '"+phone+"',`chucVu`= '"+position+"',`ngayVao`= '"+dateStart1+"',`luongNgay`= '"+salary+"',`userPass`= '"+user+"-"+pass+"' WHERE `idNhanVien`= '"+id+"'";

                Singleton.Instance().getmSocket().emit(CLIENT_SEND_REQUEST_EDIT_STAFF,query);
                editStaff.dismiss();
            }
        });
    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Singleton.Instance().getmSocket().emit(LOG_OUT,user.getId());
    }

    @Override
    public void onBackPressed() {
        initDialogQuitApp();
        dilogQuitApp.show();
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
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)){
            adap.getFilter().filter("");
            lvStaff.clearTextFilter();
        }
//        else if (newText.equalsIgnoreCase("a")){
//            ArrayList<Staff>arrStaffT =new ArrayList<>();
//            arrStaffT.clear();
//            for (Staff staff:arrStaff){
//                if (staff.getName().contentEquals("a")){
//                    arrStaffT.add(staff);
//                }
//            }
//            adap =new ListviewStaffAdapter(MainForManagerActivity.this,R.layout.item_listview_staff,arrStaffT);
//            lvStaff.setAdapter(adap);
//            adap.notifyDataSetChanged();
//        }
        return true;
    }
}
