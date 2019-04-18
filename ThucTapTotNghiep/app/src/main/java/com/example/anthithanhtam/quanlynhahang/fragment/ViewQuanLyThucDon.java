package com.example.anthithanhtam.quanlynhahang.fragment;

import com.example.anthithanhtam.quanlynhahang.model.Menu;
import com.example.anthithanhtam.quanlynhahang.model.Type;

import java.util.List;

public interface ViewQuanLyThucDon {
    void getListMenu(List<Menu> listMenu);
    void getDataType(List<Type>listType);
    void error();
}
