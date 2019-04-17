package com.example.anthithanhtam.quanlynhahang.presenter;

import com.example.anthithanhtam.quanlynhahang.database.SOService;
import com.example.anthithanhtam.quanlynhahang.fragment.ViewFragmentMainClient;
import com.example.anthithanhtam.quanlynhahang.model.MyTable;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterMainClient implements ImplMainClient {
    private ViewFragmentMainClient viewFragmentMainClient;
    private SOService soService;

    public PresenterMainClient(ViewFragmentMainClient viewFragmentMainClient, SOService soService) {
        this.viewFragmentMainClient = viewFragmentMainClient;
        this.soService = soService;
    }

    @Override
    public void getListMyTable() {
        soService.getMyTable().enqueue(new Callback<List<MyTable>>() {
            @Override
            public void onResponse(Call<List<MyTable>> call, Response<List<MyTable>> response) {
                if (response.body()!=null)
                {
                    viewFragmentMainClient.listMyTable(response.body());
                }else {
                    viewFragmentMainClient.error();
                }
            }

            @Override
            public void onFailure(Call<List<MyTable>> call, Throwable t) {
                call.clone().enqueue(this);
            }
        });
    }
}
