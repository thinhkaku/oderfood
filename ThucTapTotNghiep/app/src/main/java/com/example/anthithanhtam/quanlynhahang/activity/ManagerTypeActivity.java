package com.example.anthithanhtam.quanlynhahang.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.anthithanhtam.quanlynhahang.R;
import com.example.anthithanhtam.quanlynhahang.adapter.TypeAdapter;
import com.example.anthithanhtam.quanlynhahang.database.ApiUtils;
import com.example.anthithanhtam.quanlynhahang.database.SOService;
import com.example.anthithanhtam.quanlynhahang.model.Type;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManagerTypeActivity extends BaseActivity {

    @BindView(R.id.recyclerType)
    RecyclerView recyclerType;
    private List<Type> listType;
    private TypeAdapter typeAdapter;
    private SOService soService;


    @Override
    protected int layoutID() {
        return R.layout.activity_manager_type;
    }

    @Override
    protected void initView() {
        soService= ApiUtils.getSOService();
        listType=new ArrayList<>();
        typeAdapter=new TypeAdapter(this, listType,soService);
        recyclerType.setLayoutManager(new LinearLayoutManager(this));
        recyclerType.setHasFixedSize(true);
        recyclerType.setAdapter(typeAdapter);
        getData();
    }

    public void getData()
    {
        soService.getDataType().enqueue(new Callback<List<Type>>() {
            @Override
            public void onResponse(Call<List<Type>> call, Response<List<Type>> response) {
                if (response.body()!=null)
                {
                    listType.clear();
                    listType.addAll(response.body());
                    typeAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Type>> call, Throwable t) {
                call.clone().enqueue(this);
            }
        });
    }

    @Override
    protected void addEvent() {

    }

}
