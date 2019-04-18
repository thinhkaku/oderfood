package com.example.anthithanhtam.quanlynhahang.database;


import com.example.anthithanhtam.quanlynhahang.constant.Constant;

public class ApiUtils {
    public static SOService getSOService() {
        return RetrofitClient.getClient(Constant.PORT).create(SOService.class);
    }
}
