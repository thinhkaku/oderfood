package com.example.anthithanhtam.quanlynhahang.presenter;

import com.example.anthithanhtam.quanlynhahang.database.SOService;
import com.example.anthithanhtam.quanlynhahang.fragment.ViewFragmentBill;
import com.example.anthithanhtam.quanlynhahang.model.MyItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterFragmentBill implements ImplFragmentBill {
    private ViewFragmentBill viewFragmentBill;
    private SOService soService;

    public PresenterFragmentBill(ViewFragmentBill viewFragmentBill, SOService soService) {
        this.viewFragmentBill = viewFragmentBill;
        this.soService = soService;
    }

    @Override
    public void getListMyItem(String numberTable) {
        soService.getMyItem(numberTable).enqueue(new Callback<List<MyItem>>() {
            @Override
            public void onResponse(Call<List<MyItem>> call, Response<List<MyItem>> response) {
                if (response.body() != null) {
                    viewFragmentBill.getListMyItem(response.body());
                } else {
                    viewFragmentBill.error();
                }
            }

            @Override
            public void onFailure(Call<List<MyItem>> call, Throwable t) {
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
                    viewFragmentBill.onSuccess();
                } else {
                    viewFragmentBill.error();
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
                    viewFragmentBill.deleteMyItem(response.body());
                } else {
                    viewFragmentBill.error();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                call.clone().enqueue(this);
            }
        });
    }

    @Override
    public void editMyTable(String number, String numberPeople, String checkTb, String note, String timeCheck) {
        soService.editTable(number, numberPeople, checkTb, note, timeCheck).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body()!=null)
                {
                    viewFragmentBill.onSuccess();
                }else {
                    viewFragmentBill.error();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                call.clone().enqueue(this);
            }
        });
    }

    @Override
    public void insertBill(String bill_number_tb, String bill_number_people, String employee_id, String bill_create_date) {
        soService.insertBill(bill_number_tb, bill_number_people, employee_id, bill_create_date).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body() != null) {
                    viewFragmentBill.onSuccessInsertBill(response.body());
                } else {
                    viewFragmentBill.error();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                call.clone().enqueue(this);
            }
        });
    }

    @Override
    public void insertBillDetail(String bill_id, String menu_id, String count, String bill_detail_create_date) {
        soService.insertBillDetail(bill_id, menu_id, count, bill_detail_create_date).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body() != null) {
                    viewFragmentBill.onSuccessInsertBillDetail();

                } else {
                    viewFragmentBill.error();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                call.clone().enqueue(this);
            }
        });
    }

    public void deleteAllMyitem(String id) {
        soService.deleteAllMyItem(id).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                call.clone().enqueue(this);
            }
        });
    }
}
