package com.example.anthithanhtam.quanlynhahang.presenter;

import com.example.anthithanhtam.quanlynhahang.database.SOService;
import com.example.anthithanhtam.quanlynhahang.fragment.ViewQuanLyThucDon;
import com.example.anthithanhtam.quanlynhahang.model.Menu;
import com.example.anthithanhtam.quanlynhahang.model.Type;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterMenuManager implements ImplMenuManager{
    private SOService soService;
    private ViewQuanLyThucDon viewQuanLyThucDon;

    public PresenterMenuManager(SOService soService, ViewQuanLyThucDon viewQuanLyThucDon) {
        this.soService = soService;
        this.viewQuanLyThucDon = viewQuanLyThucDon;
    }

    @Override
    public void getListMenu() {
        soService.getDataMenuManager().enqueue(new Callback<List<Menu>>() {
            @Override
            public void onResponse(Call<List<Menu>> call, Response<List<Menu>> response) {
                if (response.body() != null) {
                    viewQuanLyThucDon.getListMenu(response.body());
                } else {
                    viewQuanLyThucDon.error();
                }
            }

            @Override
            public void onFailure(Call<List<Menu>> call, Throwable t) {
                call.clone().enqueue(this);
            }
        });
    }

    @Override
    public void getDataType() {
        soService.getDataType().enqueue(new Callback<List<Type>>() {
            @Override
            public void onResponse(Call<List<Type>> call, Response<List<Type>> response) {
                if (response.body() != null) {
                    viewQuanLyThucDon.getDataType(response.body());
                } else {
                    viewQuanLyThucDon.error();
                }

            }

            @Override
            public void onFailure(Call<List<Type>> call, Throwable t) {
                call.clone().enqueue(this);
            }
        });
    }

    @Override
    public void deleteMenu(String idMenu) {
        soService.deleteMenu(idMenu).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body()!=null)
                {
                    viewQuanLyThucDon.onSuccess();
                }else {
                    viewQuanLyThucDon.error();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                call.clone().enqueue(this);
            }
        });
    }
}
