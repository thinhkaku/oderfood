package com.example.anthithanhtam.quanlynhahang.presenter;

public interface ImplFragmentBill {
    void getListMyItem(String numberTable);
    void editMyItem(String id, String count, String status);
    void deleteMyItem(String id);
    void editMyTable(String number, String numberPeople, String checkTb, String note, String timeCheck);
    void insertBill(String bill_number_tb, String bill_number_people, String employee_id, String bill_create_date);
    void insertBillDetail(String bill_id, String menu_id, String count, String bill_detail_create_date);
}
