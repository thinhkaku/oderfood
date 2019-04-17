package com.example.anthithanhtam.quanlynhahang.fragment;

import android.support.v7.app.AlertDialog;
;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anthithanhtam.quanlynhahang.R;
import com.example.anthithanhtam.quanlynhahang.activity.ClientActivity;
import com.example.anthithanhtam.quanlynhahang.adapter.BillAdapter;
import com.example.anthithanhtam.quanlynhahang.constant.Utils;
import com.example.anthithanhtam.quanlynhahang.model.Employee;
import com.example.anthithanhtam.quanlynhahang.model.MyItem;
import com.example.anthithanhtam.quanlynhahang.presenter.PresenterFragmentBill;
import com.example.anthithanhtam.quanlynhahang.sharepreferences.ShareConstand;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class FragmentBill extends BaseFragment implements ViewFragmentBill {
    @BindView(R.id.tvTableBill)
    TextView tvTableBill;
    @BindView(R.id.tvPeopleBill)
    TextView tvPeopleBill;
    @BindView(R.id.tvTimeBill)
    TextView tvTimeBill;
    @BindView(R.id.tvTotalBill)
    TextView tvTotalBill;
    @BindView(R.id.rcItemBill)
    RecyclerView rcItemBill;
    @BindView(R.id.btnPushBill)
    ImageView btnPushBill;
    @BindView(R.id.btnAddItem)
    ImageView btnAddItem;
    @BindView(R.id.btnPrintBill)
    ImageView btnPrintBill;
    private BillAdapter listviewBillAdapter;
    private List<MyItem> arrItem;
    private String people = "0";

    private String[] nu;
    private ClientActivity clientActivity;
    private PresenterFragmentBill fragmentBillPresenter;
    private int count = 0;

    @Override
    protected void initView() {
        this.clientActivity = (ClientActivity) mActivity;
        fragmentBillPresenter = new PresenterFragmentBill(this, soService);
    }


    @Override
    protected int layoutID() {
        return R.layout.layout_bill_fragment;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return false;
    }

    public void moveData() {
        people = ShareConstand.getInstance(clientActivity).getNumPeo();
        nu = people.split("-");
        tvTableBill.setText("Bàn số: " + nu[0]);
        tvPeopleBill.setText("Số người: " + nu[1]);
        tvTimeBill.setText("Thời gian: " + Utils.fomatDateTimeDD_MM_YYYY_HH_MM(Utils.getCurentDateTime()));
        arrItem = new ArrayList<>();

        listviewBillAdapter = new BillAdapter(clientActivity, arrItem, tvTotalBill, fragmentBillPresenter);
        rcItemBill.setLayoutManager(new LinearLayoutManager(mActivity));
        rcItemBill.setHasFixedSize(true);
        rcItemBill.setAdapter(listviewBillAdapter);
        listviewBillAdapter.notifyDataSetChanged();
        getData(nu[0]);
    }

    public void getData(String numberTable) {
        clientActivity.getLnPRClient().setVisibility(View.VISIBLE);
        fragmentBillPresenter.getListMyItem(numberTable);
    }


    @OnClick({R.id.btnPushBill, R.id.btnAddItem, R.id.btnPrintBill})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnAddItem:
                ShareConstand.getInstance(clientActivity).setNumpeo(people);
                clientActivity.switchFragment(clientActivity.getFragmentMenu());
                break;
            case R.id.btnPushBill:
                if (ShareConstand.getActionBill(clientActivity).equals("1")) {
                    count = 0;
                    for (MyItem item : arrItem) {
                        count++;
                        if (item.getCount().equals("0")) {
                            fragmentBillPresenter.deleteMyItem(item.getId());
                        } else {
                            fragmentBillPresenter.editMyItem(item.getId(), item.getCount(), item.getStatus());
                        }
                    }
                } else {
                    clientActivity.switchFragment(clientActivity.getFragmentMainClient());
                }
                break;
            case R.id.btnPrintBill:
                Employee employee = ShareConstand.getEmployee(clientActivity);
                if (employee.getTenNhanVien() == null) {
                    pushClient();

                } else {
                    pushCustomer();
                }
                break;
        }
    }

    private void pushClient() {

    }

    private void pushCustomer() {
        showDialogConfigPrint();
    }

    private void showDialogConfigPrint() {
        AlertDialog.Builder builder = new AlertDialog.Builder(clientActivity);
        builder.setTitle(getString(R.string.access));
        String t = tvTotalBill.getText().toString();
        String[] a = t.split("Tổng tiền:");
        a[1] = a[1].trim();
        builder.setMessage("Xuất hóa đơn? Với tổng tiền là: " + a[1]);
        builder.setCancelable(false);
        builder.setPositiveButton(getString(R.string.cancle), (dialogInterface, i) -> dialogInterface.dismiss());
        builder.setNegativeButton(getString(R.string.export), (dialogInterface, i) -> printBill());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    private void printBill() {
        fragmentBillPresenter.insertBill(nu[0], nu[1], ShareConstand.getEmployee(clientActivity).getMaNhanVien(), Utils.getCurentDateTime());
    }


    @Override
    public void getListMyItem(List<MyItem> listMyItem) {
        arrItem.clear();
        arrItem.addAll(listMyItem);
        listviewBillAdapter.notifyDataSetChanged();
        listviewBillAdapter.getPrice();
        clientActivity.getLnPRClient().setVisibility(View.GONE);
    }

    @Override
    public void deleteMyItem(String message) {
        count--;
        if (count == 0) {
            Utils.toastMessage(clientActivity, "");
            clientActivity.switchFragment(clientActivity.getFragmentMainClient());
        }
    }

    @Override
    public void onSuccess() {
        count--;
        if (count == 0) {
            Utils.toastMessage(clientActivity, "");
            clientActivity.switchFragment(clientActivity.getFragmentMainClient());
        }
    }

    @Override
    public void onSuccessInsertBill(String id) {
        for (int i = 0; i < arrItem.size(); i++) {
            count++;
            fragmentBillPresenter.insertBillDetail(id, arrItem.get(i).getMenuId(), arrItem.get(i).getCount(), Utils.getCurentDateTime());
        }
    }

    @Override
    public void onSuccessInsertBillDetail() {
        count--;
        if (count == 0) {
            count++;
            fragmentBillPresenter.editMyTable(nu[0], "0", "0","",Utils.getCurentDateTime());
            fragmentBillPresenter.deleteAllMyitem(nu[0]);
        }
    }


    @Override
    public void error() {
        Toast.makeText(clientActivity, getString(R.string.error), Toast.LENGTH_SHORT).show();
    }


}
