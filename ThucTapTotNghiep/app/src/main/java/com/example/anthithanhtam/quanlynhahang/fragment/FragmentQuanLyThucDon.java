package com.example.anthithanhtam.quanlynhahang.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.anthithanhtam.quanlynhahang.R;
import com.example.anthithanhtam.quanlynhahang.activity.ManagerActivity;
import com.example.anthithanhtam.quanlynhahang.activity.ManagerTypeActivity;
import com.example.anthithanhtam.quanlynhahang.adapter.MenuManagerAdapter;
import com.example.anthithanhtam.quanlynhahang.dialog.AddMenuDialog;
import com.example.anthithanhtam.quanlynhahang.interfacee.DialogAddMenuInterface;
import com.example.anthithanhtam.quanlynhahang.model.Menu;
import com.example.anthithanhtam.quanlynhahang.model.Type;
import com.example.anthithanhtam.quanlynhahang.presenter.PresenterMenuManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FragmentQuanLyThucDon extends BaseFragment implements ViewQuanLyThucDon, DialogAddMenuInterface {
    @BindView(R.id.recyclerMenuMn)
    RecyclerView recyclerMenuMn;
    Unbinder unbinder;
    Unbinder unbinder1;
    private MenuManagerAdapter managerAdapter;
    private List<Menu> listMenu;
    private PresenterMenuManager presenterMenuManager;
    private ManagerActivity m;

    @Override
    protected void initView() {
        m = (ManagerActivity) mActivity;
        listMenu = new ArrayList<>();
        presenterMenuManager = new PresenterMenuManager(soService, this);
        managerAdapter = new MenuManagerAdapter(mActivity, listMenu, this, presenterMenuManager);
        recyclerMenuMn.setLayoutManager(new LinearLayoutManager(mActivity));
        recyclerMenuMn.setHasFixedSize(true);
        recyclerMenuMn.setAdapter(managerAdapter);
        presenterMenuManager.getDataType();

    }

    @Override
    protected int layoutID() {
        return R.layout.layout_quan_ly_thuc_don_fragment;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return false;
    }

    @Override
    public void getListMenu(List<Menu> listMenu) {
        this.listMenu.clear();
        this.listMenu.addAll(listMenu);
        managerAdapter.notifyDataSetChanged();
    }

    @Override
    public void getDataType(List<Type> listType) {
        presenterMenuManager.getListMenu();
    }

    @Override
    public void error() {

    }

    @Override
    public void onSuccess() {
        presenterMenuManager.getListMenu();
        Toast.makeText(m, "Xóa món ăn thành công", Toast.LENGTH_SHORT).show();
    }


    @OnClick({R.id.btnAddMenu, R.id.btnManagerType})
    public void onViewClicked(View view) {
        switch (view.getId())
        {
            case R.id.btnManagerType:
                Intent i=new Intent(mActivity, ManagerTypeActivity.class);
                startActivity(i);
                break;
            case R.id.btnAddMenu:
                AddMenuDialog addMenuDialog = AddMenuDialog.newInstace(getContext(), this);
                addMenuDialog.show(m.getSupportFragmentManager(), "");
                break;
        }


    }

    @Override
    public void onResult() {
        presenterMenuManager.getListMenu();
    }



}
