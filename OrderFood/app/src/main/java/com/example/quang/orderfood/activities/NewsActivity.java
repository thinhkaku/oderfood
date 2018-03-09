package com.example.quang.orderfood.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.quang.orderfood.R;
import com.github.nkzawa.emitter.Emitter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import adapter.NewsAdapter;
import objects.News;
import objects.Table;
import singleton.Singleton;

/**
 * Created by Administrator on 3/9/2018.
 */

public class NewsActivity extends AppCompatActivity {

    private String CLIENT_SEND_NEWS="CLIENT_SEND_NEWS";
    private String SEVER_SEND_NEWS="SEVER_SEND_NEWS";
    private ArrayList<News>arrNews;
    private NewsAdapter newsAdapter;
    private ListView lvNews;
    private ImageButton btnBackNews;
    private Emitter.Listener onResult;
    private Animation animationButton;


    {
        onResult =new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                getNews(args[0]);
            }
        };
    }

    private void getNews(final Object arg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                JSONArray data = (JSONArray) arg;
                arrNews.clear();
                for (int i=data.length()-1; i>=0; i--)
                {
                    try {
                        JSONObject object = data.getJSONObject(i);
                        int id = object.getInt("id");
                        String tenNguoiDang = object.getString("tenNguoiDang");
                        String noiDung = object.getString("noiDung");
                        String hinhAnh = object.getString("hinhAnh");
                        String thoiGian=object.getString("thoiGian");
                        News news=new News(id,tenNguoiDang,hinhAnh,noiDung,thoiGian);
                        arrNews.add(news);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                newsAdapter =new NewsAdapter(NewsActivity.this,arrNews);
                lvNews.setAdapter(newsAdapter);
                newsAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        initSocket();
        initView();
        addEvent();
    }

    private void addEvent() {
        btnBackNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initView() {
        arrNews =new ArrayList<>();
        lvNews =findViewById(R.id.lvThongTin);
        btnBackNews=findViewById(R.id.btnBackNews);
        animationButton=AnimationUtils.loadAnimation(NewsActivity.this,R.anim.button_apha);
    }

    private void initSocket() {
        Singleton.Instance().getmSocket().emit(CLIENT_SEND_NEWS,123);
        Singleton.Instance().getmSocket().on(SEVER_SEND_NEWS,onResult);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finish();
    }
}
