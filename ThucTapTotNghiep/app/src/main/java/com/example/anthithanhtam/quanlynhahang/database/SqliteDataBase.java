package com.example.anthithanhtam.quanlynhahang.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import com.example.anthithanhtam.quanlynhahang.model.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class SqliteDataBase  {
    public static final String PATH = Environment.getDataDirectory().getPath()+"/data/com.example.anthithanhtam.quanlynhahang/database/";
    public static final String  DB_NAME = "orderfood.db";
    public static final String USER_ID="user_id";
    public static final String USER_USER_NAME="user_username";
    public static final String USER_PASSWORD="user_password";
    public static final String USER_NAME="user_name";
    public static final String USER_ADDRESS="user_address";
    public static final String USER_PHONE="user_phone";
    public static final String USER_EMAIL="user_email";
    public static final String USER_IMAGE="user_image";
    public static final String USER_TITLE="user_title";
    public static final String USER_ROLE="user_role";
    private  Context context;
    private SQLiteDatabase database;

    public SqliteDataBase(Context context){
        this.context = context;
        copyDatabaseToProject();
    }
    private void  copyDatabaseToProject(){
        try {
            File file = new File(PATH+DB_NAME);
            if (file.exists()){
                return;
            }
            file.getParentFile().mkdirs();
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            InputStream inputStream = context.getAssets().open(DB_NAME);
            byte []b= new byte[1024];
            int count = inputStream.read(b);
            while (count!=-1){
                fileOutputStream.write(b,0,count);
                count= inputStream.read(b);
            }

            fileOutputStream.close();
            inputStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public  void openDataBase(){
        database = context.openOrCreateDatabase(PATH+DB_NAME, Context.MODE_PRIVATE,null);

    }
    public void closeDataBase(){
        database.close();
    }


    public  ArrayList<User> CheckLoginClient(String name, String userPass){
        ArrayList<User> listUser = new ArrayList<>();
        ArrayList<User> listUser1 = new ArrayList<>();
        openDataBase();
        Cursor cursor = database.query("user",null,null,null,null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()== false){
            int id = cursor.getInt(0);
            String userUserName = cursor.getString(1);
            String pass = cursor.getString(2);
            String userName = cursor.getString(3);
            String userAddress = cursor.getString(1);
            int userPhone = cursor.getInt(5);
            String userEmail = cursor.getString(6);
            byte[] userImage = cursor.getBlob(7);
            String userTitle = cursor.getString(8);
            int userRole = cursor.getInt(9);

            User user = new User(id,userUserName,pass,userName,userAddress,userPhone,userEmail,userImage,userTitle,userRole);
            listUser.add(user);
            cursor.moveToNext();
        }
        closeDataBase();
        for (int i=0;i<listUser.size();i++){
            if (listUser.get(i).getUserUserName().equals(name) && listUser.get(i).getUserPassWord().equals(userPass) && listUser.get(i).getUserRole()==1){
                listUser1.add(listUser.get(i));
            }
        }
        return listUser1;
    }

    public  ArrayList<User> CheckLoginAdmin(String name, String userPass){
        ArrayList<User> listUser = new ArrayList<>();
        ArrayList<User> listUser1 = new ArrayList<>();
        openDataBase();
        Cursor cursor = database.query("user",null,null,null,null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()== false){
            int id = cursor.getInt(0);
            String userUserName = cursor.getString(1);
            String pass = cursor.getString(2);
            String userName = cursor.getString(3);
            String userAddress = cursor.getString(4);
            int userPhone = cursor.getInt(5);
            String userEmail = cursor.getString(6);
            byte[] userImage = cursor.getBlob(7);
            String userTitle = cursor.getString(8);
            int userRole = cursor.getInt(9);

            User user = new User(id,userUserName,pass,userName,userAddress,userPhone,userEmail,userImage,userTitle,userRole);
            listUser.add(user);
            cursor.moveToNext();
        }
        closeDataBase();
        for (int i=0;i<listUser.size();i++){
            if (listUser.get(i).getUserUserName().equals(name) && listUser.get(i).getUserPassWord().equals(userPass) && listUser.get(i).getUserRole()==0){
                listUser1.add(listUser.get(i));
            }
        }
        return listUser1;
    }

    public long insert(User user){
        ContentValues values = new ContentValues();
        values.putNull(USER_ID);
        values.put(USER_USER_NAME,user.getUserUserName());
        values.put(USER_PASSWORD,user.getUserPassWord());
        values.put(USER_NAME,user.getUserName());
        values.put(USER_ADDRESS,user.getUserAddress());
        values.put(USER_PHONE,user.getUserPhone());
        values.put(USER_EMAIL,user.getUserEmail());
        values.put(USER_IMAGE,user.getUserImage());
        values.put(USER_TITLE,user.getUserTitle());
        values.put(USER_ROLE,user.getUserRole());
        openDataBase();
        long id = database.insert("user", null, values);
        closeDataBase();
        return id;
    }


}
