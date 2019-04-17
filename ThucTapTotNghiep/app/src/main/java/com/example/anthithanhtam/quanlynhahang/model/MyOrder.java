package com.example.anthithanhtam.quanlynhahang.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MyOrder implements Serializable {

    @SerializedName("IDMon")
    @Expose
    private String iDMon;
    @SerializedName("IDKhachHang")
    @Expose
    private String iDKhachHang;
    @SerializedName("IDMenu")
    @Expose
    private String iDMenu;
    @SerializedName("SoLuongMon")
    @Expose
    private String soLuongMon;
    @SerializedName("ThoiGian")
    @Expose
    private String thoiGian;
    @SerializedName("TrangThai")
    @Expose
    private String trangThai;

    /**
     * No args constructor for use in serialization
     *
     */
    public MyOrder() {
    }

    /**
     *
     * @param iDMenu
     * @param iDMon
     * @param trangThai
     * @param iDKhachHang
     * @param soLuongMon
     * @param thoiGian
     */
    public MyOrder(String iDMon, String iDKhachHang, String iDMenu, String soLuongMon, String thoiGian, String trangThai) {
        super();
        this.iDMon = iDMon;
        this.iDKhachHang = iDKhachHang;
        this.iDMenu = iDMenu;
        this.soLuongMon = soLuongMon;
        this.thoiGian = thoiGian;
        this.trangThai = trangThai;
    }

    public String getIDMon() {
        return iDMon;
    }

    public void setIDMon(String iDMon) {
        this.iDMon = iDMon;
    }

    public String getIDKhachHang() {
        return iDKhachHang;
    }

    public void setIDKhachHang(String iDKhachHang) {
        this.iDKhachHang = iDKhachHang;
    }

    public String getIDMenu() {
        return iDMenu;
    }

    public void setIDMenu(String iDMenu) {
        this.iDMenu = iDMenu;
    }

    public String getSoLuongMon() {
        return soLuongMon;
    }

    public void setSoLuongMon(String soLuongMon) {
        this.soLuongMon = soLuongMon;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

}
