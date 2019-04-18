package com.example.anthithanhtam.quanlynhahang.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.anthithanhtam.quanlynhahang.R;
import com.example.anthithanhtam.quanlynhahang.adapter.MenuManagerAdapter;
import com.example.anthithanhtam.quanlynhahang.model.Menu;
import com.example.anthithanhtam.quanlynhahang.model.Type;
import com.example.anthithanhtam.quanlynhahang.presenter.PresenterMenuManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindAnim;
import butterknife.BindView;

public class FragmentQuanLyThucDon extends BaseFragment implements ViewQuanLyThucDon{
    @BindView(R.id.recyclerMenuMn)
    RecyclerView recyclerMenuMn;
    private MenuManagerAdapter managerAdapter;
    private List<Menu>listMenu;
    private PresenterMenuManager presenterMenuManager;

    @Override
    protected void initView() {
        listMenu=new ArrayList<>();
        managerAdapter=new MenuManagerAdapter(mActivity, listMenu);
        recyclerMenuMn.setLayoutManager(new LinearLayoutManager(mActivity));
        recyclerMenuMn.setHasFixedSize(true);
        recyclerMenuMn.setAdapter(managerAdapter);
        presenterMenuManager=new PresenterMenuManager(soService, this);
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
        this.listMenu.addAll(listMenu);
        managerAdapter.notifyDataSetChanged();
    }

    @Override
    public void getDataType(List<Type> listType) {
        presenterMenuManager.getListMenu(listType.get(0).getTypeId());
    }

    @Override
    public void error() {

    }
}
