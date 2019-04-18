package com.example.anthithanhtam.quanlynhahang.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Position implements Serializable {

    @SerializedName("IdChucVu")
    @Expose
    private String idChucVu;
    @SerializedName("TenChucVu")
    @Expose
    private String tenChucVu;

    /**
     * No args constructor for use in serialization
     *
     */
    public Position() {
    }

    /**
     *
     * @param tenChucVu
     * @param idChucVu
     */
    public Position(String idChucVu, String tenChucVu) {
        super();
        this.idChucVu = idChucVu;
        this.tenChucVu = tenChucVu;
    }

    public String getIdChucVu() {
        return idChucVu;
    }

    public void setIdChucVu(String idChucVu) {
        this.idChucVu = idChucVu;
    }

    public String getTenChucVu() {
        return tenChucVu;
    }

    public void setTenChucVu(String tenChucVu) {
        this.tenChucVu = tenChucVu;
    }

}
