package com.example.anthithanhtam.quanlynhahang.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anthithanhtam.quanlynhahang.R;
import com.example.anthithanhtam.quanlynhahang.constant.Utils;
import com.example.anthithanhtam.quanlynhahang.model.MyItem;
import com.example.anthithanhtam.quanlynhahang.presenter.PresenterFragmentBill;
import com.example.anthithanhtam.quanlynhahang.sharepreferences.ShareConstand;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BillAdapter extends RecyclerView.Adapter<com.example.anthithanhtam.quanlynhahang.adapter.BillAdapter.ViewHolder> {
    private Context context;
    private List<MyItem> arrItem;
    private TextView tvTotalBill;
    private PresenterFragmentBill fragmentBillPresenter;
    private LayoutInflater inflater;

    public BillAdapter(Context context, List<MyItem> arrItem, TextView tvTotalBill, PresenterFragmentBill fragmentBillPresenter) {
        this.context = context;
        inflater=LayoutInflater.from(context);
        this.arrItem = arrItem;
        this.tvTotalBill = tvTotalBill;
        this.fragmentBillPresenter = fragmentBillPresenter;
        getPrice();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvNameOdFoodBill)
        TextView tvNameOdFoodBill;
        @BindView(R.id.tvPriceOfFoodBill)
        TextView tvPriceOfFoodBill;
        @BindView(R.id.ckStatus)
        CheckBox ckStatus;
        @BindView(R.id.btnDownBill)
        ImageView btnDownBill;
        @BindView(R.id.tvCountBill)
        TextView tvCountBill;
        @BindView(R.id.btnUpBill)
        ImageView btnUpBill;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View viewRow = inflater.inflate(R.layout.item_listview_bill, viewGroup, false);
        return new ViewHolder(viewRow);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final MyItem item = arrItem.get(i);
        viewHolder.tvNameOdFoodBill.setText(item.getName());
        viewHolder.tvPriceOfFoodBill.setText(Utils.fomatSalary(item.getPrice()));
        viewHolder.tvCountBill.setText(item.getCount() + "");
        if (item.getStatus().equals("0")) {
            viewHolder.ckStatus.setChecked(false);
        } else {
            viewHolder.ckStatus.setChecked(true);
        }

        final int[] j = {Integer.parseInt(item.getCount())};
        viewHolder.btnUpBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                j[0] = j[0] + 1;
                viewHolder.tvCountBill.setText(String.valueOf(j[0]));
                item.setCount(String.valueOf(j[0]));
                //fragmentBillPresenter.editMyItem(item.getId(), String.valueOf(j[0]));
                notifyDataSetChanged();
                getPrice();
                ShareConstand.setActionBill(context, "1");
            }
        });

        viewHolder.btnDownBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (j[0] == 0) {
                    viewHolder.tvCountBill.setText(0 + "");
                    item.setCount("0");
                    //fragmentBillPresenter.editMyItem(item.getId(), String.valueOf(j[0]));
                } else {
                    j[0] = j[0] - 1;
                    viewHolder.tvCountBill.setText(String.valueOf(j[0]));
                    item.setCount(String.valueOf(j[0]));
                    //fragmentBillPresenter.editMyItem(item.getId(), String.valueOf(j[0]));
                }
                getPrice();
                notifyDataSetChanged();
                ShareConstand.setActionBill(context, "1");

            }
        });
        viewHolder.ckStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    item.setStatus("1");
                } else {
                    item.setStatus("0");
                }
                ShareConstand.setActionBill(context, "1");
            }
        });
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return arrItem.size();
    }


    public void getPrice() {
        long total = 0;
        for (int i = 0; i < arrItem.size(); i++) {
            total += Integer.parseInt(arrItem.get(i).getPrice()) * Integer.parseInt(arrItem.get(i).getCount());
        }
        tvTotalBill.setText("Tổng tiền:  " + Utils.fomatSalary(String.valueOf(total)));
    }



}
