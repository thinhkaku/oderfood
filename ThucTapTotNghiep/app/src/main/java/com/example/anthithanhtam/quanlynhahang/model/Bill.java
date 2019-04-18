package com.example.anthithanhtam.quanlynhahang.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bill {

    @SerializedName("bill_id")
    @Expose
    private String billId;
    @SerializedName("bill_number_tb")
    @Expose
    private String billNumberTb;
    @SerializedName("bill_number_people")
    @Expose
    private String billNumberPeople;
    @SerializedName("employee_id")
    @Expose
    private String employeeId;
    @SerializedName("bill_create_date")
    @Expose
    private String billCreateDate;

    /**
     * No args constructor for use in serialization
     *
     */
    public Bill() {
    }

    /**
     *
     * @param employeeId
     * @param billCreateDate
     * @param billNumberPeople
     * @param billId
     * @param billNumberTb
     */
    public Bill(String billId, String billNumberTb, String billNumberPeople, String employeeId, String billCreateDate) {
        super();
        this.billId = billId;
        this.billNumberTb = billNumberTb;
        this.billNumberPeople = billNumberPeople;
        this.employeeId = employeeId;
        this.billCreateDate = billCreateDate;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getBillNumberTb() {
        return billNumberTb;
    }

    public void setBillNumberTb(String billNumberTb) {
        this.billNumberTb = billNumberTb;
    }

    public String getBillNumberPeople() {
        return billNumberPeople;
    }

    public void setBillNumberPeople(String billNumberPeople) {
        this.billNumberPeople = billNumberPeople;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getBillCreateDate() {
        return billCreateDate;
    }

    public void setBillCreateDate(String billCreateDate) {
        this.billCreateDate = billCreateDate;
    }

}
