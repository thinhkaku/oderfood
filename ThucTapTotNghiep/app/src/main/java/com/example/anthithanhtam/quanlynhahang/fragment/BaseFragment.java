package com.example.anthithanhtam.quanlynhahang.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.anthithanhtam.quanlynhahang.database.ApiUtils;
import com.example.anthithanhtam.quanlynhahang.database.SOService;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {
    protected SOService soService;
    private Unbinder unbinder;
    protected Activity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity= (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initViewInstance(inflater,container);
    }
    private View initViewInstance(LayoutInflater inflater,ViewGroup container)
    {
        soService= ApiUtils.getSOService();
        View view=inflater.inflate(layoutID(),container,false);
        unbinder= ButterKnife.bind(this, view);
        initView();
        return view;
    }

    protected abstract void initView();

    protected abstract int layoutID();

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    public abstract boolean onTouchEvent(MotionEvent e);
}
