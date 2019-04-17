package com.example.anthithanhtam.quanlynhahang.model;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ItemMenu extends RealmObject implements Serializable{
    @PrimaryKey
    private int id;
    private int group;
    private String name;
    private String price;
    private int image;
    private int count = 0;

    public ItemMenu() {
    }

    public ItemMenu(int id, int group, String name, String price, int image, int count) {
        this.id = id;
        this.group = group;
        this.name = name;
        this.price = price;
        this.image = image;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getGroup() {
        return group;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getImage() {
        return image;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
