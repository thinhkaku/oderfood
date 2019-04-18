package com.example.anthithanhtam.quanlynhahang.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Employee {

    @SerializedName("MaNhanVien")
    @Expose
    private String maNhanVien;
    @SerializedName("TaiKhoan")
    @Expose
    private String taiKhoan;
    @SerializedName("Pass")
    @Expose
    private String pass;
    @SerializedName("TenNhanVien")
    @Expose
    private String tenNhanVien;
    @SerializedName("Anh")
    @Expose
    private String anh;
    @SerializedName("SoDienThoai")
    @Expose
    private String soDienThoai;
    @SerializedName("DiaChi")
    @Expose
    private String diaChi;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("NgaySinh")
    @Expose
    private String ngaySinh;
    @SerializedName("ChucVu")
    @Expose
    private String chucVu;
    @SerializedName("MucLuong")
    @Expose
    private String mucLuong;
    @SerializedName("ThoiGianBatDau")
    @Expose
    private String thoiGianBatDau;
    @SerializedName("ThoiGianKetThuc")
    @Expose
    private String thoiGianKetThuc;

    /**
     * No args constructor for use in serialization
     *
     */
    public Employee() {
    }

    /**
     *
     * @param anh
     * @param mucLuong
     * @param soDienThoai
     * @param email
     * @param ngaySinh
     * @param diaChi
     * @param maNhanVien
     * @param thoiGianBatDau
     * @param thoiGianKetThuc
     * @param chucVu
     * @param tenNhanVien
     * @param pass
     * @param taiKhoan
     */
    public Employee(String maNhanVien, String taiKhoan, String pass, String tenNhanVien, String anh, String soDienThoai, String diaChi, String email, String ngaySinh, String chucVu, String mucLuong, String thoiGianBatDau, String thoiGianKetThuc) {
        super();
        this.maNhanVien = maNhanVien;
        this.taiKhoan = taiKhoan;
        this.pass = pass;
        this.tenNhanVien = tenNhanVien;
        this.anh = anh;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.email = email;
        this.ngaySinh = ngaySinh;
        this.chucVu = chucVu;
        this.mucLuong = mucLuong;
        this.thoiGianBatDau = thoiGianBatDau;
        this.thoiGianKetThuc = thoiGianKetThuc;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getMucLuong() {
        return mucLuong;
    }

    public void setMucLuong(String mucLuong) {
        this.mucLuong = mucLuong;
    }

    public String getThoiGianBatDau() {
        return thoiGianBatDau;
    }

    public void setThoiGianBatDau(String thoiGianBatDau) {
        this.thoiGianBatDau = thoiGianBatDau;
    }

    public String getThoiGianKetThuc() {
        return thoiGianKetThuc;
    }

    public void setThoiGianKetThuc(String thoiGianKetThuc) {
        this.thoiGianKetThuc = thoiGianKetThuc;
    }

}