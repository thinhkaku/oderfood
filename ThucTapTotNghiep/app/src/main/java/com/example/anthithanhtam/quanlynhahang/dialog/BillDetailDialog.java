package com.example.anthithanhtam.quanlynhahang.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anthithanhtam.quanlynhahang.R;

public class BillDetailDialog extends DialogFragment {
    public BillDetailDialog() {
    }

    public static BillDetailDialog newInstance(){
        BillDetailDialog billDetailDialog=new BillDetailDialog();
        billDetailDialog.setStyle(STYLE_NO_FRAME, android.R.style.Theme_Holo_Light);
        return billDetailDialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_bill_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
