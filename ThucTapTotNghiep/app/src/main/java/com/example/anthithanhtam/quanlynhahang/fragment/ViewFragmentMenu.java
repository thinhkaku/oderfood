package com.example.anthithanhtam.quanlynhahang.fragment;

import com.example.anthithanhtam.quanlynhahang.model.Menu;
import com.example.anthithanhtam.quanlynhahang.model.Type;

import java.util.List;

public interface ViewFragmentMenu {
    void getListMenu(List<Menu> listMenu);
    void getDataType(List<Type>listType);
    void editMyItem(String message);
    void insertMyItem(String message);
    void deleteMyItem(String message);
    void editTable(String message);

    void error();
}
