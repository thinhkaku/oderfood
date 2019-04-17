package com.example.anthithanhtam.quanlynhahang.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Type {

    @SerializedName("type_id")
    @Expose
    private String typeId;
    @SerializedName("type_name")
    @Expose
    private String typeName;
    @SerializedName("group_id")
    @Expose
    private String groupId;

    /**
     * No args constructor for use in serialization
     *
     */
    public Type() {
    }

    /**
     *
     * @param typeName
     * @param groupId
     * @param typeId
     */
    public Type(String typeId, String typeName, String groupId) {
        super();
        this.typeId = typeId;
        this.typeName = typeName;
        this.groupId = groupId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

}
