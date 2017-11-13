package com.example.zeeshan.profitfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private EditText _ETSalesPrice;
    private EditText _ETShippingPrice;
    private EditText _ETQuantity;
    private EditText _ETItemCost;
    private EditText _ETShippingCost;
    private Switch _SWEbay;
    private Switch _SWPaypal;
    private Switch _SWAmazon;


    Boolean EbaySeller=false,AmazonSeller=false;
    double salePrice,//salePrice = sale price not including shipping Price
            shippingPrice,//	shippingPrice = amount Priced to buyer
            itemCost,    //.	itemCost = price you paid for item
            shippingCost;//	shippingCost = price you paid for shipping

    Integer quantity;// total item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Binding the UI components to java class

        _ETSalesPrice = (EditText) findViewById(R.id.ETSalesPrice);
        _ETShippingPrice = (EditText) findViewById(R.id.ETShippingPrice);
        _ETQuantity = (EditText) findViewById(R.id.ETQuantity);
        _ETItemCost = (EditText) findViewById(R.id.ETItemCost);
        _ETShippingCost = (EditText) findViewById(R.id.ETShippingCost);
        _SWEbay = (Switch) findViewById(R.id.SWEbay);
        _SWPaypal = (Switch) findViewById(R.id.SWPaypal);
        _SWAmazon = (Switch) findViewById(R.id.SWAmazon);



        _SWEbay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(_SWEbay.isChecked())
                {
                    _SWAmazon.setChecked(false);
                    _SWPaypal.setChecked(true);
                    EbaySeller=true;
                    AmazonSeller=false;
                }
                if(!_SWEbay.isChecked()){

                    _SWPaypal.setChecked(false);
                    EbaySeller=false;
                }
            }
        });
        _SWPaypal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(_SWPaypal.isChecked()){
                    _SWAmazon.setChecked(false);
                    _SWEbay.setChecked(true);
                    EbaySeller=true;
                    AmazonSeller=false;
                }
                if(!_SWPaypal.isChecked()){

                    _SWEbay.setChecked(false);
                    EbaySeller=false;
                }


            }
        });
        _SWAmazon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(_SWAmazon.isChecked()){
                    _SWAmazon.setChecked(true);
                    _SWPaypal.setChecked(false);
                    _SWEbay.setChecked(false);
                    AmazonSeller=true;
                    EbaySeller=false;
                }
                if(!_SWAmazon.isChecked()){
                    AmazonSeller=false;
                }
            }
        });



    }

    double ReturnPercentage, Margin,MarginPercentage,NetProfite
            , TotalRevenue, TotalShippingFee,
            EbayFee, PayPalFee;


    public void onCalculate() {


        if (!_ETSalesPrice.getText().toString().equals(""))    //salePrice = sale price not including shipping Price
            salePrice =Math.round(Double.parseDouble((_ETSalesPrice.getText().toString()))*100.00)
                    /100.00;
        else
            salePrice =0.0;

        if (!_ETShippingPrice.getText().toString().equals(""))    //	shippingPrice = amount Priced to buyer
            shippingPrice =Math.round(Double.parseDouble((_ETShippingPrice.getText().toString()))
                    *100.00) /100.00;

        else
            shippingPrice =0.0;


        if (!_ETQuantity.getText().toString().equals(""))    //total item
            quantity = Integer.parseInt(_ETQuantity.getText().toString());
        else
            quantity =0;


        if (!_ETItemCost.getText().toString().equals(""))    //itemCost = price you paid for item
            itemCost =Math.round(Double.parseDouble((_ETItemCost.getText().toString()))*100.00)
                    /100.00;
        else
            itemCost =0.0;


        if (!_ETShippingCost.getText().toString().equals(""))    //ShippingCost = price you paid for shipping
            shippingCost =Math.round(Double.parseDouble((_ETShippingCost.getText().toString()))
                    *100.00) /100.00;
        else
            shippingCost =0.0;


    }


    //Calculating the total fee that eBay charges
    public void eBayCalculator() {
        if(EbaySeller)
        EbayFee = Math.round(((salePrice + shippingPrice) * (0.1))*100.00)/100.00;
        //eBay= ((Sale Price+ Shipping Price) *(10%)) here the eBay charges the 10% of totalcost
        else if(AmazonSeller)//in case of amazaon seller no ebay fee will charges
         EbayFee =0.0;


    }


    //Calculating the total fee that PayPal charges along with eBay charges
    public void payPalCalculator() {
        if(EbaySeller)
        PayPalFee = 0.30+Math.round(((salePrice + shippingPrice) * (0.029))*100.00)/100.00;
        //PayPal=0.30+ ((Sale Price+ Shipping Price) *(2.9%)) =>as mention above
        else if(AmazonSeller)//in case of amazaon seller no ebay fee will charges
        PayPalFee=0.0;
    }


    //Calculating the total Profit that we get
    public void totalProfitCalculator() {


        Margin = (salePrice + shippingPrice) - (itemCost + shippingCost);
        NetProfite = Math.round((Margin - (EbayFee + PayPalFee)) * 100.00) / 100.00;
        //        Margin= (Sale Price + Shipping Price)- (Item Cost+ Shipping Cost)
//        Total Profit= Margin – (eBay+ PayPal)


    }
    //Calculating the total Revenue generated
    public void revenueCalculator(){
        TotalRevenue=salePrice+shippingPrice;
        //Revenue= Sale Price+ Shipping Price;
    }

    //Calculating the total (Shipping and Fees)
    public void totalShippingFeeCalculator(){

        TotalShippingFee= Math.round((EbayFee+PayPalFee+shippingCost)*100.0)/100.0;
        //(Shipping and Fees) = eBay+ PayPal+ Shipping Cost
    }


    //Calculating the Margin Percentage that we get
    public void marginPercentageCalculator(){


        MarginPercentage=Math.round(((NetProfite/TotalRevenue)*100)*10.0)/10.0;

/*
        The Total Profit P is the difference between the cost to make a product C and the selling price or revenue R.
        o	P = R - C
        The Margin Percentage G is the profit P divided by the selling price or revenue R.
        o	G = P / R = (R - C) / R
*/


    }

    //Calculating the Return Percentage generated
    public  void returnPercentageCalculator(){

        ReturnPercentage=Math.round(((NetProfite/(itemCost+TotalShippingFee))*100)*10.00)/10.00;

        /*
        The Return Percentage RP is the profit P divided by the selling price or Cost C.
        o	RP = P / C= (R - C) / C*/

    }

    public void CalculateResult(View view) {

        if((EbaySeller==true || AmazonSeller==true)&& (_SWAmazon.isChecked() ||_SWPaypal.isChecked())){
            onCalculate();
            if(quantity==0)
           {
               Toast.makeText(this, "Insert Item Quantity", Toast.LENGTH_SHORT).show();
           }
           else
           {

               eBayCalculator();
               payPalCalculator();
               totalProfitCalculator();
               totalShippingFeeCalculator();
               revenueCalculator();
               marginPercentageCalculator();
               returnPercentageCalculator();

               Intent  intent=new Intent(getApplicationContext(),DetailedResult.class);

               intent.putExtra("NetProfite",NetProfite*quantity);
               intent.putExtra("ReturnPercentage",ReturnPercentage);
               intent.putExtra("MarginPercentage",MarginPercentage);
               intent.putExtra("TotalRevenue",TotalRevenue*quantity);
               intent.putExtra("salePrice",salePrice*quantity);
               intent.putExtra("shippingPrice",shippingPrice*quantity);
               intent.putExtra("quantity",quantity);
               intent.putExtra("TotalShippingFee",TotalShippingFee*quantity);
               intent.putExtra("EbayFee",EbayFee*quantity);
               intent.putExtra("PayPalFee",PayPalFee*quantity);
               intent.putExtra("shippingCost",shippingCost*quantity);

               startActivity(intent);



           }

        }

        else{
            Toast.makeText(this, "Select the Service Channel First", Toast.LENGTH_SHORT).show();

        }
    }
}



