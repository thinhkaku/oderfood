package com.example.anthithanhtam.quanlynhahang.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.anthithanhtam.quanlynhahang.R;
import com.example.anthithanhtam.quanlynhahang.constant.Constant;
import com.example.anthithanhtam.quanlynhahang.constant.Utils;
import com.example.anthithanhtam.quanlynhahang.model.Menu;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuManagerAdapter extends RecyclerView.Adapter<MenuManagerAdapter.ViewHodler> {
    private Context context;
    private List<Menu> arrItem;
    private LayoutInflater inflater;


    public MenuManagerAdapter(Context context, List<Menu> arrItem) {
        this.context = context;
        this.arrItem = arrItem;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_menu_manager, viewGroup, false);
        return new ViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodler viewHodler, int i) {
        Menu menu=arrItem.get(i);
        viewHodler.tvNameOdFoodMn.setText(menu.getName());
        viewHodler.tvPriceOfFoodMn.setText(Utils.fomatSalary(menu.getPrice()));
        Glide.with(context).load(Constant.PORT_IMAGE+menu.getImage()).into(viewHodler.imgOfFoodMn);
        viewHodler.btnDeleteMenu.setOnClickListener(v -> {

        });

        viewHodler.btnEditMn.setOnClickListener(v -> {
            Toast.makeText(context, "Sửa", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return arrItem.size();
    }



    class ViewHodler extends RecyclerView.ViewHolder {
        @BindView(R.id.imgOfFoodMn)
        ImageView imgOfFoodMn;
        @BindView(R.id.tvNameOdFoodMn)
        TextView tvNameOdFoodMn;
        @BindView(R.id.tvPriceOfFoodMn)
        TextView tvPriceOfFoodMn;
        @BindView(R.id.btnEditMn)
        ImageView btnEditMn;
        @BindView(R.id.btnDeleteMenu)
        ImageView btnDeleteMenu;


        public ViewHodler(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
