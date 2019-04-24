package com.example.anthithanhtam.quanlynhahang.fragment;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.anthithanhtam.quanlynhahang.R;
import com.example.anthithanhtam.quanlynhahang.model.ThongKe;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

public class FragmentQuanLyBaoCao extends BaseFragment implements OnChartValueSelectedListener {
    @BindView(R.id.chart_real)
    LineChart mLineChart;
    @BindView(R.id.prBLoadHistory)
    ProgressBar prBLoadHistory;
    @BindView(R.id.crdLoadHistory)
    CardView crdLoadHistory;



    @Override
    protected void initView() {
//        getActivity().setRequestedOrientation(
//                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        initLineChar();
        List<ThongKe>list=new ArrayList<>();
        for (int i=0;i<25;i++)
        {
            list.add(new ThongKe(i, i*20));
        }
        setDataLineChartAVG(list);
    }

    @Override
    protected int layoutID() {
        return R.layout.layout_quan_ly_bao_cao_fragment;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return false;
    }



    private void initLineChar() {
        mLineChart.setOnChartValueSelectedListener(this);
        // no description text
        mLineChart.getDescription().setEnabled(false);
        // enable touch gestures
        mLineChart.setTouchEnabled(true);
//        mLineChart.setDragDecelerationFrictionCoef(0.9f);
        // enable scaling and dragging
        mLineChart.setDragEnabled(true);
        mLineChart.setScaleEnabled(true);
        // if disabled, scaling can be done on x- and y-axis separately
        mLineChart.setPinchZoom(false);
        // set an alternative background color
        mLineChart.setDrawGridBackground(false);
        mLineChart.setBackgroundColor(Color.WHITE);
    }

    private void setDataLineChartAVG(List<ThongKe> arrThongKe) {
        ArrayList<Entry> avgEntries = new ArrayList<Entry>();
        ArrayList<Entry> minEntries = new ArrayList<Entry>();
        ArrayList<Entry> maxEntries = new ArrayList<Entry>();
        int j = 0;
        while (j < 86400) {
            if (j < arrThongKe.size() * 600) {
                int soKhach = arrThongKe.get(j / 600).getSoKhach();
                float thuNhap = arrThongKe.get(j / 600).getThuNhap();
                minEntries.add(new Entry(j, soKhach));
                avgEntries.add(new Entry(j, thuNhap));
            }
            j = j + 600;
        }


//        SimpleDateFormat sdHH=new SimpleDateFormat(Constants.SERVER_DATE_FORMAT, Locale.getDefault());
//        SimpleDateFormat sdMM=new SimpleDateFormat(Constants.SERVER_DATE_FORMAT,Locale.getDefault());
//        Date newDateHH = null;
//        Date newDateMM = null;
//        try {
//            newDateHH = sdHH.parse(arrHistory.get(0).getTime());
//            newDateMM = sdMM.parse(arrHistory.get(0).getTime());
//            sdHH = new SimpleDateFormat("hh");
//            sdMM = new SimpleDateFormat("mm");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

//        String hh=sdHH.format(newDateHH);
//        String mm=sdMM.format(newDateMM);
//        Log.e("hh: ",hh);
//        Log.e("mm: ",mm);
//        int ll=Integer.parseInt(hh)*60*60+Integer.parseInt(mm)*60;

        LineDataSet avgDataSet = createLineDataSet(avgEntries, R.string.number_people_chart, R.color.colorAccent);
        LineDataSet minDataSet = createLineDataSet(minEntries, R.string.money, R.color.colorPrimary);

        LineData lineData = new LineData();
        lineData.addDataSet(avgDataSet);
        lineData.addDataSet(minDataSet);
        // set data
        mLineChart.setData(lineData);
        mLineChart.notifyDataSetChanged();
        mLineChart.invalidate();
        mLineChart.animateXY(2500, 2500);

        // get the legend (only possible after setting data)
        Legend legend = mLineChart.getLegend();

        // modify the legend ...
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextSize(11f);
        legend.setTextColor(Color.BLACK);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);

        YAxis leftAxis = mLineChart.getAxisLeft();
        leftAxis.setTextColor(Color.BLACK);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setDrawGridLines(true);
        leftAxis.setGranularityEnabled(true);

        YAxis rightAxis = mLineChart.getAxisRight();
        rightAxis.setTextColor(Color.BLACK);
        rightAxis.setAxisMinimum(0);
        rightAxis.setDrawGridLines(false);
        rightAxis.setDrawZeroLine(false);
        rightAxis.setGranularityEnabled(false);
        //fixXchart(ll);

    }

    //1451581200-

    private void fixXchart(int ll) {
//        IAxisValueFormatter xAxisFormatter = new HourAxisValueFormatter(ll-3600);
//        XAxis xAxis = mLineChart.getXAxis();
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setValueFormatter(xAxisFormatter);
//        MyMarkerView myMarkerView = new MyMarkerView(mActivity.getApplicationContext(), R.layout.my_marker_view_layout);
        //mLineChart.setMarkerView(myMarkerView);

    }

    @NonNull
    private LineDataSet createLineDataSet(ArrayList<Entry> entries, int textId, int colorId) {
        LineDataSet dataSet = new LineDataSet(entries, getString(textId));
        dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        dataSet.setColor(ContextCompat.getColor(mActivity, colorId));
        dataSet.setLineWidth(2f);
        dataSet.setDrawCircleHole(false);
        dataSet.setDrawCircles(false);
        return dataSet;
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
