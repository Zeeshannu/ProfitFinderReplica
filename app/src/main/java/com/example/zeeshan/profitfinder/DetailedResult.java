package com.example.zeeshan.profitfinder;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class DetailedResult extends AppCompatActivity {
    private float[] yData = new float[3];

    private String[] xData = {"Mitch", "Jessica" , "Mohammad" };
    PieChart pieChart;
private     static String TAG="DetailedResult";


    double Return,Margin,
            NetProfite, TotalRevenue,TotalShippingFee,
            SalePriceRevenue,ShippingPriceRevenue,QuantityRevenue,
            EbayFee, PayPalFee,ShippingCost;

    private TextView _ETNetProfite;
    private EditText _ETReturn;
    private EditText _ETMargin;
    private TextView _ETRevenue;
    private EditText _ETSalePriceRevenue;
    private EditText _ETShippingPriceRevenue;
    private EditText _ETQuantityRevenue;
    private TextView _ETTotalShippingFee;
    private EditText _ETEbayFee;
    private EditText _ETPayPalFee;
    private EditText _ETShippingCost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_result);

        yData[0]= (float) SharedPrefrence.NET_PROFIT;
        yData[1]= (float) SharedPrefrence.ITEM_COST;
        yData[2]= (float) SharedPrefrence.NET_SHIPPING_FEES;

        Log.d(TAG, "onCreate:Before Starting  ");
        _ETNetProfite = (TextView) findViewById(R.id.ETNetProfite);
        _ETReturn = (EditText) findViewById(R.id.ETReturn);
        _ETMargin = (EditText) findViewById(R.id.ETMargin);
        _ETRevenue = (TextView) findViewById(R.id.ETTotalRevenue);
        _ETSalePriceRevenue = (EditText) findViewById(R.id.ETSalePriceRevenue);
        _ETShippingPriceRevenue = (EditText) findViewById(R.id.ETShippingPriceRevenue);
        _ETQuantityRevenue = (EditText) findViewById(R.id.ETQuantityRevenue);
        _ETTotalShippingFee = (TextView) findViewById(R.id.ETTotalShippingFee);
        _ETEbayFee = (EditText) findViewById(R.id.ETEbayFee);
        _ETPayPalFee = (EditText) findViewById(R.id.ETPayPalFee);
        _ETShippingCost = (EditText) findViewById(R.id.ETShippingCost);
        pieChart=findViewById(R.id.PieChart);

        setValues();


        pieChart.setRotationEnabled(true);
        pieChart.setUsePercentValues(true);
        //pieChart.setHoleColor(Color.BLUE);
        //pieChart.setCenterTextColor(Color.BLACK);
        pieChart.setHoleRadius(50f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Super Cool Chart");
        pieChart.setCenterTextSize(10);
        pieChart.setDrawEntryLabels(true);
        //pieChart.setEntryLabelTextSize(20);
        //More options just check out the documentation!

        addDataSet();


    }


    private void addDataSet() {
        Log.d(TAG, "addDataSet started");
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        for(int i = 0; i < yData.length; i++){
            yEntrys.add(new PieEntry(yData[i] , i));
        }

        for(int i = 1; i < xData.length; i++){
            xEntrys.add(xData[i]);
        }

        //create the data set
        final  PieDataSet pieDataSet = new PieDataSet(yEntrys, "Employee Sales");
        pieDataSet.setSliceSpace(10);

        pieDataSet.setValueTextSize(12);

        //add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GREEN);
        colors.add(Color.YELLOW);
        colors.add(Color.RED);
        pieDataSet.setColors(colors);

        //add legend to chart
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();


    }



    public void setValues(){
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        _ETNetProfite.setText("$"+bundle.get("NetProfite").toString());
         _ETReturn.setText(bundle.get("ReturnPercentage").toString()+"%");
        _ETMargin.setText(bundle.get("MarginPercentage").toString()+"%");
        _ETRevenue.setText("$"+bundle.get("TotalRevenue").toString());
         _ETSalePriceRevenue.setText("$"+bundle.get("salePrice").toString());
         _ETShippingPriceRevenue.setText("$"+bundle.get("shippingPrice").toString());

        _ETQuantityRevenue.setText(bundle.get("quantity").toString());
        _ETTotalShippingFee.setText("$"+bundle.get("TotalShippingFee").toString());
         _ETEbayFee.setText("$"+bundle.get("EbayFee").toString());
         _ETPayPalFee.setText("$"+bundle.get("PayPalFee").toString());
         _ETShippingCost.setText("$"+bundle.get("shippingCost").toString());


    }
}
