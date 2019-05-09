package com.example.anthithanhtam.quanlynhahang.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.anthithanhtam.quanlynhahang.R;
import com.example.anthithanhtam.quanlynhahang.constant.Utils;
import com.example.anthithanhtam.quanlynhahang.model.ThongKe;
import com.example.anthithanhtam.quanlynhahang.model.TotalMoney;
import com.example.anthithanhtam.quanlynhahang.model.TotalPeople;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.formatter.StackedValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentQuanLyBaoCao extends BaseFragment implements OnChartValueSelectedListener {
    @BindView(R.id.chart_real)
    CombinedChart mLineChart;
    @BindView(R.id.prBLoadHistory)
    ProgressBar prBLoadHistory;
    @BindView(R.id.crdLoadHistory)
    CardView crdLoadHistory;
    @BindView(R.id.fabReport)
    FloatingActionButton fabReport;
    Unbinder unbinder;

    private List<ThongKe> list = new ArrayList<>();
    String year;

    @Override
    protected void initView() {
         year = Utils.formatYear(Utils.getCurentDateTime());
        showChartTotalPeople();
    }

    private void showChartTotalPeople()
    {
        list.clear();
        for (int i = 1; i <= 12; i++) {
            soService.thongKeSoNguoi(String.valueOf(i), year).enqueue(new Callback<List<TotalPeople>>() {
                @Override
                public void onResponse(Call<List<TotalPeople>> call, Response<List<TotalPeople>> response) {
                    if (response.body().get(0).getTotalPeople() != null) {
                        list.add(new ThongKe(Integer.parseInt(response.body().get(0).getTotalPeople()), 0));
                    } else {
                        list.add(new ThongKe(0, 0));
                    }
                    if (list.size()==12)
                    {
                        fixXchart();
                    }
                }

                @Override
                public void onFailure(Call<List<TotalPeople>> call, Throwable t) {

                }
            });
        }

    }

    private void showChartTotalMonney()
    {
        list.clear();
        for (int i = 1; i <= 12; i++) {
            soService.thongKeSoTien(String.valueOf(i), year).enqueue(new Callback<List<TotalMoney>>() {
                @Override
                public void onResponse(Call<List<TotalMoney>> call, Response<List<TotalMoney>> response) {
                    if (response.body().get(0).getTotalMoney() != null) {
                        list.add(new ThongKe(0, Float.valueOf(response.body().get(0).getTotalMoney())));
                    }else {
                        list.add(new ThongKe(0, 0));
                    }
                    if (list.size()==12)
                    {
                        fixXchart();
                    }
                }

                @Override
                public void onFailure(Call<List<TotalMoney>> call, Throwable t) {

                }
            });
        }
    }

    private Dialog dialogSelectReport;
    private boolean check;

    private void initDialogAddType() {
        dialogSelectReport = new Dialog(mActivity);
        dialogSelectReport.setContentView(R.layout.dialog_select_report);
        RadioButton rdSoNguoi = dialogSelectReport.findViewById(R.id.rdSoNguoi);
        RadioButton rdThuNhap = dialogSelectReport.findViewById(R.id.rdThuNhap);
        Button btnAccess = dialogSelectReport.findViewById(R.id.btnAccess);
        if (check)
        {
            rdThuNhap.setChecked(true);
        }
        rdSoNguoi.setOnClickListener(v -> {
            check=false;
        });
        rdThuNhap.setOnClickListener(v -> {
            check=true;
        });
        btnAccess.setOnClickListener(v -> {
            if (check) {
                showChartTotalMonney();
            } else {
                showChartTotalPeople();
            }
            dialogSelectReport.dismiss();
        });
        dialogSelectReport.show();
    }


    @Override
    protected int layoutID() {
        return R.layout.layout_quan_ly_bao_cao_fragment;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return false;
    }






    //1451581200-

    private void fixXchart() {
        mLineChart.getDescription().setEnabled(false);
        mLineChart.setBackgroundColor(Color.WHITE);
        mLineChart.setDrawGridBackground(false);
        mLineChart.setDrawBarShadow(false);
        mLineChart.setHighlightFullBarEnabled(false);
        mLineChart.setOnChartValueSelectedListener(this);

        YAxis rightAxis = mLineChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setAxisMinimum(0f);

        YAxis leftAxis = mLineChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0f);

        final List<String> xLabel = new ArrayList<>();
        xLabel.add("");
        xLabel.add("Jan");
        xLabel.add("Feb");
        xLabel.add("Mar");
        xLabel.add("Apr");
        xLabel.add("May");
        xLabel.add("Jun");
        xLabel.add("Jul");
        xLabel.add("Aug");
        xLabel.add("Sep");
        xLabel.add("Oct");
        xLabel.add("Nov");
        xLabel.add("Dec");

        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(){
            @Override
            public String getFormattedValue(float value) {
                return xLabel.get((int) value % xLabel.size());
            }
        });


        CombinedData data = new CombinedData();
        LineData lineDatas = new LineData();
        lineDatas.addDataSet((ILineDataSet) dataChart(list));

        data.setData(lineDatas);

        xAxis.setAxisMaximum(data.getXMax() + 0.2f);

        mLineChart.setData(data);
        mLineChart.invalidate();

    }



    private  DataSet dataChart(List<ThongKe>list) {

        LineData d = new LineData();

        ArrayList<Entry> entries = new ArrayList<Entry>();

        if (check)
        {

            for (int index = 0; index < 12; index++) {
                entries.add(new Entry(index+1, list.get(index).getThuNhap()));
            }
            LineDataSet set = new LineDataSet(entries, "THu nhập");
            //LineDataSet set = createLineDataSet(entries,R.string.money,R.color.colorAccent);
            set.setColor(Color.RED);
            set.setLineWidth(2.5f);
            set.setCircleColor(Color.GREEN);
            set.setCircleRadius(5f);
            set.setFillColor(Color.GREEN);
            set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            set.setDrawValues(true);
            set.setValueTextSize(10f);
            set.setValueTextColor(Color.GREEN);

            set.setAxisDependency(YAxis.AxisDependency.LEFT);
            d.addDataSet(set);

            return set;
        }else {
            for (int index = 0; index < 12; index++) {
                entries.add(new Entry(index+1, list.get(index).getSoKhach()));
            }
            LineDataSet set = new LineDataSet(entries, "Số khách");
            //LineDataSet set = createLineDataSet(entries,R.string.money,R.color.colorAccent);
            set.setColor(Color.GREEN);
            set.setLineWidth(2.5f);
            set.setCircleColor(Color.RED);
            set.setCircleRadius(5f);
            set.setFillColor(Color.GREEN);
            set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            set.setDrawValues(true);
            set.setValueTextSize(10f);
            set.setValueTextColor(Color.GREEN);

            set.setAxisDependency(YAxis.AxisDependency.LEFT);
            d.addDataSet(set);

            return set;
        }
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


    @OnClick(R.id.fabReport)
    public void onViewClicked() {
        initDialogAddType();
    }
}
