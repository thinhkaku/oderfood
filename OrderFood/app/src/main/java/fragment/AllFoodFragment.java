package fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.quang.orderfood.R;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;

import adapter.ListviewMenuAdapter;
import adapter.MenuManagementAdapterPager;
import adapter.MenuManagerAdapter;
import consts.Constants;
import objects.ItemMenu;
import objects.PagerTitle;
import singleton.Singleton;

/**
 * Created by Administrator on 3/1/2018.
 */

public class AllFoodFragment extends Fragment {
    private static String CLIENT_SEND_MENU="CLIENT_SEND_MENU";
    private static String SERVER_SEND_MENU_DRINK="SERVER_SEND_MENU";
    private ArrayList<ItemMenu>arrAllFood;
    private ListView lvMenu;
    private Context context;
    private Activity activity;
    private Socket mSocket;
    private MenuManagerAdapter menuManagerAdapter;
    private Emitter.Listener onResult;

    {
        try {
            mSocket = IO.socket(Constants.PORT);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        onResult=new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                resultAllFood(args[0]);
            }

        };
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fm_all_food, container, false);

    }

    @Override
    public void onStart() {
        super.onStart();
        initSocket();
        initView();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initView() {
        arrAllFood=new ArrayList<>();
        lvMenu=getActivity().findViewById(R.id.lvAllFoodManager);

    }

    private void resultAllFood(final Object args) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                JSONArray data = (JSONArray) args;
                arrAllFood.clear();
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
                menuManagerAdapter =new MenuManagerAdapter(context,arrAllFood);
                lvMenu.setAdapter(menuManagerAdapter);
                menuManagerAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initSocket() {
        mSocket.connect();
        Singleton.Instance().setmSocket(mSocket);
        Toast.makeText(context,"initSocke1t",Toast.LENGTH_SHORT).show();
        Singleton.Instance().getmSocket().emit(CLIENT_SEND_MENU,123);
        Singleton.Instance().getmSocket().on(SERVER_SEND_MENU_DRINK,onResult);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

}