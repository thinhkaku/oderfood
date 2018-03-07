package adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.renderscript.ScriptIntrinsicHistogram;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.quang.orderfood.R;
import com.example.quang.orderfood.activities.EditMenuActivity;
import com.example.quang.orderfood.activities.MenuManagementActivity;

import java.util.ArrayList;
import java.util.List;

import consts.Constants;
import objects.ItemMenu;
import singleton.Singleton;




/**
 * Created by Administrator on 3/2/2018.
 */

public class MenuManagerAdapter extends ArrayAdapter<ItemMenu> {
    private static String CLIENT_SEND_DELETE_MENU="CLIENT_SEND_DELETE_MENU";
    private LayoutInflater inflater;
    private String PICK_PHOTO_FOR_AVATAR ="PICK_PHOTO_FOR_AVATAR";
    private Dialog dialogRemove, diglogEditMenu;
    private ArrayList<ItemMenu>arrMenu=new ArrayList<>();
    private Animation animationButton;
    public MenuManagerAdapter(@NonNull Context context, @NonNull ArrayList<ItemMenu> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
        inflater=LayoutInflater.from(context);
        arrMenu=objects;
        animationButton= AnimationUtils.loadAnimation(getContext(),R.anim.button_apha);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View v, @NonNull ViewGroup parent) {
        final ViewHolder viewHolder;
        if (v==null){
            viewHolder=new ViewHolder();
            v= inflater.inflate(R.layout.item_list_menu_manager,parent,false);
            viewHolder.imgFood=v.findViewById(R.id.imgOfFoodMenuManager);
            viewHolder.tvName =v.findViewById(R.id.tvNameOdFoodMenuManager);
            viewHolder.tvPrice =v.findViewById(R.id.tvPriceOfFoodMenuManager);
            viewHolder.btnRemove =v.findViewById(R.id.btnRemoveMenuManager);
            v.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) v.getTag();
        }
        final ItemMenu itemMenu =arrMenu.get(position);
        Glide.with(getContext()).load(Constants.PORT+itemMenu.getImage()).into(viewHolder.imgFood);
        viewHolder.tvName.setText(itemMenu.getName());
        viewHolder.tvPrice.setText(itemMenu.getPrice());
        viewHolder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 viewHolder.btnRemove.startAnimation(animationButton);
                 intitDiLogReMove(itemMenu.getName());
                 dialogRemove.show();
            }
        });
        return v;
    }
    private class ViewHolder{
        ImageView imgFood;
        TextView tvName;
        TextView tvPrice;
         ImageButton btnRemove;
    }
    private void  intitDiLogReMove(final String tenMonAn){
        dialogRemove =new Dialog(getContext(),android.R.style.Theme_DeviceDefault_Dialog_NoActionBar_MinWidth);
        dialogRemove.setContentView(R.layout.remove_manager_dilog);
        dialogRemove.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogRemove.setCancelable(false);
        final Button btnHuy=dialogRemove.findViewById(R.id.btnHuyRemove);
        final Button btnDongY=dialogRemove.findViewById(R.id.btnDongYRemoveDialog);
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnHuy.startAnimation(animationButton);
                dialogRemove.dismiss();
            }
        });
        btnDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnDongY.startAnimation(animationButton);
                dialogRemove.dismiss();
                Singleton.Instance().getmSocket().emit(CLIENT_SEND_DELETE_MENU,tenMonAn);
            }
        });
    }

    private void  initDialogEditMenu(String name, String price, String image){
        diglogEditMenu=new Dialog(getContext(),android.R.style.Theme_DeviceDefault_Dialog_NoActionBar_MinWidth);
        diglogEditMenu.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        diglogEditMenu.setContentView(R.layout.edit_menu_dialog);
        diglogEditMenu.setCancelable(false);
        EditText edtTenMonAn =diglogEditMenu.findViewById(R.id.edtEditTenMonAn);
        EditText edtGiaMonAn =diglogEditMenu.findViewById(R.id.edtEditGiaMonAn);
        final ImageView imgEdit=diglogEditMenu.findViewById(R.id.imgEditHinhMonAn);
        final Button btnHuy=diglogEditMenu.findViewById(R.id.btnHuyEditMenu);
        edtTenMonAn.setText(name);
        edtGiaMonAn.setText(price);
        Glide.with(getContext()).load(Constants.PORT+image).into(imgEdit);
        final Button btnOK=diglogEditMenu.findViewById(R.id.btnDongYEditMenu);
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnHuy.startAnimation(animationButton);
                diglogEditMenu.dismiss();
            }
        });
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnOK.startAnimation(animationButton);
                diglogEditMenu.dismiss();
            }
        });
        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgEdit.startAnimation(animationButton);

            }
        });
        diglogEditMenu.show();
    }

}
