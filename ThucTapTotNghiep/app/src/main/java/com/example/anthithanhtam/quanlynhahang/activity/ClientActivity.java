package com.example.anthithanhtam.quanlynhahang.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.anthithanhtam.quanlynhahang.R;
import com.example.anthithanhtam.quanlynhahang.constant.Constant;
import com.example.anthithanhtam.quanlynhahang.constant.Utils;
import com.example.anthithanhtam.quanlynhahang.fragment.FragmentBill;
import com.example.anthithanhtam.quanlynhahang.fragment.FragmentMainClient;
import com.example.anthithanhtam.quanlynhahang.fragment.FragmentMenu;
import com.example.anthithanhtam.quanlynhahang.model.Employee;
import com.example.anthithanhtam.quanlynhahang.sharepreferences.ShareConstand;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ClientActivity extends BaseActivity {
    @BindView(R.id.toolbarA)
    Toolbar toolbarA;
    @BindView(R.id.frameClient)
    FrameLayout frameClient;
    @BindView(R.id.lnPRClient)
    LinearLayout lnPRClient;
    @BindView(R.id.imgHinhDaiDien)
    CircleImageView imgHinhDaiDien;
    @BindView(R.id.txtTen)
    TextView txtTen;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    private FragmentMainClient fragmentMainClient;
    private FragmentBill fragmentBill;
    private FragmentMenu fragmentMenu;
    private Employee employee;

    public LinearLayout getLnPRClient() {
        return lnPRClient;
    }

    @Override
    protected int layoutID() {
        return R.layout.activity_client;
    }

    @Override
    protected void initView() {
        employee = ShareConstand.getEmployee(this);
        if (employee != null) {
            txtTen.setText(employee.getTenNhanVien());
            Glide.with(this).load(Constant.PORT_IMAGE + employee.getAnh()).into(imgHinhDaiDien);
        }
        initFragment();
        toolbarA = findViewById(R.id.toolbarA);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbarA, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void initFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        fragmentMainClient = new FragmentMainClient();
        fragmentBill = new FragmentBill();
        fragmentMenu = new FragmentMenu();
        transaction.add(R.id.frameClient, fragmentMainClient);
        transaction.add(R.id.frameClient, fragmentBill);
        transaction.add(R.id.frameClient, fragmentMenu);
        transaction.commit();
        switchFragment(fragmentMainClient);
    }

    public void switchFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.hide(fragmentMainClient);
        transaction.hide(fragmentBill);
        transaction.hide(fragmentMenu);
        transaction.show(fragment);
        transaction.commit();
    }

    @Override
    protected void addEvent() {
    }

    public FragmentMainClient getFragmentMainClient() {
        toolbarA.setTitle(getString(R.string.select_table));
        fragmentMainClient.getData();
        return fragmentMainClient;
    }

    public FragmentBill getFragmentBill() {
        ShareConstand.setActionBill(this, "2");
        toolbarA.setTitle(getString(R.string.bill));
        fragmentBill.moveData();
        return fragmentBill;
    }

    public FragmentMenu getFragmentMenu() {
        ShareConstand.setActionMenu(this, "2");
        fragmentMenu.getBtnGoToBill().setVisibility(View.VISIBLE);
        toolbarA.setTitle(getString(R.string.menu));
        fragmentMenu.reloadData();
        return fragmentMenu;
    }

    @Override
    public void onBackPressed() {
        if (fragmentMainClient.isVisible()) {
            Utils.dilogQuitApp(this);
        } else {
            toolbarA.setTitle(getString(R.string.select_table));
            fragmentMainClient.getData();
            switchFragment(fragmentMainClient);
        }
    }





    @OnClick({R.id.btnThucDon, R.id.btnHoaDonThanhToan, R.id.btnBangTin, R.id.btnThongTinCaNhan, R.id.btnDangXuat})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnDangXuat:
                drawerLayout.closeDrawers();
                finish();
                break;
            case R.id.btnThucDon:
                if (!fragmentMenu.isVisible() && checkInternet) {
                    fragmentMenu.getBtnGoToBill().setVisibility(View.INVISIBLE);
                    ShareConstand.getInstance(this).setNumpeo("0-0");
                    fragmentMenu.reloadData();
                    switchFragment(fragmentMenu);
                }
                drawerLayout.closeDrawers();
                break;
            case R.id.btnBangTin:
                drawerLayout.closeDrawers();
                break;
            case R.id.btnHoaDonThanhToan:
                drawerLayout.closeDrawers();
                break;
            case R.id.btnThongTinCaNhan:
                Utils.intiDilogDetailEmploy(employee, this);
                drawerLayout.closeDrawers();
                break;
        }
    }
}