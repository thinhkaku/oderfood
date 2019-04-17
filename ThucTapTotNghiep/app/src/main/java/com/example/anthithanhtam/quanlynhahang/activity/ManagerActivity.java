package com.example.anthithanhtam.quanlynhahang.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.anthithanhtam.quanlynhahang.R;
import com.example.anthithanhtam.quanlynhahang.adapter.ManagerPagerAdapter;
import com.example.anthithanhtam.quanlynhahang.constant.Utils;
import com.example.anthithanhtam.quanlynhahang.fragment.FragmentQuanLyBaoCao;
import com.example.anthithanhtam.quanlynhahang.fragment.FragmentQuanLyHoaDon;
import com.example.anthithanhtam.quanlynhahang.fragment.FragmentQuanLyNhanVien;
import com.example.anthithanhtam.quanlynhahang.fragment.FragmentQuanLyThucDon;

import butterknife.BindView;
import butterknife.OnClick;

public class ManagerActivity extends BaseActivity{
    @BindView(R.id.toolbarManager)
    Toolbar toolbarManager;
    @BindView(R.id.vPManager)
    ViewPager vPManager;
    @BindView(R.id.lnPRClient)
    LinearLayout lnPRClient;
    @BindView(R.id.imgHinhDaiDien)
    ImageView imgHinhDaiDien;
    @BindView(R.id.txtTen)
    TextView txtTen;
    @BindView(R.id.drManager)
    DrawerLayout drManager;
    private FragmentQuanLyNhanVien fragmentQuanLyNhanVien;
    private FragmentQuanLyBaoCao fragmentQuanLyBaoCao;
    private FragmentQuanLyHoaDon fragmentQuanLyHoaDon;
    private FragmentQuanLyThucDon fragmentQuanLyThucDon;
    private ManagerPagerAdapter managerPagerAdapter;

    public LinearLayout getLnProgressBar() {
        return lnPRClient;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int layoutID() {
        return R.layout.activity_manager;
    }

    @Override
    protected void initView() {
        toolbarManager.setTitle(getString(R.string.manager_employee));
        setSupportActionBar(toolbarManager);
        initDrawerLayout();
        initFragment();
    }

    private void initDrawerLayout() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drManager, toolbarManager, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drManager.setDrawerListener(toggle);
        toggle.syncState();
        toggle.setHomeAsUpIndicator(R.drawable.background_edt);
    }

    private void initFragment() {
        fragmentQuanLyBaoCao = new FragmentQuanLyBaoCao();
        fragmentQuanLyHoaDon = new FragmentQuanLyHoaDon();
        fragmentQuanLyNhanVien = new FragmentQuanLyNhanVien();
        fragmentQuanLyThucDon = new FragmentQuanLyThucDon();
        managerPagerAdapter = new ManagerPagerAdapter(getSupportFragmentManager());
        managerPagerAdapter.addFragment(fragmentQuanLyNhanVien, R.drawable.add_item);
        managerPagerAdapter.addFragment(fragmentQuanLyThucDon, R.drawable.add_item);
        managerPagerAdapter.addFragment(fragmentQuanLyHoaDon, R.drawable.add_item);
        managerPagerAdapter.addFragment(fragmentQuanLyBaoCao, R.drawable.add_item);
        vPManager.setAdapter(managerPagerAdapter);
        managerPagerAdapter.notifyDataSetChanged();
    }

    @Override
    protected void addEvent() {
        vPManager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i == 0) {
                    toolbarManager.setTitle(getString(R.string.manager_employee));
                } else if (i == 1) {
                    toolbarManager.setTitle(getString(R.string.manager_menu));
                } else if (i == 2) {
                    toolbarManager.setTitle(getString(R.string.manager_bill));
                } else {
                    toolbarManager.setTitle(getString(R.string.manager_report));
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        Utils.dilogQuitApp(this);
    }

    @OnClick({R.id.btnQuanLyNhanVien, R.id.btnQuanLyThucDon, R.id.btnQuanLyHoaDon, R.id.btnQuanLyBaoCao, R.id.btnDangXuat})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnDangXuat:
                drManager.closeDrawers();
                finish();
                break;
            case R.id.btnQuanLyBaoCao:
                vPManager.setCurrentItem(3, false);
                drManager.closeDrawers();
                break;
            case R.id.btnQuanLyHoaDon:
                vPManager.setCurrentItem(2, false);
                drManager.closeDrawers();
                break;
            case R.id.btnQuanLyNhanVien:
                vPManager.setCurrentItem(0, false);
                drManager.closeDrawers();
                break;
            case R.id.btnQuanLyThucDon:
                vPManager.setCurrentItem(1, false);
                drManager.closeDrawers();
                break;
        }
    }
}
