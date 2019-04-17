package com.example.anthithanhtam.quanlynhahang.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OrderDetail implements Serializable {

    @SerializedName("idhoadon")
    @Expose
    private String idhoadon;
    @SerializedName("banso")
    @Expose
    private String banso;
    @SerializedName("songuoi")
    @Expose
    private String songuoi;
    @SerializedName("thoigian")
    @Expose
    private String thoigian;
    @SerializedName("tongtien")
    @Expose
    private String tongtien;
    @SerializedName("tenmon")
    @Expose
    private String tenmon;

    /**
     * No args constructor for use in serialization
     *
     */
    public OrderDetail() {
    }

    /**
     *
     * @param songuoi
     * @param tenmon
     * @param idhoadon
     * @param banso
     * @param tongtien
     * @param thoigian
     */
    public OrderDetail(String idhoadon, String banso, String songuoi, String thoigian, String tongtien, String tenmon) {
        super();
        this.idhoadon = idhoadon;
        this.banso = banso;
        this.songuoi = songuoi;
        this.thoigian = thoigian;
        this.tongtien = tongtien;
        this.tenmon = tenmon;
    }

    public String getIdhoadon() {
        return idhoadon;
    }

    public void setIdhoadon(String idhoadon) {
        this.idhoadon = idhoadon;
    }

    public String getBanso() {
        return banso;
    }

    public void setBanso(String banso) {
        this.banso = banso;
    }

    public String getSonguoi() {
        return songuoi;
    }

    public void setSonguoi(String songuoi) {
        this.songuoi = songuoi;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public String getTongtien() {
        return tongtien;
    }

    public void setTongtien(String tongtien) {
        this.tongtien = tongtien;
    }

    public String getTenmon() {
        return tenmon;
    }

    public void setTenmon(String tenmon) {
        this.tenmon = tenmon;
    }

}
