package com.example.anthithanhtam.quanlynhahang.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.anthithanhtam.quanlynhahang.R;
import com.example.anthithanhtam.quanlynhahang.activity.ManagerActivity;
import com.example.anthithanhtam.quanlynhahang.adapter.BillManagerAdapter;
import com.example.anthithanhtam.quanlynhahang.model.Bill;
import com.example.anthithanhtam.quanlynhahang.presenter.PresenterQuanLyHoaDon;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragmentQuanLyHoaDon extends BaseFragment implements ViewFragmentQuanLyHoaDon, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.recyclerBill)
    RecyclerView recyclerBill;
    Unbinder unbinder;
    @BindView(R.id.swiperefreshBill)
    SwipeRefreshLayout swiperefreshBill;
    private PresenterQuanLyHoaDon presenterQuanLyHoaDon;
    private BillManagerAdapter billManagerAdapter;
    private List<Bill> mListBill;
    private ManagerActivity mManagerActivity;

    @Override
    protected void initView() {
        mManagerActivity= (ManagerActivity) mActivity;
        mListBill = new ArrayList<>();
        presenterQuanLyHoaDon = new PresenterQuanLyHoaDon(soService, this);
        getData();
        billManagerAdapter = new BillManagerAdapter(mListBill, mManagerActivity, soService);
        recyclerBill.setLayoutManager(new LinearLayoutManager(mActivity));
        recyclerBill.setHasFixedSize(true);
        recyclerBill.setAdapter(billManagerAdapter);
        swiperefreshBill.setOnRefreshListener(this);
    }

    private void getData(){
        mManagerActivity.getLnProgressBar().setVisibility(View.VISIBLE);
        presenterQuanLyHoaDon.getDataBill();
    }

    @Override
    protected int layoutID() {
        return R.layout.layout_quan_ly_hoa_don_fragment;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return false;
    }

    @Override
    public void getDataBill(List<Bill> listBill) {
        mListBill.clear();
        mListBill.addAll(listBill);
        billManagerAdapter.notifyDataSetChanged();
        swiperefreshBill.setRefreshing(false);
        mManagerActivity.getLnProgressBar().setVisibility(View.INVISIBLE);
    }

    @Override
    public void onError() {
        mManagerActivity.getLnProgressBar().setVisibility(View.INVISIBLE);
    }


    @Override
    public void onRefresh() {
        getData();
    }
}
