package com.example.anthithanhtam.quanlynhahang.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.anthithanhtam.quanlynhahang.R;
import com.example.anthithanhtam.quanlynhahang.constant.Utils;
import com.example.anthithanhtam.quanlynhahang.fragment.FragmentLoginAdmin;
import com.example.anthithanhtam.quanlynhahang.fragment.FragmentLoginClient;
import com.example.anthithanhtam.quanlynhahang.fragment.FragmentRegister;
import com.example.anthithanhtam.quanlynhahang.fragment.FragmentSelectUser;

public class SwichBeginAppActivity extends BaseActivity {
    private FragmentSelectUser fragmentSelectUser;
    private FragmentLoginClient fragmentLoginClient;
    private FragmentLoginAdmin fragmentLoginAdmin;
    private FragmentRegister fragmentRegister;
    private LinearLayout progressBarSwitchBegin;

    public LinearLayout getProgressBarSwitchBegin() {
        return progressBarSwitchBegin;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int layoutID() {
        return R.layout.activity_swich_begin_app;
    }

    @Override
    protected void initView() {
        progressBarSwitchBegin=findViewById(R.id.lnPRClient);
        initFragment();
    }

    private void initFragment(){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentSelectUser=new FragmentSelectUser();
        fragmentLoginClient=new FragmentLoginClient();
        fragmentLoginAdmin= new FragmentLoginAdmin();
        fragmentRegister=new FragmentRegister();
        fragmentTransaction.add(R.id.frame,fragmentSelectUser);
        fragmentTransaction.add(R.id.frame,fragmentLoginClient);
        fragmentTransaction.add(R.id.frame,fragmentLoginAdmin);
        fragmentTransaction.add(R.id.frame,fragmentRegister);
        fragmentTransaction.commit();
        switchFragment(fragmentSelectUser);
    }

    public void switchFragment(Fragment fragment)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.hide(fragmentSelectUser);
        fragmentTransaction.hide(fragmentLoginClient);
        fragmentTransaction.hide(fragmentLoginAdmin);
        fragmentTransaction.hide(fragmentRegister);
        fragmentTransaction.show(fragment);
        fragmentTransaction.commit();
    }

    public void setData(String userName, String pass)
    {
        fragmentLoginClient.setData(userName,pass);
    }
    @Override
    protected void addEvent() {

    }

    public FragmentRegister getFragmentRegister() {
        return fragmentRegister;
    }

    public FragmentSelectUser getFragmentSelectUser() {
        return fragmentSelectUser;
    }

    public FragmentLoginClient getFragmentLoginClient() {
        return fragmentLoginClient;
    }

    public FragmentLoginAdmin getFragmentLoginAdmin() {
        return fragmentLoginAdmin;
    }

    @Override
    public void onBackPressed() {
        if (fragmentSelectUser.isVisible())
        {
            Utils.dilogQuitApp(this);
        }else {
            switchFragment(fragmentSelectUser);
        }
    }

}
