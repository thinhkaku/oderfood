package com.example.anthithanhtam.quanlynhahang.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Finance implements Serializable {

    @SerializedName("MaTaiChinh")
    @Expose
    private String maTaiChinh;
    @SerializedName("GhiChu")
    @Expose
    private String ghiChu;
    @SerializedName("IDDonHang")
    @Expose
    private Object iDDonHang;
    @SerializedName("ChiTra")
    @Expose
    private String chiTra;
    @SerializedName("TrangThai")
    @Expose
    private String trangThai;
    @SerializedName("NgayTao")
    @Expose
    private String ngayTao;

    /**
     * No args constructor for use in serialization
     *
     */
    public Finance() {
    }

    /**
     *
     * @param trangThai
     * @param chiTra
     * @param maTaiChinh
     * @param iDDonHang
     * @param ngayTao
     * @param ghiChu
     */
    public Finance(String maTaiChinh, String ghiChu, Object iDDonHang, String chiTra, String trangThai, String ngayTao) {
        super();
        this.maTaiChinh = maTaiChinh;
        this.ghiChu = ghiChu;
        this.iDDonHang = iDDonHang;
        this.chiTra = chiTra;
        this.trangThai = trangThai;
        this.ngayTao = ngayTao;
    }

    public String getMaTaiChinh() {
        return maTaiChinh;
    }

    public void setMaTaiChinh(String maTaiChinh) {
        this.maTaiChinh = maTaiChinh;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Object getIDDonHang() {
        return iDDonHang;
    }

    public void setIDDonHang(Object iDDonHang) {
        this.iDDonHang = iDDonHang;
    }

    public String getChiTra() {
        return chiTra;
    }

    public void setChiTra(String chiTra) {
        this.chiTra = chiTra;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

}
