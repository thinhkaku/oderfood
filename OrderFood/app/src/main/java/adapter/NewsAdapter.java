package adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.quang.orderfood.R;

import java.util.ArrayList;
import java.util.List;

import consts.Constants;
import de.hdodenhof.circleimageview.CircleImageView;
import objects.News;

/**
 * Created by Administrator on 3/9/2018.
 */

public class NewsAdapter extends ArrayAdapter<News> {
    private ArrayList<News>arrNews=new ArrayList<>();
    private LayoutInflater layoutInflater;
    public NewsAdapter(@NonNull Context context, @NonNull ArrayList<News> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
        layoutInflater=LayoutInflater.from(context);
        arrNews=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View v, @NonNull ViewGroup parent) {
        ViewHodler viewHodler;
        if(v ==null){
            viewHodler =new ViewHodler();
            v =layoutInflater.inflate(R.layout.item_news,parent,false);
            viewHodler.imgHinh =v.findViewById(R.id.imgAnhDaiDien);
            viewHodler.txtNoiDung=v.findViewById(R.id.txtNoiDung);
            viewHodler.txtTen =v.findViewById(R.id.txtTen);
            viewHodler.txtTime =v.findViewById(R.id.txtTime);
            v.setTag(viewHodler);
        }else {
            viewHodler= (ViewHodler) v.getTag();
        }
        News news =arrNews.get(position);
        Glide.with(getContext()).load(Constants.PORT+news.getHinhAnh()).into(viewHodler.imgHinh);
        viewHodler.txtTime.setText(news.getThoiGian());
        viewHodler.txtNoiDung.setText(news.getNoiDung());
        viewHodler.txtTen.setText(news.getTen());
        return v;
    }

    private class  ViewHodler{
        private CircleImageView imgHinh;
        private TextView txtTen, txtNoiDung,txtTime;
    }
}
