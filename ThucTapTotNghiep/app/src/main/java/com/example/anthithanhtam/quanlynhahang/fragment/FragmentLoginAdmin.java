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
import com.example.anthithanhtam.quanlynhahang.activity.ManagerActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FragmentLoginAdmin extends BaseFragment {
    @BindView(R.id.edtUserAdmin)
    EditText edtUserAdmin;
    @BindView(R.id.edtPassAdmin)
    EditText edtPassAdmin;
    @BindView(R.id.btnLoginAdmin)
    Button btnLoginAdmin;


    @Override
    protected void initView() {
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
                    Intent i = new Intent(mActivity, ManagerActivity.class);
                    startActivity(i);
                }
                break;
        }
    }
}
