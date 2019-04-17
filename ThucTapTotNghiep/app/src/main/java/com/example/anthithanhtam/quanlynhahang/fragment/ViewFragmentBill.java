package com.example.anthithanhtam.quanlynhahang.fragment;

import com.example.anthithanhtam.quanlynhahang.model.MyItem;

import java.util.List;

public interface ViewFragmentBill {
    void getListMyItem(List<MyItem>listMyItem);
    void deleteMyItem(String message);
    void onSuccess();
    void onSuccessInsertBill(String id);
    void onSuccessInsertBillDetail();
    void error();
}
