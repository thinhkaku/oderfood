package com.example.quang.orderfood.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

public class MenuManagementActivity extends FragmentActivity implements View.OnClickListener, SearchView.OnQueryTextListener {
    private MenuManagementAdapterPager menuManagementAdapterPager;
    private ArrayList<PagerTitle>pagerTitles;
    private ViewPager viewPager;
    private FoodFragment foodFragment;
    private DrinkFragment drinkFragment;
    private AllFoodFragment allFoodFragment;
    private ImageButton btnBack;
    private FloatingActionButton floatingActionButton;
   private Animation animationButton;
   private SearchView searchView;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_management);
        initView();
        addEvent();
    }



    private void addEvent() {
        btnBack.setOnClickListener(this);
        floatingActionButton.setOnClickListener(this);
    }


    private void initView() {
        pagerTitles=new ArrayList<>();
        foodFragment =new FoodFragment();
        drinkFragment =new DrinkFragment();
        allFoodFragment =new AllFoodFragment();
        pagerTitles.add(new PagerTitle("Tất cả",allFoodFragment));
        pagerTitles.add(new PagerTitle("Thức ăn",foodFragment));
        pagerTitles.add(new PagerTitle("Đồ uống",drinkFragment));
        viewPager =findViewById(R.id.viewPager);
        btnBack  =findViewById(R.id.btnBackManager);
        floatingActionButton =findViewById(R.id.fab);
        animationButton= AnimationUtils.loadAnimation(MenuManagementActivity.this,R.anim.button_apha);
        menuManagementAdapterPager=new MenuManagementAdapterPager(getSupportFragmentManager(),pagerTitles);
        viewPager.setAdapter(menuManagementAdapterPager);
        menuManagementAdapterPager.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnBackManager:
                btnBack.startAnimation(animationButton);
                Intent intent=new Intent(MenuManagementActivity.this,MainForManagerActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.fab:
                Intent intent1 =new Intent(MenuManagementActivity.this,AddItemMenuActivity.class);
                startActivity(intent1);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent=new Intent(MenuManagementActivity.this,MainForManagerActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_view, menu);
        MenuItem itemSearch = menu.findItem(R.id.search_view);
        searchView = (SearchView) itemSearch.getActionView();
        //set OnQueryTextListener cho search view để thực hiện search theo text
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
