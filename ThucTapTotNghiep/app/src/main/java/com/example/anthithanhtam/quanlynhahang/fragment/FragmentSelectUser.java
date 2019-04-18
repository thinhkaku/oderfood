package com.example.anthithanhtam.quanlynhahang.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.anthithanhtam.quanlynhahang.R;
import com.example.anthithanhtam.quanlynhahang.activity.ClientActivity;
import com.example.anthithanhtam.quanlynhahang.activity.SwichBeginAppActivity;
import com.example.anthithanhtam.quanlynhahang.model.Employee;
import com.example.anthithanhtam.quanlynhahang.sharepreferences.ShareConstand;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FragmentSelectUser extends BaseFragment {
    Unbinder unbinder;
    private SwichBeginAppActivity swichBeginAppActivity;


    @Override
    protected void initView() {
        this.swichBeginAppActivity = (SwichBeginAppActivity) mActivity;
    }

    @Override
    protected int layoutID() {
        return R.layout.layout_select_user_fragment;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        Log.d("fra: ", e.getX()+"");
        return true;
    }


    @OnClick({R.id.btnClient, R.id.btnEmployee, R.id.btnAdmin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnEmployee:
                swichBeginAppActivity.switchFragment(swichBeginAppActivity.getFragmentLoginClient());
                break;
            case R.id.btnClient:
                ShareConstand.setEmployee(getContext(), new Employee());
                Intent i = new Intent(mActivity, ClientActivity.class);
                startActivity(i);

                break;
            case R.id.btnAdmin:
                swichBeginAppActivity.switchFragment(swichBeginAppActivity.getFragmentLoginAdmin());
                break;
        }
    }
}
