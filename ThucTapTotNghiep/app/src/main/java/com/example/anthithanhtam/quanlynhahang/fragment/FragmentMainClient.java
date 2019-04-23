package com.example.anthithanhtam.quanlynhahang.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anthithanhtam.quanlynhahang.R;
import com.example.anthithanhtam.quanlynhahang.activity.ClientActivity;
import com.example.anthithanhtam.quanlynhahang.adapter.TableAdapter;
import com.example.anthithanhtam.quanlynhahang.model.MyTable;
import com.example.anthithanhtam.quanlynhahang.presenter.PresenterMainClient;
import com.example.anthithanhtam.quanlynhahang.sharepreferences.ShareConstand;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FragmentMainClient extends BaseFragment implements AdapterView.OnItemClickListener, ViewFragmentMainClient, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.txtBanTrong)
    TextView txtBanTrong;
    @BindView(R.id.txtBanDaDat)
    TextView txtBanDaDat;
    @BindView(R.id.gVTable)
    GridView gVTable;
    @BindView(R.id.swipeRefreshTable)
    SwipeRefreshLayout swipeRefreshTable;
    private ClientActivity clientActivity;
    private TableAdapter tableAdapter;
    private List<MyTable> arrTable = new ArrayList<>();
    private int number;
    private String people;
    private Dialog dialogPeople;
    private PresenterMainClient presenterMainClient;


    public void getData() {
        clientActivity.getLnPRClient().setVisibility(View.VISIBLE);
        presenterMainClient.getListMyTable();
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.gVTable:
                MyTable table = arrTable.get(position);
                int check = Integer.parseInt(table.getCheck());
                number = Integer.parseInt(table.getNumber());
                if (check == 0) {
                    dialogPeople.show();
                } else {
                    ShareConstand.getInstance(mActivity).setNumpeo(number + "-" + table.getNumPeople());
                    clientActivity.switchFragment(clientActivity.getFragmentBill());
                }
                break;
        }
    }


    private void initDialogPeople() {
        dialogPeople = new Dialog(mActivity, android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth);
        dialogPeople.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogPeople.setContentView(R.layout.dialog_check_number);
        dialogPeople.setCancelable(false);
        final EditText edtPeople = dialogPeople.findViewById(R.id.edtPeopleDialog);
        final Button btnExit = dialogPeople.findViewById(R.id.btnExitDialog);
        final Button btnDone = dialogPeople.findViewById(R.id.btnDoneDialog);

        btnExit.setOnClickListener(view -> dialogPeople.dismiss());
        btnDone.setOnClickListener(view -> {

            people = edtPeople.getText().toString();
            if (people.isEmpty()) {
                Snackbar snackbar = Snackbar
                        .make(edtPeople, getString(R.string.no_enter_number_table), Snackbar.LENGTH_SHORT);
                snackbar.setActionTextColor(Color.WHITE);
                View snackbarView = snackbar.getView();
                snackbarView.setBackgroundColor(Color.DKGRAY);
                snackbar.show();
            } else {
                ShareConstand.getInstance(mActivity).setNumpeo(number + "-" + people);
                clientActivity.switchFragment(clientActivity.getFragmentMenu());
                dialogPeople.dismiss();
            }
            edtPeople.setText("");
        });
    }

    @Override
    protected void initView() {
        clientActivity = (ClientActivity) mActivity;
        presenterMainClient = new PresenterMainClient(this, soService);
        swipeRefreshTable.setOnRefreshListener(this);
        tableAdapter = new TableAdapter(mActivity, R.layout.item_gridview, arrTable);
        gVTable.setAdapter(tableAdapter);
        getData();
        initDialogPeople();
        gVTable.setOnItemClickListener(this);
    }

    @Override
    protected int layoutID() {
        return R.layout.layout_main_client_fragment;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return false;
    }



    @Override
    public void listMyTable(List<MyTable> listMyTable) {
        int numTable=0;
        for (MyTable myTable:listMyTable)
        {
            if (myTable.getCheck().equals("1"))
            {
                numTable++;
            }
        }
        txtBanDaDat.setText(String.valueOf(numTable));
        txtBanTrong.setText(String.valueOf(listMyTable.size()-numTable));
        swipeRefreshTable.setRefreshing(false);
        arrTable.clear();
        arrTable.addAll(listMyTable);
        tableAdapter.notifyDataSetChanged();
        clientActivity.getLnPRClient().setVisibility(View.GONE);
    }

    @Override
    public void error() {
        Toast.makeText(mActivity, getString(R.string.error), Toast.LENGTH_SHORT).show();
        clientActivity.getLnPRClient().setVisibility(View.GONE);
    }

    @Override
    public void onRefresh() {
        getData();
    }



}
