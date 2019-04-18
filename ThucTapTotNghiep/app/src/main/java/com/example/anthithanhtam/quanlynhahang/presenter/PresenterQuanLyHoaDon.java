package com.example.anthithanhtam.quanlynhahang.presenter;

import com.example.anthithanhtam.quanlynhahang.database.SOService;
import com.example.anthithanhtam.quanlynhahang.fragment.ViewFragmentQuanLyHoaDon;
import com.example.anthithanhtam.quanlynhahang.model.Bill;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterQuanLyHoaDon implements ImplQuanLyHoaDon{
    private SOService soService;
    private ViewFragmentQuanLyHoaDon viewFragmentQuanLyHoaDon;

    public PresenterQuanLyHoaDon(SOService soService, ViewFragmentQuanLyHoaDon viewFragmentQuanLyHoaDon) {
        this.soService = soService;
        this.viewFragmentQuanLyHoaDon = viewFragmentQuanLyHoaDon;
    }

    @Override
    public void getDataBill() {
        soService.getDataBill().enqueue(new Callback<List<Bill>>() {
            @Override
            public void onResponse(Call<List<Bill>> call, Response<List<Bill>> response) {
                if (response.body()!=null)
                {
                    viewFragmentQuanLyHoaDon.getDataBill(response.body());
                }else {
                    viewFragmentQuanLyHoaDon.onError();
                }
            }

            @Override
            public void onFailure(Call<List<Bill>> call, Throwable t) {
                call.clone().enqueue(this);
            }
        });
    }
}
