package com.example.anthithanhtam.quanlynhahang.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anthithanhtam.quanlynhahang.R;
import com.example.anthithanhtam.quanlynhahang.constant.Utils;
import com.example.anthithanhtam.quanlynhahang.model.BillDetail;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BillDetailAdapter extends RecyclerView.Adapter<BillDetailAdapter.ViewHolder> {

    private List<BillDetail> arrItem;
    private LayoutInflater inflater;

    public BillDetailAdapter(Context context, List<BillDetail> arrItem) {
        this.arrItem = arrItem;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_bill_detail, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHodler, int i) {
        BillDetail myItem=arrItem.get(i);
        viewHodler.tvNameOdFoodBillDetail.setText(myItem.getMenuName());
        viewHodler.tvPriceOfFoodBillDetail.setText(Utils.fomatSalary(myItem.getMenuPrice()));
        viewHodler.tvCountBillDetail.setText(myItem.getCount());
    }

    @Override
    public int getItemCount() {
        return arrItem.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvNameOdFoodBillDetail)
        TextView tvNameOdFoodBillDetail;
        @BindView(R.id.tvPriceOfFoodBillDetail)
        TextView tvPriceOfFoodBillDetail;
        @BindView(R.id.tvCountBillDetail)
        TextView tvCountBillDetail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
