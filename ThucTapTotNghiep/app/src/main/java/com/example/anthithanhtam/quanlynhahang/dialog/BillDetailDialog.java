package com.example.anthithanhtam.quanlynhahang.dialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anthithanhtam.quanlynhahang.R;
import com.example.anthithanhtam.quanlynhahang.adapter.BillDetailAdapter;
import com.example.anthithanhtam.quanlynhahang.constant.Utils;
import com.example.anthithanhtam.quanlynhahang.database.SOService;
import com.example.anthithanhtam.quanlynhahang.model.BillDetail;
import com.example.anthithanhtam.quanlynhahang.model.Employee;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressLint("ValidFragment")
public class BillDetailDialog extends DialogFragment {
    @BindView(R.id.txtIdBillDL)
    TextView txtIdBillDL;
    @BindView(R.id.txtTongTienDL)
    TextView txtTongTienDL;
    @BindView(R.id.txtEmployeeCreateDL)
    TextView txtEmployeeCreateDL;
    @BindView(R.id.txtDateCreateDL)
    TextView txtDateCreateDL;
    @BindView(R.id.recyclerBillDetail)
    RecyclerView recyclerBillDetail;
    Unbinder unbinder;
    private SOService soService;
    private String idBill, dateCreate, employee;



    private List<BillDetail> arrMyitem;
    private BillDetailAdapter billDetailAdapter;


    public BillDetailDialog(SOService soService, String idBill, String dateCreate, String employee) {
        this.soService=soService;
        this.idBill=idBill;
        this.dateCreate=dateCreate;
        this.employee=employee;

    }

    public static BillDetailDialog newInstance(SOService soService, String idBill, String dateCreate, String employee) {
        BillDetailDialog billDetailDialog = new BillDetailDialog(soService, idBill, dateCreate, employee);
        billDetailDialog.setStyle(STYLE_NO_FRAME, android.R.style.Theme_Holo_Light);
        return billDetailDialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_bill_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }

    private void initData() {
        soService.getDataBillDetail(idBill).enqueue(new Callback<List<BillDetail>>() {
            @Override
            public void onResponse(Call<List<BillDetail>> call, Response<List<BillDetail>> response) {
                if (response.body()!=null)
                {
                    arrMyitem.addAll(response.body());
                    billDetailAdapter.notifyDataSetChanged();
                    getPrice();
                }
            }

            @Override
            public void onFailure(Call<List<BillDetail>> call, Throwable t) {
                call.clone().enqueue(this);
            }
        });

        soService.getEmployee().enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                List<Employee>list=response.body();
                for (Employee employee:list)
                {
                    if (employee.getMaNhanVien().equals(idBill))
                    {
                        txtEmployeeCreateDL.setText(employee.getTenNhanVien());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {

            }
        });


    }

    private void initView() {
        txtIdBillDL.setText(idBill);
        txtDateCreateDL.setText(Utils.fomatDateTimeDD_MM_YYYY_HH_MM(dateCreate));
        txtEmployeeCreateDL.setText(employee);
        arrMyitem=new ArrayList<>();
        billDetailAdapter=new BillDetailAdapter(getContext(), arrMyitem);
        recyclerBillDetail.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerBillDetail.setHasFixedSize(true);
        recyclerBillDetail.setAdapter(billDetailAdapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void getPrice() {
        long total = 0;
        for (int i = 0; i < arrMyitem.size(); i++) {
            total += Integer.parseInt(arrMyitem.get(i).getMenuPrice()) * Integer.parseInt(arrMyitem.get(i).getCount());
        }
        txtTongTienDL.setText(Utils.fomatSalary(String.valueOf(total)));
    }
}
