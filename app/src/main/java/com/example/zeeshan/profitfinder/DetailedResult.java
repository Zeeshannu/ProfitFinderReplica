package com.example.zeeshan.profitfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class DetailedResult extends AppCompatActivity {



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


        _ETNetProfite = (TextView) findViewById(R.id.ETNetProfite);
        _ETReturn = (EditText) findViewById(R.id.ETReturn);
        _ETMargin = (EditText) findViewById(R.id.ETMargin);
        _ETRevenue = (TextView) findViewById(R.id.ETTotalRevenue);
        _ETSalePriceRevenue = (EditText) findViewById(R.id.ETSalePriceRevenue);
        _ETShippingPriceRevenue = (EditText) findViewById(R.id.ETShippingPriceRevenue);
        _ETQuantityRevenue = (EditText) findViewById(R.id.ETQuantityRevenue);
        _ETTotalShippingFee= (TextView) findViewById(R.id.ETTotalShippingFee);
        _ETEbayFee = (EditText) findViewById(R.id.ETEbayFee);
        _ETPayPalFee = (EditText) findViewById(R.id.ETPayPalFee);
        _ETShippingCost = (EditText) findViewById(R.id.ETShippingCost);

        setValues();
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
