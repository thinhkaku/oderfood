package com.example.anthithanhtam.quanlynhahang.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.anthithanhtam.quanlynhahang.R;
import com.example.anthithanhtam.quanlynhahang.model.MyTable;
import com.example.anthithanhtam.quanlynhahang.model.Table;

import java.util.ArrayList;
import java.util.List;

public class TableAdapter extends BaseAdapter{
    private Context context;
    private int layout;
    private List<MyTable> arrTable;


    public TableAdapter(Context context, int layout, List<MyTable> arrTable) {
        this.context = context;
        this.layout = layout;
        this.arrTable = arrTable;
    }

    private class ViewHolder
    {
        TextView tvNum;
    }

    @Override
    public int getCount() {
        return arrTable.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewRow = view;
        final ViewHolder viewHolder;
        if (viewRow == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewRow = inflater.inflate(layout,viewGroup,false);
            viewHolder.tvNum = viewRow.findViewById(R.id.number);
            viewRow.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) viewRow.getTag();
        }
        final MyTable table = arrTable.get(i);
        viewHolder.tvNum.setText(table.getNumber()+"");

         if (table.getCheck().equals("1"))
        {
            viewHolder.tvNum.setTextColor(Color.WHITE);
            viewHolder.tvNum.setBackgroundResource(R.drawable.background_table_off);

        }else {
            viewHolder.tvNum.setBackgroundResource(R.drawable.background_table);
        }
        return viewRow;
    }
}
