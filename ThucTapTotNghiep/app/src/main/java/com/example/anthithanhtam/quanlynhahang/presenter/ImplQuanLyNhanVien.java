package com.example.anthithanhtam.quanlynhahang.presenter;

import com.example.anthithanhtam.quanlynhahang.model.Employee;

import java.util.List;

public interface ImplQuanLyNhanVien {
    void getEmployee();
    void deleteEmployee(String id, String nameImage);
    void editEmployee(Employee employee);

}
