package com.example.anthithanhtam.quanlynhahang.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.anthithanhtam.quanlynhahang.R;
import com.example.anthithanhtam.quanlynhahang.activity.ManagerActivity;
import com.example.anthithanhtam.quanlynhahang.constant.Constant;
import com.example.anthithanhtam.quanlynhahang.constant.Utils;
import com.example.anthithanhtam.quanlynhahang.dialog.MenuDetailDialog;
import com.example.anthithanhtam.quanlynhahang.dialog.MenuEditDialog;
import com.example.anthithanhtam.quanlynhahang.interfacee.DialogAddMenuInterface;
import com.example.anthithanhtam.quanlynhahang.model.Menu;
import com.example.anthithanhtam.quanlynhahang.presenter.PresenterMenuManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuManagerAdapter extends RecyclerView.Adapter<MenuManagerAdapter.ViewHodler> {
    private Context context;
    private List<Menu> arrItem;
    private LayoutInflater inflater;
    private ManagerActivity managerActivity;
    private DialogAddMenuInterface dialogAddMenuInterface;
    private PresenterMenuManager presenterMenuManager;


    public MenuManagerAdapter(Context context, List<Menu> arrItem, DialogAddMenuInterface dialogAddMenuInterface, PresenterMenuManager presenterMenuManager) {
        this.context = context;
        managerActivity= (ManagerActivity) context;
        this.arrItem = arrItem;
        this.dialogAddMenuInterface=dialogAddMenuInterface;
        this.presenterMenuManager=presenterMenuManager;
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
            questionDeleteMenu(menu.getId());
        });

        viewHodler.btnEditMn.setOnClickListener(v -> {
            MenuEditDialog menuEditDialog=MenuEditDialog.newInstace(menu,context,dialogAddMenuInterface);
            menuEditDialog.show(managerActivity.getSupportFragmentManager(),"");
        });


        viewHodler.frMenuManager.setOnClickListener(v -> {
            MenuDetailDialog menuDetailDialog=MenuDetailDialog.newInstace(menu, context);
            menuDetailDialog.show(managerActivity.getSupportFragmentManager(),"");
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
        @BindView(R.id.frMenuManager)
        FrameLayout frMenuManager;


        public ViewHodler(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private void questionDeleteMenu(String menuId)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn thực sự muốn xóa món ăn này không?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenterMenuManager.deleteMenu(menuId);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
}
