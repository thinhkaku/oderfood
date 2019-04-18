package com.example.anthithanhtam.quanlynhahang.presenter;

import com.example.anthithanhtam.quanlynhahang.database.SOService;
import com.example.anthithanhtam.quanlynhahang.fragment.ViewFragmentMenu;
import com.example.anthithanhtam.quanlynhahang.model.Menu;
import com.example.anthithanhtam.quanlynhahang.model.Type;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterFragmentMenu implements ImplFragmentMenu {
    private ViewFragmentMenu viewFragmentMenu;
    private SOService soService;

    public PresenterFragmentMenu(ViewFragmentMenu viewFragmentMenu, SOService soService) {
        this.viewFragmentMenu = viewFragmentMenu;
        this.soService = soService;
    }

    @Override
    public void getListMenu(String type) {
        soService.getMenu(type).enqueue(new Callback<List<Menu>>() {
            @Override
            public void onResponse(Call<List<Menu>> call, Response<List<Menu>> response) {
                if (response.body() != null) {
                    viewFragmentMenu.getListMenu(response.body());
                } else {
                    viewFragmentMenu.error();
                }
            }

            @Override
            public void onFailure(Call<List<Menu>> call, Throwable t) {
                call.clone().enqueue(this);
            }
        });
    }

    @Override
    public void editMyItem(String id, String count, String status) {
        soService.editMyItem(id, count, status).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (response.body() != null) {
                    viewFragmentMenu.editMyItem(response.body());
                } else {
                    viewFragmentMenu.error();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                call.clone().enqueue(this);
            }
        });
    }

    @Override
    public void deleteMyItem(String id) {
        soService.deleteMyItem(id).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body() != null) {
                    viewFragmentMenu.deleteMyItem(response.body());
                } else {
                    viewFragmentMenu.error();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                call.clone().enqueue(this);
            }
        });
    }

    @Override
    public void editTable(String number, String numberPeople, String checkTb, String note, String timeCheck) {
        soService.editTable(number, numberPeople, checkTb, note, timeCheck).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body() != null) {
                    viewFragmentMenu.editTable(response.body());
                } else {
                    viewFragmentMenu.error();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                call.clone().enqueue(this);
            }
        });
    }

    @Override
    public void insertMyItem(String menuId, String numberTable, String count, String createAt) {
        soService.insertMyItem(menuId, numberTable, count, createAt).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body() != null) {
                    viewFragmentMenu.insertMyItem(response.body());
                } else {
                    viewFragmentMenu.error();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
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
                    viewFragmentMenu.getDataType(response.body());
                } else {
                    viewFragmentMenu.error();
                }

            }

            @Override
            public void onFailure(Call<List<Type>> call, Throwable t) {
                call.clone().enqueue(this);
            }
        });
    }
}
