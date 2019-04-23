package com.example.anthithanhtam.quanlynhahang.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anthithanhtam.quanlynhahang.R;
import com.example.anthithanhtam.quanlynhahang.activity.ClientActivity;
import com.example.anthithanhtam.quanlynhahang.activity.ManagerActivity;
import com.example.anthithanhtam.quanlynhahang.constant.Utils;
import com.example.anthithanhtam.quanlynhahang.database.SOService;
import com.example.anthithanhtam.quanlynhahang.dialog.BillDetailDialog;
import com.example.anthithanhtam.quanlynhahang.model.Bill;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BillClientAdapter extends RecyclerView.Adapter<BillClientAdapter.ViewHodler> {
    private List<Bill> listBill;
    private LayoutInflater inflater;
    private ClientActivity managerActivity;
    private SOService soService;

    public BillClientAdapter(List<Bill> listBill, ClientActivity managerActivity, SOService soService) {
        this.listBill = listBill;
        this.managerActivity = managerActivity;
        this.soService = soService;
        inflater = LayoutInflater.from(managerActivity);
    }

    @NonNull
    @Override
    public ViewHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_client_bill, viewGroup, false);
        return new ViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodler viewHodler, int i) {
        Bill bill = listBill.get(i);
        viewHodler.txtIdBill.setText(bill.getBillId());
        viewHodler.txtNumberTB.setText(bill.getBillNumberTb());
        viewHodler.txtPeople.setText(bill.getBillNumberPeople());
        viewHodler.txtEmployee.setText(bill.getEmployeeId());
        viewHodler.txtDateCreate.setText(Utils.fomatDateTimeDD_MM_YYYY_HH_MM(bill.getBillCreateDate()));
        viewHodler.btnSelectBill.setOnClickListener(v -> {
            BillDetailDialog billDetailDialog = BillDetailDialog.newInstance(soService, bill.getBillId(), bill.getBillCreateDate(), bill.getEmployeeId());
            billDetailDialog.show(managerActivity.getSupportFragmentManager(), "");
        });
    }

    @Override
    public int getItemCount() {
        return listBill.size();
    }



    class ViewHodler extends RecyclerView.ViewHolder {
        @BindView(R.id.txtIdBill)
        TextView txtIdBill;
        @BindView(R.id.txtNumberTB)
        TextView txtNumberTB;
        @BindView(R.id.txtPeople)
        TextView txtPeople;
        @BindView(R.id.txtEmployee)
        TextView txtEmployee;
        @BindView(R.id.txtDateCreate)
        TextView txtDateCreate;
        @BindView(R.id.btnSelectBill)
        LinearLayout btnSelectBill;

        public ViewHodler(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
