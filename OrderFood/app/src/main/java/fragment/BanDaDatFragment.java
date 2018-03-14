package fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.example.quang.orderfood.R;
import com.example.quang.orderfood.activities.BillActivity;
import com.example.quang.orderfood.activities.MainForWaiterActivity1;
import com.example.quang.orderfood.activities.MenuActivity;
import com.github.nkzawa.emitter.Emitter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import adapter.GridviewTableAdapter;
import objects.Table;
import singleton.Singleton;

/**
 * Created by Administrator on 3/13/2018.
 */

public class BanDaDatFragment extends Fragment implements AdapterView.OnItemClickListener {

    private GridView gvBanDaDat;
    private Activity activity;
    private Context context;
    private ArrayList<Table> arrTable;
    private GridviewTableAdapter gridviewTableAdapter;
    private String REQUEST_TABLE_DA_DAT="REQUEST_TABLE_DA_DAT";
    private Emitter.Listener onResult;
    private String SERVER_SEND_LIST_TABLE="RESULT_TABLE_DA_DAT";
    private int number;

    {
        onResult=new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                getResult(args[0]);
            }
        };
    }

    private void getResult(final  Object arg) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                JSONArray data = (JSONArray) arg;
                arrTable.clear();
                for (int i=0; i<data.length(); i++)
                {
                    try {
                        JSONObject object = data.getJSONObject(i);
                        int num = object.getInt("tenBan");
                        String position = object.getString("viTri");
                        int numOfChair = object.getInt("soGhe");
                        String note = object.getString("ghiChu");
                        int check = object.getInt("tinhTrang");
                        Table table = new Table(num,position,numOfChair,check,note);
                        arrTable.add(table);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                gridviewTableAdapter=new GridviewTableAdapter(context, R.layout.item_gridview,arrTable);
                gvBanDaDat.setAdapter(gridviewTableAdapter);
                gridviewTableAdapter.notifyDataSetChanged();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fm_ban_da_dat,container,false);
    }

    @Override
    public void onStart() {
        super.onStart();
        initSocket();
        initView();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity=activity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    private void initSocket() {
        Singleton.Instance().getmSocket().emit(REQUEST_TABLE_DA_DAT,-1);
        Singleton.Instance().getmSocket().on(SERVER_SEND_LIST_TABLE,onResult);
    }

    private void initView() {
        arrTable=new ArrayList<>();
        gvBanDaDat=activity.findViewById(R.id.gvBanDaDat);
        gvBanDaDat.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId())
        {
            case R.id.gvBanDaDat:
                Table table = arrTable.get(position);
                number = table.getNumber();
                Toast.makeText(context,number+"",Toast.LENGTH_SHORT).show();

                        MainForWaiterActivity1.CHECK_TABLE1 = true;
                        Intent intent = new Intent(activity,BillActivity.class);
                        intent.putExtra("table",number+"");
                        startActivity(intent);
                break;
        }
    }
}
