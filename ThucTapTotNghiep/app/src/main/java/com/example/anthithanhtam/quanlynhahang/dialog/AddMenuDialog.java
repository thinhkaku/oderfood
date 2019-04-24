package com.example.anthithanhtam.quanlynhahang.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.anthithanhtam.quanlynhahang.R;
import com.example.anthithanhtam.quanlynhahang.database.ApiUtils;
import com.example.anthithanhtam.quanlynhahang.database.SOService;
import com.example.anthithanhtam.quanlynhahang.model.Type;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressLint("ValidFragment")
public class AddMenuDialog extends BaseDialog {
    @BindView(R.id.edtNameMenu)
    TextInputEditText edtNameMenu;
    @BindView(R.id.spDanhMuc)
    Spinner spDanhMuc;
    @BindView(R.id.edtGia)
    TextInputEditText edtGia;
    @BindView(R.id.edtDescrible)
    TextInputEditText edtDescrible;
    @BindView(R.id.imgAnhDaiDien)
    ImageView imgAnhDaiDien;
    @BindView(R.id.btnSelectImage)
    Button btnSelectImage;
    @BindView(R.id.btnXacNhan)
    Button btnXacNhan;
    Unbinder unbinder;
    private Context context;
    private List<Type> mListType = new ArrayList<>();
    private ArrayList<String> arrDanhMuc = new ArrayList<>();
    private ArrayAdapter<String> danhMucAdapter;
    private SOService soService;

    public static AddMenuDialog newInstace(Context context) {
        AddMenuDialog addMenuDialog = new AddMenuDialog(context);
        addMenuDialog.setStyle(DialogFragment.STYLE_NORMAL,
                android.R.style.Theme_Light_NoTitleBar);
        return addMenuDialog;
    }

    public AddMenuDialog(Context context) {
        this.context = context;
    }

    @Override
    public int layoutId() {
        return R.layout.dialog_add_menu;
    }

    @Override
    public void loadData() {
        soService = ApiUtils.getSOService();
        soService.getDataType().enqueue(new Callback<List<Type>>() {
            @Override
            public void onResponse(Call<List<Type>> call, Response<List<Type>> response) {
                if (response.body() != null) {
                    mListType.clear();
                    mListType.addAll(response.body());
                    arrDanhMuc.clear();
                    for (Type type : mListType) {
                        arrDanhMuc.add(type.getTypeName());
                    }
                    danhMucAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, arrDanhMuc);
                    danhMucAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spDanhMuc.setAdapter(danhMucAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Type>> call, Throwable t) {
                call.clone().enqueue(this);
            }
        });
    }




    @OnClick({R.id.btnSelectImage, R.id.btnXacNhan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSelectImage:
                break;
            case R.id.btnXacNhan:
                this.dismiss();
                break;
        }
    }
}
