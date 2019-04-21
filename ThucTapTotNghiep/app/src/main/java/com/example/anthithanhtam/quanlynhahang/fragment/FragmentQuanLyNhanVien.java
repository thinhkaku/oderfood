package com.example.anthithanhtam.quanlynhahang.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.anthithanhtam.quanlynhahang.R;
import com.example.anthithanhtam.quanlynhahang.activity.ManagerActivity;
import com.example.anthithanhtam.quanlynhahang.adapter.EmployeeAdapter;
import com.example.anthithanhtam.quanlynhahang.constant.Utils;
import com.example.anthithanhtam.quanlynhahang.model.Employee;
import com.example.anthithanhtam.quanlynhahang.presenter.PresenterQuanLyNhanVien;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FragmentQuanLyNhanVien extends BaseFragment implements ViewFragmentQuanLyNhanVien, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.recyclerNhanVien)
    RecyclerView recyclerNhanVien;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;
    @BindView(R.id.btnAddEmployee)
    ImageView btnAddEmployee;
    Unbinder unbinder;
    private List<Employee> listEmploy;
    private EmployeeAdapter employeeAdapter;
    private PresenterQuanLyNhanVien presenterQuanLyNhanVien;
    private ManagerActivity mManagerActivity;


    @Override
    protected void initView() {
        mManagerActivity = (ManagerActivity) mActivity;
        listEmploy = new ArrayList<>();
        swiperefresh.setColorSchemeColors(Color.RED);
        presenterQuanLyNhanVien = new PresenterQuanLyNhanVien(this, soService);
        recyclerNhanVien.setLayoutManager(new LinearLayoutManager(mActivity));
        recyclerNhanVien.setHasFixedSize(true);
        employeeAdapter = new EmployeeAdapter(listEmploy, mActivity, presenterQuanLyNhanVien, mManagerActivity);
        recyclerNhanVien.setAdapter(employeeAdapter);
        addData();
        swiperefresh.setOnRefreshListener(this);
    }

    @Override
    protected int layoutID() {
        return R.layout.layout_quan_ly_nhan_vien_fragment;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return false;
    }


    private void addData() {
        mManagerActivity.getLnProgressBar().setVisibility(View.VISIBLE);
        presenterQuanLyNhanVien.getEmployee();
    }


    @Override
    public void getEmployee(List<Employee> listEmployee) {
        swiperefresh.setRefreshing(false);
        listEmploy.clear();
        listEmploy.addAll(listEmployee);
        employeeAdapter.notifyDataSetChanged();
        mManagerActivity.getLnProgressBar().setVisibility(View.GONE);
    }

    @Override
    public void deleteEmployee(String message) {
        if (message.equals("1")) {
            Utils.toastMessage(mManagerActivity, "");
            //Toast.makeText(context, getString(R.string.delete_employee_success), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mActivity, getString(R.string.delete_employee_fail), Toast.LENGTH_SHORT).show();
        }
        mManagerActivity.getLnProgressBar().setVisibility(View.GONE);
        presenterQuanLyNhanVien.getEmployee();

    }

    @Override
    public void editEmployee(String message) {
        if (message.equals("1")) {
            Toast.makeText(mActivity, getString(R.string.edit_employee_success), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mActivity, getString(R.string.edit_employee_fail), Toast.LENGTH_SHORT).show();


        }
        mManagerActivity.getLnProgressBar().setVisibility(View.GONE);
        presenterQuanLyNhanVien.getEmployee();
    }


    @Override
    public void onRefresh() {
        addData();
    }

    @OnClick(R.id.btnAddEmployee)
    public void onViewClicked() {
        FragmentRegister fragmentRegister=FragmentRegister.newInstance(soService,mManagerActivity);
        fragmentRegister.show(getFragmentManager(),"");
    }
}
