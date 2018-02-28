package adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quang.orderfood.R;

import java.util.ArrayList;

import objects.HistoryBill;
import objects.Staff;

/**
 * Created by quang on 31-Jan-18.
 */

public class ListviewStaffAdapter extends ArrayAdapter<Staff> {

    private Context context;
    private int layout;
    private ArrayList<Staff> arrItem=new ArrayList<>();


    public ListviewStaffAdapter(Context context, int layout, ArrayList<Staff> arrItem) {
        super(context, android.R.layout.simple_list_item_1, arrItem);
        this.context = context;
        this.layout = layout;
        this.arrItem = arrItem;
    }

    private class ViewHolder{
        TextView tvName;
        ImageView imCheckOnline;

    }

    @NonNull
    @Override
    public Filter getFilter() {
        return super.getFilter();

    }

    //    @Override
//    public int getCount() {
//        return arrItem.size();
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return i;
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return 0;
//    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewRow = view;
        if (viewRow == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewRow = inflater.inflate(layout,viewGroup,false);
            ListviewStaffAdapter.ViewHolder viewHolder = new ListviewStaffAdapter.ViewHolder();

            viewHolder.tvName = viewRow.findViewById(R.id.tvNameStaff);
            viewHolder.imCheckOnline = viewRow.findViewById(R.id.checkOnline);

            viewRow.setTag(viewHolder);
        }

        final Staff staff = arrItem.get(i);
        final ListviewStaffAdapter.ViewHolder viewHolder = (ListviewStaffAdapter.ViewHolder) viewRow.getTag();
        viewHolder.tvName.setText(staff.getName());
        if (staff.getCheckOnline() == 1)
        {
            viewHolder.imCheckOnline.setVisibility(View.VISIBLE);
        }
        else {
            viewHolder.imCheckOnline.setVisibility(View.INVISIBLE);
        }
        return viewRow;
    }
}