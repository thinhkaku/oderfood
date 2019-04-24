package com.example.anthithanhtam.quanlynhahang.dialog;


import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.anthithanhtam.quanlynhahang.R;
import com.example.anthithanhtam.quanlynhahang.constant.Constant;
import com.example.anthithanhtam.quanlynhahang.model.Menu;

import butterknife.BindView;
import butterknife.OnClick;

@SuppressLint("ValidFragment")
public class MenuDetailDialog extends BaseDialog {

    @BindView(R.id.txtTenMonAnDL)
    TextView txtTenMonAnDL;
    @BindView(R.id.txtGia)
    TextView txtGia;
    @BindView(R.id.imgMenu)
    ImageView imgMenu;
    @BindView(R.id.txtDescribe)
    TextView txtDescribe;
    @BindView(R.id.btnOK)
    Button btnOK;
    private Menu menu;
    private Context context;

    public static MenuDetailDialog newInstace(Menu menu, Context context) {
        MenuDetailDialog menuDetailDialog = new MenuDetailDialog(menu, context);
        menuDetailDialog.setStyle(DialogFragment.STYLE_NORMAL,
                android.R.style.Theme_Light_NoTitleBar);
        return menuDetailDialog;
    }

    public MenuDetailDialog(Menu menu, Context context) {
        this.menu=menu;
        this.context=context;
    }

    @Override
    public int layoutId() {
        return R.layout.dialog_menu_detail;
    }

    @Override
    public void loadData() {
        Glide.with(context).load(Constant.PORT_IMAGE+menu.getImage()).into(imgMenu);
        txtTenMonAnDL.setText(menu.getName());
        txtGia.setText(menu.getPrice());
        txtDescribe.setText(menu.getDescrible());
    }


    @OnClick(R.id.btnOK)
    public void onViewClicked() {
        this.dismiss();
    }
}

