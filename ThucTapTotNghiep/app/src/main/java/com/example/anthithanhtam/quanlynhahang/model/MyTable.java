package com.example.anthithanhtam.quanlynhahang.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MyTable implements Serializable {

    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("numPeople")
    @Expose
    private String numPeople;
    @SerializedName("check")
    @Expose
    private String check;
    @SerializedName("note")
    @Expose
    private String note;

    @SerializedName("timeCheck")
    @Expose
    private String timeCheck;

    /**
     * No args constructor for use in serialization
     *
     */
    public MyTable() {
    }

    /**
     *
     * @param numPeople
     * @param check
     * @param number
     * @param note
     * @param timeCheck
     */
    public MyTable(String number, String numPeople, String check, String note, String timeCheck) {
        super();
        this.number = number;
        this.numPeople = numPeople;
        this.check = check;
        this.note = note;
        this.timeCheck = timeCheck;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumPeople() {
        return numPeople;
    }

    public void setNumPeople(String numPeople) {
        this.numPeople = numPeople;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTimeCheck() {
        return timeCheck;
    }

    public void setTimeCheck(String timeCheck) {
        this.timeCheck = timeCheck;
    }
}
