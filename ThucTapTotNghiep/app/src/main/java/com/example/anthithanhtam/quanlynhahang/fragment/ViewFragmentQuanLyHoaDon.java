package com.example.anthithanhtam.quanlynhahang.fragment;

import com.example.anthithanhtam.quanlynhahang.model.Bill;

import java.util.List;

public interface ViewFragmentQuanLyHoaDon {
    void getDataBill(List<Bill>listBill);
    void onError();
}
