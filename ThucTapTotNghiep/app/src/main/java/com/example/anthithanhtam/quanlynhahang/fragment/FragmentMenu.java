package com.example.anthithanhtam.quanlynhahang.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.anthithanhtam.quanlynhahang.R;
import com.example.anthithanhtam.quanlynhahang.activity.ClientActivity;
import com.example.anthithanhtam.quanlynhahang.adapter.MenuAdapter;
import com.example.anthithanhtam.quanlynhahang.constant.Utils;
import com.example.anthithanhtam.quanlynhahang.model.Menu;
import com.example.anthithanhtam.quanlynhahang.model.MyItem;
import com.example.anthithanhtam.quanlynhahang.model.Type;
import com.example.anthithanhtam.quanlynhahang.presenter.PresenterFragmentMenu;
import com.example.anthithanhtam.quanlynhahang.sharepreferences.ShareConstand;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentMenu extends BaseFragment implements ViewFragmentMenu, SwipeRefreshLayout.OnRefreshListener, View.OnTouchListener {
    @BindView(R.id.spDanhMuc)
    Spinner spDanhMuc;
    @BindView(R.id.rcMenu)
    RecyclerView rcMenu;
    @BindView(R.id.swiperefreshMenu)
    SwipeRefreshLayout swiperefreshMenu;
    @BindView(R.id.fabMenu)
    FloatingActionButton fabMenu;
    @BindView(R.id.rlMenu)
    RelativeLayout rlMenu;
    Unbinder unbinder;

    private ClientActivity clientActivity;
    private MenuAdapter menuAdapter;
    private ArrayList<String> arrDanhMuc = new ArrayList<>();
    private ArrayAdapter<String> danhMucAdapter;
    private List<MyItem> arrItem = new ArrayList<>();
    private List<Type> mListType = new ArrayList<>();
    private String[] nu;
    private ArrayList<Menu> arrItemChecked = new ArrayList<>();
    private List<Menu> arrMenu = new ArrayList<>();
    private PresenterFragmentMenu presenterFragmentMenu;
    private int count = 0;

    public FloatingActionButton getBtnGoToBill() {
        return fabMenu;
    }


    private void addEvent() {
        menuAdapter = new MenuAdapter(mActivity, arrMenu);
        rcMenu.setLayoutManager(new LinearLayoutManager(mActivity));
        rcMenu.setHasFixedSize(true);
        rcMenu.setAdapter(menuAdapter);
        menuAdapter.notifyDataSetChanged();
        swiperefreshMenu.setOnRefreshListener(this);
        //fabMenu.setOnTouchListener(this);

    }


    private void xacNhanChonMon() {
        arrItemChecked.clear();
        count = 0;
        for (int i = 0; i < arrMenu.size(); i++) {
            if (!arrMenu.get(i).getCount().equals("0")) {
                arrItemChecked.add(arrMenu.get(i));
            }
        }

        List<Menu> listItemMenu = new ArrayList<>();
        listItemMenu.addAll(arrItemChecked);

        for (Menu itemMenu : arrItemChecked) {
            for (MyItem myItem : arrItem) {
                if (itemMenu.getId().equals(myItem.getMenuId())) {
                    listItemMenu.remove(itemMenu);
                    presenterFragmentMenu.editMyItem(myItem.getId(), itemMenu.getCount(), myItem.getStatus());
                    count++;
                }
            }
        }


        for (int i = 0; i < listItemMenu.size(); i++) {
            String time = Utils.getCurentDateTime();
            count++;
            presenterFragmentMenu.insertMyItem(listItemMenu.get(i).getId(), nu[0], listItemMenu.get(i).getCount(), time);
            if (arrItem.size() == 0) {
                count++;
                presenterFragmentMenu.editTable(nu[0], nu[1], "1", "", time);
            }
        }
    }

    public void reloadData() {
        String people = ShareConstand.getInstance(mActivity).getNumPeo();
        nu = people.split("-");
        data();
    }


    private void initData() {
        String people = ShareConstand.getInstance(mActivity).getNumPeo();
        nu = people.split("-");
        presenterFragmentMenu = new PresenterFragmentMenu(this, soService);
        presenterFragmentMenu.getDataType();
    }


    @Override
    protected void initView() {
        clientActivity = (ClientActivity) mActivity;
        initData();
        addEvent();
    }

    @Override
    protected int layoutID() {
        return R.layout.layout_menu_fragment;
    }

    @Override
    public void getListMenu(List<Menu> listMenu) {
        arrMenu.clear();
        arrMenu.addAll(listMenu);
        data();
    }

    @Override
    public void editMyItem(String message) {
        count--;
        if (count == 0) {
            Utils.toastMessage(clientActivity, "");
            clientActivity.switchFragment(clientActivity.getFragmentBill());
        }
    }

    @Override
    public void insertMyItem(String message) {
        count--;
        if (count == 0) {
            Utils.toastMessage(clientActivity, "");
            clientActivity.switchFragment(clientActivity.getFragmentBill());
        }
    }

    @Override
    public void deleteMyItem(String message) {
        count--;
        if (count == 0) {
            Utils.toastMessage(clientActivity, "");
            clientActivity.switchFragment(clientActivity.getFragmentBill());
        }
    }

    @Override
    public void editTable(String message) {
        count--;
        if (count == 0) {
            Utils.toastMessage(clientActivity, "");
            if (arrItem.size() == 0) {
                clientActivity.switchFragment(clientActivity.getFragmentBill());
            } else {
                clientActivity.switchFragment(clientActivity.getFragmentMainClient());
            }
        }
    }

    @Override
    public void getDataType(List<Type> listType) {
        mListType.clear();
        mListType.addAll(listType);
        arrDanhMuc.clear();
        for (Type type : mListType) {
            arrDanhMuc.add(type.getTypeName());
        }
        danhMucAdapter = new ArrayAdapter<String>(mActivity, android.R.layout.simple_spinner_item, arrDanhMuc);
        danhMucAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDanhMuc.setAdapter(danhMucAdapter);
        spDanhMuc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                presenterFragmentMenu.getListMenu(mListType.get(position).getTypeId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        swiperefreshMenu.setRefreshing(false);
    }

    private void data() {
        soService.getMyItem(nu[0]).enqueue(new Callback<List<MyItem>>() {
            @Override
            public void onResponse(Call<List<MyItem>> call, Response<List<MyItem>> response) {
                if (response.body() != null) {
                    arrItem.clear();
                    arrItem = response.body();
                    for (Menu menu : arrMenu) {
                        menu.setCount("0");
                    }

                    if (arrItem.size() != 0) {
                        for (int i = 0; i < arrItem.size(); i++) {
                            for (int j = 0; j < arrMenu.size(); j++) {
                                if (arrItem.get(i).getMenuId().equals(arrMenu.get(j).getId())) {
                                    arrMenu.get(j).setCount(arrItem.get(i).getCount());
                                }
                            }
                        }
                    } else {
                        for (Menu menu : arrMenu) {
                            menu.setCount("0");
                        }
                    }
                    menuAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<MyItem>> call, Throwable t) {
                call.clone().enqueue(this);
            }
        });

    }

    @Override
    public void error() {
        Toast.makeText(mActivity, getString(R.string.error), Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onRefresh() {
        swiperefreshMenu.setRefreshing(true);
        presenterFragmentMenu.getDataType();
    }

    private int _xDelta;
    private int _yDelta;
    private boolean checkClick;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        final int X = (int) event.getRawX();
        final int Y = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                checkClick = false;
                RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
                _xDelta = X - lParams.leftMargin;
                _yDelta = Y - lParams.topMargin;
                break;
            case MotionEvent.ACTION_UP:
                if (!checkClick) {
                    if (ShareConstand.getActionMenu(mActivity).equals("1")) {
                        xacNhanChonMon();
                    } else {
                        clientActivity.switchFragment(clientActivity.getFragmentBill());
                    }
                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                checkClick = true;
//                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
//                layoutParams.leftMargin = X - _xDelta;
//                layoutParams.topMargin = Y - _yDelta;
//                layoutParams.rightMargin = -250;
//                layoutParams.bottomMargin = -250;
//                fabMenu.setLayoutParams(layoutParams);
                break;
        }
        rcMenu.invalidate();
        return true;
    }


    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return false;
    }



    @OnClick(R.id.fabMenu)
    public void onViewClicked() {
        if (ShareConstand.getActionMenu(mActivity).equals("1")) {
            xacNhanChonMon();
        } else {
            clientActivity.switchFragment(clientActivity.getFragmentBill());
        }
    }
}
