package com.example.anthithanhtam.quanlynhahang.model;

import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class TotalMoney {

    @SerializedName("total_money")
    @Expose
    private String totalMoney;

    /**
     * No args constructor for use in serialization
     *
     */
    public TotalMoney() {
    }

    /**
     *
     * @param totalMoney
     */
    public TotalMoney(String totalMoney) {
        super();
        this.totalMoney = totalMoney;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

}
