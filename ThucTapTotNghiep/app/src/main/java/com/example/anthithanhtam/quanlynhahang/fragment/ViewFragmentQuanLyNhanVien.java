package com.example.anthithanhtam.quanlynhahang.fragment;

import com.example.anthithanhtam.quanlynhahang.model.Employee;

import java.util.List;

public interface ViewFragmentQuanLyNhanVien {
    void getEmployee(List<Employee>listEmployee);
    void deleteEmployee(String message);
    void editEmployee(String message);
}
