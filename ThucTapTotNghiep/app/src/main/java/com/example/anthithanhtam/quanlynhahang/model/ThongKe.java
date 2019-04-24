package com.example.anthithanhtam.quanlynhahang.model;

public class ThongKe {
    private int soKhach;
    private float thuNhap;

    public ThongKe(int soKhach, float thuNhap) {
        this.soKhach = soKhach;
        this.thuNhap = thuNhap;
    }

    public int getSoKhach() {
        return soKhach;
    }

    public void setSoKhach(int soKhach) {
        this.soKhach = soKhach;
    }

    public float getThuNhap() {
        return thuNhap;
    }

    public void setThuNhap(float thuNhap) {
        this.thuNhap = thuNhap;
    }
}
