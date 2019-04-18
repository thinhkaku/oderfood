package com.example.anthithanhtam.quanlynhahang.presenter;

public interface ImplFragmentMenu {

    void editMyItem(String id, String count, String status);
    void deleteMyItem(String id);
    void editTable(String number, String numberPeople, String checkTb, String note, String timeCheck);
    void insertMyItem(String menuId, String numberTable, String count, String createAt);
    void getListMenu(String type);
    void getDataType();
}
