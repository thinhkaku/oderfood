package com.example.anthithanhtam.quanlynhahang.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyItem {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("menuId")
    @Expose
    private String menuId;
    @SerializedName("numberTable")
    @Expose
    private String numberTable;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("count")
    @Expose
    private String count;

    @SerializedName("create_at")
    @Expose
    private String createAt;

    @SerializedName("status")
    @Expose
    private String status;

    /**
     * No args constructor for use in serialization
     *
     */
    public MyItem() {
    }

    /**
     *
     * @param id
     * @param price
     * @param count
     * @param name
     * @param image
     * @param menuId
     * @param numberTable
     * @param createAt
     * @param status
     */
    public MyItem(String id, String menuId, String numberTable, String name, String price, String image, String count, String createAt, String status) {
        super();
        this.id = id;
        this.menuId = menuId;
        this.numberTable = numberTable;
        this.name = name;
        this.price = price;
        this.image = image;
        this.count = count;
        this.createAt=createAt;
        this.status=status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String groupItem) {
        this.menuId = groupItem;
    }

    public String getNumberTable() {
        return numberTable;
    }

    public void setNumberTable(String numberTable) {
        this.numberTable = numberTable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
