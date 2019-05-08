package com.example.anthithanhtam.quanlynhahang.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TotalPeople {

    @SerializedName("total_people")
    @Expose
    private String totalPeople;

    /**
     * No args constructor for use in serialization
     *
     */
    public TotalPeople() {
    }

    /**
     *
     * @param totalPeople
     */
    public TotalPeople(String totalPeople) {
        super();
        this.totalPeople = totalPeople;
    }

    public String getTotalPeople() {
        return totalPeople;
    }

    public void setTotalPeople(String totalPeople) {
        this.totalPeople = totalPeople;
    }

}