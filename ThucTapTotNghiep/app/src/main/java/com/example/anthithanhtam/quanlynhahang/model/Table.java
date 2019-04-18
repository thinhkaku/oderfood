package com.example.anthithanhtam.quanlynhahang.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Table extends RealmObject {
    @PrimaryKey
    private int number;
    private String position;
    private  int numPeople;
    private int numberOfChair;
    private int check;
    private String note;
    public Table(int number, String position, int numPeople, int numberOfChair, int check, String note) {
        this.number = number;
        this.position = position;
        this.numPeople = numPeople;
        this.numberOfChair = numberOfChair;
        this.check = check;
        this.note = note;
    }
    public Table() {
    }

    public int getNumPeople() {
        return numPeople;
    }

    public void setNumPeople(int numPeople) {
        this.numPeople = numPeople;
    }

    public int getNumber() {
        return number;
    }

    public String getPosition() {
        return position;
    }

    public int getNumberOfChair() {
        return numberOfChair;
    }

    public int getCheck() {
        return check;
    }

    public String getNote() {
        return note;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setNumberOfChair(int numberOfChair) {
        this.numberOfChair = numberOfChair;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
