package com.example.anthithanhtam.quanlynhahang.presenter;

import com.example.anthithanhtam.quanlynhahang.database.SOService;
import com.example.anthithanhtam.quanlynhahang.fragment.ViewFragmentQuanLyNhanVien;
import com.example.anthithanhtam.quanlynhahang.model.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterQuanLyNhanVien implements ImplQuanLyNhanVien {
    private ViewFragmentQuanLyNhanVien viewFragmentQuanLyNhanVien;
    private SOService soService;

    public PresenterQuanLyNhanVien(ViewFragmentQuanLyNhanVien viewFragmentQuanLyNhanVien, SOService soService) {
        this.viewFragmentQuanLyNhanVien = viewFragmentQuanLyNhanVien;
        this.soService = soService;
    }

    @Override
    public void getEmployee() {
        soService.getEmployee().enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                viewFragmentQuanLyNhanVien.getEmployee(response.body());
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                call.clone().enqueue(this);
            }
        });
    }

    @Override
    public void deleteEmployee(final String id, final String nameImage) {
        soService.deleteImage(nameImage).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body()!=null)
                {
                    if (response.body().equals("1"))
                    {
                        soService.deleteEmployee(id).enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                if (response.body()!=null)
                                {
                                    viewFragmentQuanLyNhanVien.deleteEmployee(response.body());
                                }

                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                call.clone().enqueue(this);
                            }
                        });
                    }else {
                        viewFragmentQuanLyNhanVien.deleteEmployee(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                call.clone().enqueue(this);
            }
        });

    }

    @Override
    public void editEmployee(final Employee employee) {
        soService.editEmployee(employee.getMaNhanVien(),employee.getPass(),employee.getTenNhanVien(),employee.getAnh(),employee.getSoDienThoai(),employee.getDiaChi(),
                employee.getEmail(),employee.getNgaySinh(),employee.getChucVu(),employee.getMucLuong(), employee.getThoiGianBatDau(), employee.getThoiGianKetThuc()).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body()!=null )
                {
                    viewFragmentQuanLyNhanVien.editEmployee(response.body());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                call.clone().enqueue(this);
            }
        });
    }
}
