package com.example.anthithanhtam.quanlynhahang.sharepreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.anthithanhtam.quanlynhahang.model.Employee;
import com.example.anthithanhtam.quanlynhahang.model.User;
import com.google.gson.Gson;

public class ShareConstand {
    private static SharedPreferences mSettings;
    private static SharedPreferences.Editor mEditor;

    public ShareConstand() {
    }
    private static class SingletonHelper {
        private static final ShareConstand INSTANCE = new ShareConstand();
    }
    public static ShareConstand getInstance(Context mContext) {
        mSettings = mContext.getSharedPreferences("share_app_odderfood", 0);
        mEditor = mSettings.edit();
        return SingletonHelper.INSTANCE;
    }

    public void setNumpeo(String numpeo){
        mEditor.putString("numpeo", numpeo);
        mEditor.apply();
    }

    public void setSetCheckAddTable(int checkAddTable){
        mEditor.putInt("checktable", checkAddTable);
        mEditor.apply();
    }

    public  int getCheckAddTable(){
        return mSettings.getInt("checktable",0);
    }

    public  String getNumPeo(){
        return mSettings.getString("numpeo","");
    }

    public static User getUser(Context context){
        Gson gson = new Gson();
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        String json = appSharedPrefs.getString("save_user", "");
        return gson.fromJson(json, User.class);
    }

    public static Employee getEmployee(Context context){
        Gson gson = new Gson();
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        String json = appSharedPrefs.getString("Employee", "");
        return gson.fromJson(json, Employee.class);
    }

    public static void setEmployee(Context context, Employee employee){
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(employee);
        prefsEditor.putString("Employee", json);
        prefsEditor.commit();
    }

    public static String getActionBill(Context context){
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        return appSharedPrefs.getString("action_bill", "");

    }

    public static void setActionBill(Context context,String username){
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("action_bill", username);
        prefsEditor.commit();
    }

    public static String getActionMenu(Context context){
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        return appSharedPrefs.getString("action_menu", "");
    }

    public static void setActionMenu(Context context,String username){
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("action_menu", username);
        prefsEditor.commit();
    }

    public static String getUserPass(Context context){
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        return appSharedPrefs.getString("pass", "");
    }



    public static void setUserPass(Context context,String pass){
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("pass", pass);
        prefsEditor.commit();
    }
}
