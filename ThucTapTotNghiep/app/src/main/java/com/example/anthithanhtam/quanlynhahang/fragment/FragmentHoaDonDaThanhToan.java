package com.example.anthithanhtam.quanlynhahang.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import com.example.anthithanhtam.quanlynhahang.R;
import com.example.anthithanhtam.quanlynhahang.activity.ClientActivity;
import com.example.anthithanhtam.quanlynhahang.activity.ManagerActivity;
import com.example.anthithanhtam.quanlynhahang.adapter.BillClientAdapter;
import com.example.anthithanhtam.quanlynhahang.adapter.BillManagerAdapter;
import com.example.anthithanhtam.quanlynhahang.model.Bill;
import com.example.anthithanhtam.quanlynhahang.presenter.PresenterQuanLyHoaDon;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

public class FragmentHoaDonDaThanhToan extends BaseFragment implements ViewFragmentQuanLyHoaDon, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.recyclerBill)
    RecyclerView recyclerBill;
    Unbinder unbinder;
    @BindView(R.id.swiperefreshBill)
    SwipeRefreshLayout swiperefreshBill;
    private PresenterQuanLyHoaDon presenterQuanLyHoaDon;
    private BillClientAdapter billClientAdapter;
    private List<Bill> mListBill;
    private ClientActivity clientActivity;

    @Override
    protected void initView() {
        clientActivity= (ClientActivity) mActivity;
        mListBill = new ArrayList<>();
        presenterQuanLyHoaDon = new PresenterQuanLyHoaDon(soService, this);
        billClientAdapter = new BillClientAdapter(mListBill, clientActivity, soService);
        recyclerBill.setLayoutManager(new LinearLayoutManager(mActivity));
        recyclerBill.setHasFixedSize(true);
        recyclerBill.setAdapter(billClientAdapter);
        swiperefreshBill.setOnRefreshListener(this);
        getData();
    }

    public void getData(){
        clientActivity.getLnPRClient().setVisibility(View.VISIBLE);
        if (clientActivity.getEmployee()!=null){
            presenterQuanLyHoaDon.getDataBillEmployee(clientActivity.getEmployee().getMaNhanVien());
        }

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
        billClientAdapter.notifyDataSetChanged();
        swiperefreshBill.setRefreshing(false);
        clientActivity.getLnPRClient().setVisibility(View.INVISIBLE);
    }

    @Override
    public void onError() {
        clientActivity.getLnPRClient().setVisibility(View.INVISIBLE);
    }


    @Override
    public void onRefresh() {
        getData();
    }
}
