package com.example.anthithanhtam.quanlynhahang.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BillDetail {

    @SerializedName("bill_detail_id")
    @Expose
    private String billDetailId;
    @SerializedName("menu_name")
    @Expose
    private String menuName;
    @SerializedName("menu_image")
    @Expose
    private String menuImage;
    @SerializedName("menu_price")
    @Expose
    private String menuPrice;
    @SerializedName("count")
    @Expose
    private String count;
    @SerializedName("bill_detail_create_date")
    @Expose
    private String billDetailCreateDate;

    /**
     * No args constructor for use in serialization
     *
     */
    public BillDetail() {
    }

    /**
     *
     * @param count
     * @param menuImage
     * @param billDetailCreateDate
     * @param menuPrice
     * @param billDetailId
     * @param menuName
     */
    public BillDetail(String billDetailId, String menuName, String menuImage, String menuPrice, String count, String billDetailCreateDate) {
        super();
        this.billDetailId = billDetailId;
        this.menuName = menuName;
        this.menuImage = menuImage;
        this.menuPrice = menuPrice;
        this.count = count;
        this.billDetailCreateDate = billDetailCreateDate;
    }

    public String getBillDetailId() {
        return billDetailId;
    }

    public void setBillDetailId(String billDetailId) {
        this.billDetailId = billDetailId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuImage() {
        return menuImage;
    }

    public void setMenuImage(String menuImage) {
        this.menuImage = menuImage;
    }

    public String getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(String menuPrice) {
        this.menuPrice = menuPrice;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getBillDetailCreateDate() {
        return billDetailCreateDate;
    }

    public void setBillDetailCreateDate(String billDetailCreateDate) {
        this.billDetailCreateDate = billDetailCreateDate;
    }

}