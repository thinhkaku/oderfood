package com.example.anthithanhtam.quanlynhahang;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;


public class BoardCastReciver extends BroadcastReceiver {
    private static final String TAG = "BoardCastReciver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "true");
        Toast.makeText(context, "Bật thiết bị", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(context, ServiceMyTable.class);
        if (checkInternet(context)) {
            context.startService(i);
        }
    }

    boolean checkInternet(Context context) {
        ServiceManager serviceManager = new ServiceManager(context);
        if (serviceManager.isNetworkAvailable()) {
            return true;
        } else {
            return false;
        }
    }

}
