package com.example.quang.orderfood.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.quang.orderfood.R;
import com.github.nkzawa.emitter.Emitter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import adapter.MenuManagementAdapterPager;
import fragment.AllFoodFragment;
import fragment.DrinkFragment;
import fragment.FoodFragment;
import objects.ItemMenu;
import objects.PagerTitle;
import singleton.Singleton;

/**
 * Created by Administrator on 3/1/2018.
 */

public class MenuManagementActivity extends AppCompatActivity implements View.OnClickListener {
    private MenuManagementAdapterPager menuManagementAdapterPager;
    private ArrayList<PagerTitle>pagerTitles;
    private static String CLIENT_SEND_MENU="CLIENT_SEND_MENU";
    private static String SERVER_SEND_MENU_DRINK="SERVER_SEND_MENU";
    private ViewPager viewPager;
    private ArrayList<ItemMenu>arrAllFood;
    private FoodFragment foodFragment;
    private DrinkFragment drinkFragment;
    private AllFoodFragment allFoodFragment;
    private ImageButton btnBack;
    Emitter.Listener onResult;
   private Animation animationButton;


    {
        onResult=new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                //resultAllFood(args[0]);
            }

        };
    }

    private void resultAllFood(final Object args) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                JSONArray data = (JSONArray) args;
                arrAllFood.clear();
                Toast.makeText(MenuManagementActivity.this,args.toString(),Toast.LENGTH_SHORT).show();
                for (int i=0; i<data.length(); i++)
                {
                    try {
                        JSONObject object = data.getJSONObject(i);
                        String group = object.getString("tenNhom");
                        String name = object.getString("tenMonAn");
                        String price = object.getString("gia");
                        String unit = object.getString("tenDVTinh");
                        String check = object.getString("tinhTrang");
                        String img = object.getString("anhMonAn");

                        ItemMenu itemMenu = new ItemMenu(group,name,price,unit,check,img,0);
                        arrAllFood.add(itemMenu);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_management);
        //initSocket();
        initView();
        addEvent();
    }

    private void initSocket() {
        Singleton.Instance().getmSocket().emit(CLIENT_SEND_MENU,123);
        Singleton.Instance().getmSocket().on(SERVER_SEND_MENU_DRINK,onResult);
    }

    private void addEvent() {
        btnBack.setOnClickListener(this);
    }


    private void initView() {
        pagerTitles=new ArrayList<>();
        arrAllFood=new ArrayList<>();
        foodFragment =new FoodFragment();
        drinkFragment =new DrinkFragment();
        allFoodFragment =new AllFoodFragment();
        pagerTitles.add(new PagerTitle("Tất cả",allFoodFragment));
        pagerTitles.add(new PagerTitle("Thức ăn",foodFragment));
        pagerTitles.add(new PagerTitle("Đồ uống",drinkFragment));
        viewPager =findViewById(R.id.viewPager);
        btnBack  =findViewById(R.id.btnBackManager);
        animationButton= AnimationUtils.loadAnimation(MenuManagementActivity.this,R.anim.button_apha);
        menuManagementAdapterPager=new MenuManagementAdapterPager(getSupportFragmentManager(),pagerTitles);
        viewPager.setAdapter(menuManagementAdapterPager);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnBackManager:
                btnBack.startAnimation(animationButton);
                initSocket();
                Toast.makeText(MenuManagementActivity.this,arrAllFood.toString(),Toast.LENGTH_SHORT).show();
                //finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //finish();
    }
}
