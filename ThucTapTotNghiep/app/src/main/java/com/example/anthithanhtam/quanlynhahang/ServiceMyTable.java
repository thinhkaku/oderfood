package com.example.anthithanhtam.quanlynhahang;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.example.anthithanhtam.quanlynhahang.activity.ClientActivity;
import com.example.anthithanhtam.quanlynhahang.database.ApiUtils;
import com.example.anthithanhtam.quanlynhahang.database.SOService;
import com.example.anthithanhtam.quanlynhahang.model.Employee;
import com.example.anthithanhtam.quanlynhahang.model.Status;
import com.example.anthithanhtam.quanlynhahang.sharepreferences.ShareConstand;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceMyTable extends Service {
    private static final String TAG = "ServiceMyTable";
    private SOService soService;
    private List<Status> listStatus, listStatusOld;
    private Handler handler;
    private Runnable runnable;
    private Employee employee;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        if (soService == null) {
            listStatus = new ArrayList<>();
            listStatusOld = new ArrayList<>();
            soService = ApiUtils.getSOService();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {

                handler.postDelayed(this, 10000);

                Log.d(TAG, "Run");
                employee = ShareConstand.getEmployee(ServiceMyTable.this);
                if (employee!=null) {
                    Log.d(TAG, "RunMain");
                    soService.getStatusTable().enqueue(new Callback<List<Status>>() {
                        @Override
                        public void onResponse(Call<List<Status>> call, Response<List<Status>> response) {
                            if (response.body() != null) {
                                listStatus.clear();
                                listStatus.addAll(response.body());
                                if (response.body().size() > 0){
                                    if (listStatus.size() > listStatusOld.size()) {
                                        listStatusOld.clear();
                                        listStatusOld.addAll(listStatus);
                                        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                                        manager.cancel(1);
                                        showNotification(listStatusOld);
                                    } else if (listStatus.size() < listStatusOld.size()) {
                                        listStatusOld.clear();
                                        listStatusOld.addAll(listStatus);
                                        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                                        manager.cancel(1);
                                        showNotification(listStatusOld);
                                    } else {
                                        return;
                                    }
                                }else {
                                    listStatusOld.clear();
                                    listStatusOld.addAll(listStatus);
                                    NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                                    manager.cancel(1);
                                }

                            }
                        }

                        @Override
                        public void onFailure(Call<List<Status>> call, Throwable t) {
                            call.clone().enqueue(this);
                        }
                    });
                }

            }
        };
        handler.post(runnable);
        return Service.START_STICKY;
    }



    private void showNotification(List<Status> listStatus) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setAutoCancel(true);
        builder.setSmallIcon(R.drawable.ic_notifications_active_black_24dp);
        builder.setTicker("Thông báo");

        // Sét đặt thời điểm sự kiện xẩy ra.
        // Các thông báo trên Panel được sắp xếp bởi thời gian này.
        builder.setWhen(System.currentTimeMillis() + 10 * 1000);
        builder.setContentTitle("Thông báo");
        StringBuilder number = new StringBuilder();
        for (Status status : listStatus) {
            number.append(status.getNumber()).append(", ");
        }
        builder.setContentText("Có bàn số " + number.substring(0, number.length() - 2) + " cần thanh toán");
        if (!MainApp.isActivityVisible()){

        }
        Intent resultIntent = new Intent(this, ClientActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(ClientActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        builder.setContentIntent(resultPendingIntent);
        Notification notification = builder.build();
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1, notification);
    }

    @Override
    public void onDestroy() {
        handler.removeCallbacks(runnable);
        Log.d(TAG, "destroy");
        super.onDestroy();
    }
}
