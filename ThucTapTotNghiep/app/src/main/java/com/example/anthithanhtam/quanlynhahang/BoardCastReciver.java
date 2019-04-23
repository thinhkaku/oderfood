package com.example.anthithanhtam.quanlynhahang;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;

public class BoardCastReciver extends BroadcastReceiver {
    private static boolean checkInternet;
    private static final String TAG = "BoardCastReciver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG,"true");
//        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        if (connectivityManager.getActiveNetworkInfo() != null) {
//            checkInternet=true;
//
//        } else {
//            checkInternet=false;
//            Log.d(TAG,"false");
//        }
    }

    public static boolean isInternet(){
        return checkInternet;
    }


}
