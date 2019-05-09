package com.example.anthithanhtam.quanlynhahang.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.anthithanhtam.quanlynhahang.R;
import com.example.anthithanhtam.quanlynhahang.activity.ClientActivity;
import com.example.anthithanhtam.quanlynhahang.activity.ManagerActivity;
import com.example.anthithanhtam.quanlynhahang.activity.SwichBeginAppActivity;
import com.example.anthithanhtam.quanlynhahang.constant.Utils;
import com.example.anthithanhtam.quanlynhahang.model.Employee;
import com.example.anthithanhtam.quanlynhahang.sharepreferences.ShareConstand;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentLoginAdmin extends BaseFragment {
    @BindView(R.id.edtUserAdmin)
    EditText edtUserAdmin;
    @BindView(R.id.edtPassAdmin)
    EditText edtPassAdmin;
    @BindView(R.id.btnLoginAdmin)
    Button btnLoginAdmin;
    private SwichBeginAppActivity swichBeginAppActivity;

    @Override
    protected void initView() {
        swichBeginAppActivity= (SwichBeginAppActivity) mActivity;
    }

    @Override
    protected int layoutID() {
        return R.layout.layout_login_admin_fragment;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return false;
    }


    @OnClick(R.id.btnLoginAdmin)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnLoginAdmin:
                String userName = edtUserAdmin.getText().toString();
                String pass = edtPassAdmin.getText().toString();
                if (userName.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(mActivity, "Chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    swichBeginAppActivity.getProgressBarSwitchBegin().setVisibility(View.VISIBLE);
                    soService.checkLogin(userName, pass).enqueue(new Callback<List<Employee>>() {
                        @Override
                        public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                            if (response.body().size() == 0) {
                                Toast.makeText(mActivity, getString(R.string.pass_false), Toast.LENGTH_SHORT).show();
                            } else {
                                if (response.body().get(0).getChucVu().equals("0")){
                                    ShareConstand.setEmployee(mActivity, response.body().get(0));
                                    //Toast.makeText(context, getString(R.string.login_success), Toast.LENGTH_SHORT).show();
                                    Utils.toastMessage(swichBeginAppActivity, getString(R.string.success));
                                    Intent i = new Intent(mActivity, ManagerActivity.class);
                                    startActivity(i);
                                }else {
                                    Toast.makeText(mActivity, getString(R.string.pass_false), Toast.LENGTH_SHORT).show();
                                }

                            }
                            swichBeginAppActivity.getProgressBarSwitchBegin().setVisibility(View.GONE);
                        }

                        @Override
                        public void onFailure(Call<List<Employee>> call, Throwable t) {
                            call.clone().enqueue(this);
                        }
                    });
                }
                break;
        }
    }
}
