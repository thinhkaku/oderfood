package com.example.anthithanhtam.quanlynhahang.database;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.example.anthithanhtam.quanlynhahang.model.Item;
import com.example.anthithanhtam.quanlynhahang.model.ItemMenu;
import com.example.anthithanhtam.quanlynhahang.model.Table;
import com.example.anthithanhtam.quanlynhahang.model.User;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.Sort;

public class RealmControler {
    private  Realm realm;
    public RealmControler() {
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(config);
    }


    public RealmResults<Table> getBooks() {
        realm.beginTransaction();
        RealmResults<Table> rs = realm.where(Table.class).findAllSorted("number", Sort.ASCENDING);
        realm.commitTransaction();
        return rs;
    }

    public void inserTable(Table table){
        realm.beginTransaction();
        if (realm.where(Table.class).findAll().size() > 0) {
            int id_new = realm.where(Table.class).max("number").intValue() + 1;
            table.setNumber(id_new);
        } else {

            table.setNumber(1);
        }
        realm.insertOrUpdate(table);
        realm.commitTransaction();
    }

    public void update(Table table) {
        realm.beginTransaction();
        realm.insertOrUpdate(table);
        realm.commitTransaction();
    }

    public void inserItemMenu(ItemMenu itemMenu){
        realm.beginTransaction();
        if (realm.where(ItemMenu.class).findAll().size() > 0) {
            int id_group = realm.where(ItemMenu.class).max("id").intValue() + 1;
            itemMenu.setGroup(id_group);
        } else {

            itemMenu.setGroup(1);
        }
        realm.insertOrUpdate(itemMenu);
        realm.commitTransaction();
    }

    public RealmResults<ItemMenu> getItemMenu() {
        realm.beginTransaction();
        RealmResults<ItemMenu> rs = realm.where(ItemMenu.class).findAllSorted("group", Sort.ASCENDING);
        realm.commitTransaction();
        return rs;
    }

    public void inserItem(Item itemMenu){
        realm.beginTransaction();
        if (realm.where(Item.class).findAll().size() > 0) {
            int id_group = realm.where(Item.class).max("id").intValue() + 1;
            itemMenu.setGroup(id_group);
        } else {

            itemMenu.setGroup(1);
        }
        realm.insertOrUpdate(itemMenu);
        realm.commitTransaction();

    }

    public void updateItem(Item itemMenu){

        realm.beginTransaction();
        realm.insertOrUpdate(itemMenu);
        realm.commitTransaction();
    }



    public RealmResults<Item> getItem(int numberTable) {
        realm.beginTransaction();
        RealmResults<Item> rs = realm.where(Item.class).equalTo("numberTable",numberTable).findAllSorted("group", Sort.ASCENDING);
        realm.commitTransaction();
        return rs;
    }

    public RealmResults<User>getUser(String user, String pass)
    {
        realm.beginTransaction();
        RealmResults<User> result= realm.where(User.class).equalTo("userUserName", user).equalTo("userPassWord", pass).findAll();
        realm.commitTransaction();
        return result;
    }

    public  void  registerUser(User user){
        realm.beginTransaction();
        if (realm.where(User.class).findAll().size() > 0) {
            int id_group = realm.where(User.class).max("userId").intValue() + 1;
            Log.e("userID", id_group+"");
            user.setUserId(id_group);
        } else {

            user.setUserId(1);
        }
        realm.insertOrUpdate(user);
        realm.commitTransaction();
    }

    public  void deleteItem(Item item){
        realm.beginTransaction();
        //item.deleteFromRealm();
        RealmResults<Item> result = realm.where(Item.class).equalTo("numberTable",item.getNumberTable()).findAll();
        result.deleteAllFromRealm();
        realm.commitTransaction();
    }

}
