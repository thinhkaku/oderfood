package com.example.anthithanhtam.quanlynhahang.activity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.anthithanhtam.quanlynhahang.R;
import com.example.anthithanhtam.quanlynhahang.adapter.TypeAdapter;
import com.example.anthithanhtam.quanlynhahang.constant.Utils;
import com.example.anthithanhtam.quanlynhahang.database.ApiUtils;
import com.example.anthithanhtam.quanlynhahang.database.SOService;
import com.example.anthithanhtam.quanlynhahang.model.Type;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManagerTypeActivity extends BaseActivity {

    @BindView(R.id.recyclerType)
    RecyclerView recyclerType;
    @BindView(R.id.btnAddType)
    ImageView btnAddType;
    private List<Type> listType;
    private TypeAdapter typeAdapter;
    private SOService soService;


    @Override
    protected int layoutID() {
        return R.layout.activity_manager_type;
    }

    @Override
    protected void initView() {
        soService = ApiUtils.getSOService();
        listType = new ArrayList<>();
        typeAdapter = new TypeAdapter(this, listType, soService);
        recyclerType.setLayoutManager(new LinearLayoutManager(this));
        recyclerType.setHasFixedSize(true);
        recyclerType.setAdapter(typeAdapter);
        getData();
    }

    public void getData() {
        soService.getDataType().enqueue(new Callback<List<Type>>() {
            @Override
            public void onResponse(Call<List<Type>> call, Response<List<Type>> response) {
                if (response.body() != null) {
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



    @OnClick(R.id.btnAddType)
    public void onViewClicked() {
        initDialogAddType();
    }

    private Dialog dialogAddType;
    private void initDialogAddType()
    {
        dialogAddType=new Dialog(this);
        dialogAddType.setContentView(R.layout.dialog_add_type);
        EditText edtNameType=dialogAddType.findViewById(R.id.edtNameType);
        Button btnCancle=dialogAddType.findViewById(R.id.btnCancle);
        Button btnAccess=dialogAddType.findViewById(R.id.btnAccess);
        btnAccess.setOnClickListener(v->{
            String nameType=edtNameType.getText().toString().trim();
            if (!nameType.isEmpty())
            {
                soService.insertType(nameType).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.body()!=null)
                        {
                            getData();
                            Utils.toastMessage(ManagerTypeActivity.this,"");
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        call.clone().enqueue(this);
                    }
                });
            }
            dialogAddType.dismiss();
        });
        btnCancle.setOnClickListener(v->{
            dialogAddType.dismiss();
        });
        dialogAddType.show();
    }
}
