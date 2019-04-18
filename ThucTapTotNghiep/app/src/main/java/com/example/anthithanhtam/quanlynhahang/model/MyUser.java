package com.example.anthithanhtam.quanlynhahang.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MyUser implements Serializable {

    @SerializedName("IdNguoiDung")
    @Expose
    private String idNguoiDung;
    @SerializedName("TaiKhoanNguoiDung")
    @Expose
    private String taiKhoanNguoiDung;
    @SerializedName("MatKhauNguoiDung")
    @Expose
    private String matKhauNguoiDung;
    @SerializedName("TenNguoiDung")
    @Expose
    private String tenNguoiDung;
    @SerializedName("DiaChi")
    @Expose
    private String diaChi;
    @SerializedName("SoDienThoai")
    @Expose
    private String soDienThoai;
    @SerializedName("DiaChiEmail")
    @Expose
    private String diaChiEmail;
    @SerializedName("AnhDaiDien")
    @Expose
    private String anhDaiDien;
    @SerializedName("GhiChu")
    @Expose
    private String ghiChu;
    @SerializedName("ChucVu")
    @Expose
    private String chucVu;
    @SerializedName("SoLanDatHang")
    @Expose
    private String soLanDatHang;
    @SerializedName("NgayTao")
    @Expose
    private String ngayTao;

    /**
     * No args constructor for use in serialization
     *
     */
    public MyUser() {
    }

    /**
     *
     * @param diaChiEmail
     * @param matKhauNguoiDung
     * @param soDienThoai
     * @param diaChi
     * @param ngayTao
     * @param taiKhoanNguoiDung
     * @param anhDaiDien
     * @param tenNguoiDung
     * @param idNguoiDung
     * @param chucVu
     * @param soLanDatHang
     * @param ghiChu
     */
    public MyUser(String idNguoiDung, String taiKhoanNguoiDung, String matKhauNguoiDung, String tenNguoiDung, String diaChi, String soDienThoai, String diaChiEmail, String anhDaiDien, String ghiChu, String chucVu, String soLanDatHang, String ngayTao) {
        super();
        this.idNguoiDung = idNguoiDung;
        this.taiKhoanNguoiDung = taiKhoanNguoiDung;
        this.matKhauNguoiDung = matKhauNguoiDung;
        this.tenNguoiDung = tenNguoiDung;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.diaChiEmail = diaChiEmail;
        this.anhDaiDien = anhDaiDien;
        this.ghiChu = ghiChu;
        this.chucVu = chucVu;
        this.soLanDatHang = soLanDatHang;
        this.ngayTao = ngayTao;
    }

    public String getIdNguoiDung() {
        return idNguoiDung;
    }

    public void setIdNguoiDung(String idNguoiDung) {
        this.idNguoiDung = idNguoiDung;
    }

    public String getTaiKhoanNguoiDung() {
        return taiKhoanNguoiDung;
    }

    public void setTaiKhoanNguoiDung(String taiKhoanNguoiDung) {
        this.taiKhoanNguoiDung = taiKhoanNguoiDung;
    }

    public String getMatKhauNguoiDung() {
        return matKhauNguoiDung;
    }

    public void setMatKhauNguoiDung(String matKhauNguoiDung) {
        this.matKhauNguoiDung = matKhauNguoiDung;
    }

    public String getTenNguoiDung() {
        return tenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        this.tenNguoiDung = tenNguoiDung;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getDiaChiEmail() {
        return diaChiEmail;
    }

    public void setDiaChiEmail(String diaChiEmail) {
        this.diaChiEmail = diaChiEmail;
    }

    public String getAnhDaiDien() {
        return anhDaiDien;
    }

    public void setAnhDaiDien(String anhDaiDien) {
        this.anhDaiDien = anhDaiDien;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getSoLanDatHang() {
        return soLanDatHang;
    }

    public void setSoLanDatHang(String soLanDatHang) {
        this.soLanDatHang = soLanDatHang;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

}