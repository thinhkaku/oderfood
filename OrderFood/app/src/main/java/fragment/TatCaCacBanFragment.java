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
import com.example.quang.orderfood.activities.MainForWaiterActivity;
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

public class TatCaCacBanFragment extends Fragment implements AdapterView.OnItemClickListener {
    private GridView gvTatCa;
    private Activity activity;
    private Context context;
    private ArrayList<Table>arrTable;

    private GridviewTableAdapter gridviewTableAdapter;
    private String REQUEST_BOOK="REQUEST_BOOK";
    private Emitter.Listener onResult;
    private Dialog dialogPeople;
    private int number;
    private Animation animationButton;
    private String SERVER_SEND_LIST_TABLE="SERVER_SEND_LIST_TABLE";
    private String people;
    private int banDangChon;

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

                for (int i=0;i<arrTable.size()-1;i++)
                {
                    if (arrTable.get(i).getCheck() == 1||arrTable.get(i).getCheck()==2)
                    {
                        if (banDangChon==i){
                            dialogPeople.dismiss();
                        }
                    }
                }
                gridviewTableAdapter=new GridviewTableAdapter(context,R.layout.item_gridview,arrTable);
                gvTatCa.setAdapter(gridviewTableAdapter);
                gridviewTableAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId())
        {
            case R.id.gvTatCaBan:
                    banDangChon=i;
                    Table table = arrTable.get(i);
                    int check = table.getCheck();
                    number = table.getNumber();
                Toast.makeText(context,number+"",Toast.LENGTH_SHORT).show();
                    if (table.getCheck()==3){
                        Toast.makeText(context,"Bàn này đang có người chọn",Toast.LENGTH_SHORT).show();
                    }else {
                        if (check == 0)
                        {
                            MainForWaiterActivity1.CHECK_TABLE1 = false;
                            dialogPeople.show();
                        }
                        else
                        {
                            MainForWaiterActivity1.CHECK_TABLE1 = true;
                            Intent intent = new Intent(activity,BillActivity.class);
                            intent.putExtra("table",number+"");
                            startActivity(intent);
                        }
                }
                break;
        }
    }


    private void initDialogPeople() {
        dialogPeople = new Dialog(context,android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth);
        dialogPeople.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogPeople.setContentView(R.layout.dialog_check_number);
        dialogPeople.setCancelable(false);
        final EditText edtPeople = dialogPeople.findViewById(R.id.edtPeopleDialog);
        final Button btnExit = dialogPeople.findViewById(R.id.btnExitDialog);
        final Button btnDone = dialogPeople.findViewById(R.id.btnDoneDialog);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnExit.startAnimation(animationButton);
                dialogPeople.dismiss();
            }
        });
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnDone.startAnimation(animationButton);
                people = edtPeople.getText().toString();
                if (people.isEmpty()){
                    Snackbar snackbar = Snackbar
                            .make(edtPeople, "Chưa nhập số bàn!", Snackbar.LENGTH_SHORT);
                    snackbar.setActionTextColor(Color.WHITE);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(Color.DKGRAY);
                    snackbar.show();
                }else {
                    Singleton.Instance().getmSocket().emit("CLIENT_REQUEST_TINH_TRANG_BAN",number);
                    Singleton.Instance().getmSocket().emit(REQUEST_BOOK,number);
                    Intent intent = new Intent(activity, MenuActivity.class);
                    intent.putExtra("numPeo",number+"-"+people);
                    startActivity(intent);
                    dialogPeople.dismiss();
                }
                edtPeople.setText("");
            }
        });
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fm_tat_ca_ban,container,false);
    }

    @Override
    public void onStart() {
        super.onStart();
        initSocket();
        initView();
        addEvent();
        initDialogPeople();
    }

    private void addEvent() {
        gvTatCa.setOnItemClickListener(this);
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
        Singleton.Instance().getmSocket().emit(REQUEST_BOOK,-1);
        Singleton.Instance().getmSocket().on(SERVER_SEND_LIST_TABLE,onResult);
    }

    private void initView() {
        arrTable=new ArrayList<>();
        gvTatCa=activity.findViewById(R.id.gvTatCaBan);
        animationButton= AnimationUtils.loadAnimation(context,R.anim.button_apha);
    }
}
