package com.example.anthithanhtam.quanlynhahang.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.anthithanhtam.quanlynhahang.R;
import com.example.anthithanhtam.quanlynhahang.constant.Constant;
import com.example.anthithanhtam.quanlynhahang.constant.Utils;
import com.example.anthithanhtam.quanlynhahang.model.Menu;
import com.example.anthithanhtam.quanlynhahang.sharepreferences.ShareConstand;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHodler> {
    private Context context;
    private List<Menu> arrItem;
    private LayoutInflater inflater;


    public MenuAdapter(Context context, List<Menu> arrItem) {
        this.context = context;
        this.arrItem = arrItem;
        inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public ViewHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View viewRow = inflater.inflate(R.layout.item_listview_menu, viewGroup, false);
        return new ViewHodler(viewRow);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodler viewHolder, int i) {
        final Menu menu = arrItem.get(i);
        Glide.with(context).load(Constant.PORT_IMAGE + menu.getImage()).into(viewHolder.imgOfFood);
        viewHolder.tvNameOdFood.setText(menu.getName());
        viewHolder.tvPriceOfFood.setText(Utils.fomatSalary(menu.getPrice()));
        viewHolder.tvCount.setText(String.valueOf(menu.getCount()));
        viewHolder.btnUp.setOnClickListener(view -> {
            int i1 = Integer.parseInt(menu.getCount());
            i1 = i1 + 1;
            viewHolder.tvCount.setText(String.valueOf(i1));
            menu.setCount(String.valueOf(i1));
            ShareConstand.setActionMenu(context, "1");
        });

        viewHolder.btnDown.setOnClickListener(view -> {
            int i12 = Integer.parseInt(menu.getCount());
            if (i12 == 0) {
                menu.setCount(String.valueOf(i12));
            } else {
                i12 = i12 - 1;
                viewHolder.tvCount.setText(String.valueOf(i12));
                menu.setCount(String.valueOf(i12));
            }
            ShareConstand.setActionMenu(context, "1");
        });
    }

    @Override
    public int getItemCount() {
        return arrItem.size();
    }



    class ViewHodler extends RecyclerView.ViewHolder {
        @BindView(R.id.imgOfFood)
        ImageView imgOfFood;
        @BindView(R.id.tvNameOdFood)
        TextView tvNameOdFood;
        @BindView(R.id.tvPriceOfFood)
        TextView tvPriceOfFood;
        @BindView(R.id.btnUp)
        ImageView btnUp;
        @BindView(R.id.tvCount)
        TextView tvCount;
        @BindView(R.id.btnDown)
        ImageView btnDown;

        public ViewHodler(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
