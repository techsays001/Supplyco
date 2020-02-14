package com.msg91.sendotp.sample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Subsudypayment extends AppCompatActivity implements PaymentResultListener {
        EditText date;
        Button pay;
Intent j;
    SharedPreferences sh,shp;
String f;
        @Override
        protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcidypayment);
        date = findViewById(R.id.datepay12);
        pay = findViewById(R.id.paybt12);
           sh=getSharedPreferences("data",MODE_PRIVATE);
            shp=getSharedPreferences("reh",MODE_PRIVATE);
          //  Toast.makeText(Subsudypayment.this, shp.getString("rid",null),Toast.LENGTH_LONG).show();

            j=getIntent();

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (date.getText().toString().isEmpty()) {

                    date.setError("null");

                }
                else {

                    new SweetAlertDialog(Subsudypayment.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Confitm")
                            .setContentText("yes")
                           // .setConfirmText("OK")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog
//                                            .setTitleText("Logining...!")
//
//                                            .setConfirmText("OK")

                                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                @Override
                                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                    startPayment();

                                                }
                                            })
                                            .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                }
                            })
                            .show();
                }




//




            }

            private void startPayment() {

                final View.OnClickListener activity = this;

                final Checkout co = new Checkout();

                try {
                    JSONObject options = new JSONObject();
                    options.put("name", "Razorpay Corp");
                    options.put("description", "Demoing Charges");
                    //You can omit the image option to fetch the image from dashboard
                    options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
                    options.put("currency", "INR");
                    options.put("amount", String.valueOf(Integer.parseInt(j.getStringExtra("price")) * (Integer.parseInt(date.getText().toString())) * 100));
                            //String.valueOf(Integer.parseInt(j.getStringExtra("price"))* String.valueOf(Integer.parseInt(date.getText().toString())*100))));

                    JSONObject preFill = new JSONObject();
                    preFill.put("email", "test@razorpay.com");
                    preFill.put("contact", "9876543210");

                    options.put("prefill", preFill);

                    co.open(Objects.requireNonNull(Subsudypayment.this), options);
                } catch (Exception e) {
                    Toast.makeText(Subsudypayment.this, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                            .show();
                    e.printStackTrace();
                }





            }







        });


    }



    @Override
    public void onPaymentSuccess( String s) {
        data();

    }

    private void data() {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://androidprojectstechsays.000webhostapp.com/Supplyco_manegument_sydtem/Purcase_subsedy.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//If we are getting success from server
                    //    Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                        if(response.contains("ok"))
                        {

                            new SweetAlertDialog(Subsudypayment.this, SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("Payment Success")
                                    .setContentText("Back to Dashboard!")
                                    .setConfirmText("ok")
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            sDialog
                                                    .setTitleText("Logining...!")

                                                    .setConfirmText("OK")

                                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                        @Override
                                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                            Intent in=new Intent(Subsudypayment.this,MainActivity2.class);
                                                            startActivity(in);
                                                        }
                                                    })
                                                    .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                        }
                                    })
                                    .show();




//
                        }


                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject json_obj = jsonArray.getJSONObject(i);
//ba = json_obj.getString("balance");


                            }
//Toast.makeText(Recharge.this, "your new balnce is "+ba, Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//You can handle error here if you want
                    }

                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name",sh.getString("name",null ));
                params.put("pname", j.getStringExtra("pname"));
                params.put("price",  j.getStringExtra("price"));
                params.put("img",  j.getStringExtra("img"));
                params.put("sub",  j.getStringExtra("sub"));
                params.put("ph",sh.getString("phone",null ));
                params.put("add",sh.getString("address",null ));
                params.put("la",sh.getString("la",null ));
                params.put("lo",sh.getString("lo",null ));
                params.put("idd",j.getStringExtra("idd"));
                params.put("date",date.getText().toString());
                params.put("rname",shp.getString("rname",null ));
                params.put("rid",shp.getString("rid",null ));
                params.put("rimg",shp.getString("image",null ));
          //  params.put("date",s);

//returning parameter
                return params;
            }

        };

// m = Integer.parseInt(ba) - Integer.parseInt(result.getContents());
// balance.setText(m+"");


//Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(Subsudypayment.this);
        requestQueue.add(stringRequest);
    }





    @Override
    public void onPaymentError(int i, String s) {

    }

}