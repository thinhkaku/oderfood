package adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.quang.orderfood.R;
import com.example.quang.orderfood.activities.MenuManagementActivity;

import java.util.ArrayList;
import java.util.List;

import consts.Constants;
import objects.ItemMenu;

/**
 * Created by Administrator on 3/2/2018.
 */

public class MenuManagerAdapter extends ArrayAdapter<ItemMenu> {
    private LayoutInflater inflater;
    private ArrayList<ItemMenu>arrMenu=new ArrayList<>();
    public MenuManagerAdapter(@NonNull Context context, @NonNull ArrayList<ItemMenu> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
        inflater=LayoutInflater.from(context);
        arrMenu=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View v, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (v==null){
            viewHolder=new ViewHolder();
            v= inflater.inflate(R.layout.item_list_menu_manager,parent,false);
            viewHolder.imgFood=v.findViewById(R.id.imgOfFoodMenuManager);
            viewHolder.tvName =v.findViewById(R.id.tvNameOdFoodMenuManager);
            viewHolder.tvPrice =v.findViewById(R.id.tvPriceOfFoodMenuManager);
            viewHolder.btnEdit =v.findViewById(R.id.btnEditMenuManager);
            viewHolder.btnRemove =v.findViewById(R.id.btnRemoveMenuManager);
            v.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) v.getTag();
        }
        ItemMenu itemMenu =arrMenu.get(position);
        Glide.with(getContext()).load(Constants.PORT+itemMenu.getImage()).into(viewHolder.imgFood);
        viewHolder.tvName.setText(itemMenu.getName());
        viewHolder.tvPrice.setText(itemMenu.getPrice());
        viewHolder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        viewHolder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return v;
    }
    private class ViewHolder{
        ImageView imgFood;
        TextView tvName;
        TextView tvPrice;
        ImageButton btnEdit, btnRemove;
    }
}
