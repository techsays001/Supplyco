package com.msg91.sendotp.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Employassign extends AppCompatActivity  implements
        AdapterView.OnItemSelectedListener {
    private ArrayList<Doctor> goodModelArrayList;

    private ArrayList<String> names = new ArrayList<String>();
Intent j;
    Spinner spiner;
    TextView name,phone,refresh;
    Button assign;
    ImageView imgview;
    String a,b,c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employassign);
        j=getIntent();
        Toast.makeText(Employassign.this, j.getStringExtra("la"), Toast.LENGTH_LONG).show();
        imgview = findViewById(R.id.empimg12);
        name = findViewById(R.id.empname12);
        spiner = findViewById(R.id.empid12);
        refresh = findViewById(R.id.emprefresh12);
        assign = findViewById(R.id.empassing12);
        phone = findViewById(R.id.empph12);


        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://androidprojectstechsays.000webhostapp.com/Supplyco_manegument_sydtem/emp_id.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//If we are getting success from server
//                        Toast.makeText(Phycology.this, response, Toast.LENGTH_LONG).show();

                        goodModelArrayList = new ArrayList<>();


                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject json_obj = jsonArray.getJSONObject(i);
//ba = json_obj.getString("balance");

                                Doctor doctor=new Doctor();
                                doctor.setName(json_obj.getString("name"));
                                goodModelArrayList.add(doctor);
                            }
//Toast.makeText(Recharge.this, "your new balnce is "+ba, Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        for (int i = 0; i < goodModelArrayList.size(); i++){
                            names.add(goodModelArrayList.get(i).getName().toString());
                        }

                        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(Employassign.this, R.layout.support_simple_spinner_dropdown_item, names);
                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                        spiner.setAdapter(spinnerArrayAdapter);



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
//Adding parameters to request



// Toast.makeText(MainActivity.this,"submitted",Toast.LENGTH_LONG).show();

//returning parameter
                return params;
            }

        };

// m = Integer.parseInt(ba) - Integer.parseInt(result.getContents());
// balance.setText(m+"");


        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(Employassign.this);
        requestQueue.add(stringRequest);

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://androidprojectstechsays.000webhostapp.com/Supplyco_manegument_sydtem/view_emp.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
//If we are getting success from server
//                       Toast.makeText(Phycology.this,response,Toast.LENGTH_LONG).show();
                                try {
                                    JSONArray jsonArray = new JSONArray(response);
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject json_obj = jsonArray.getJSONObject(i);
//ba = json_obj.getString("balance");
                                        a=json_obj.getString("name");
                                        b=json_obj.getString("phone");
                                        c=json_obj.getString("image");


                                        name.setText(a);
                                        phone.setText(b);
                                        Picasso.get().load(c).into(imgview);
                                    }
//Toast.makeText(Recharge.this, "your new balnce is "+ba, Toast.LENGTH_LONG).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                //   Toast.makeText(Signin.this, "success", Toast.LENGTH_LONG).show();

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
//Adding parameters to request
                        params.put("id",spiner.getSelectedItem().toString());

// Toast.makeText(MainActivity.this,"submitted",Toast.LENGTH_LONG).show();

//returning parameter
                        return params;
                    }

                };

// m = Integer.parseInt(ba) - Integer.parseInt(result.getContents());
// balance.setText(m+"");


//Adding the string request to the queue
                RequestQueue requestQueue = Volley.newRequestQueue(Employassign.this);
                requestQueue.add(stringRequest);


            }
        });
assign.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://androidprojectstechsays.000webhostapp.com/Supplyco_manegument_sydtem/employ_assign.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//If we are getting success from server
                        //  Toast.makeText(regtwo.this,response,Toast.LENGTH_LONG).show();
                        if(response.contains("Registration Successful"))
                        {

                            new SweetAlertDialog(Employassign.this, SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("Oder Assign")
                                    .setContentText("Completed!")
                                    .setConfirmText("Yes,")
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            sDialog
                                                    .setTitleText("Logining...!")

                                                    .setConfirmText("OK")

                                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                        @Override
                                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
//                                                            Intent in=new Intent(regtwo.this, Signin.class);
//                                                            startActivity(in);
                                                        }
                                                    })
                                                    .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                        }
                                    })
                                    .show();




//
                        }


                        else{


                            new SweetAlertDialog(Employassign.this, SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("Registration Failed")
                                    .setContentText("Exite!")
                                    .setConfirmText("Yes")
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            sDialog
                                                    .setTitleText("Exite...!")

                                                    .setConfirmText("OK")

                                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                        @Override
                                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                            finish();
                                                            System.exit(0);
                                                        }
                                                    })
                                                    .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                        }
                                    })
                                    .show();



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



                params.put("name",j.getStringExtra("name"));
                params.put("add",j.getStringExtra("address"));
                params.put("ph",j.getStringExtra("ph"));
                params.put("la",j.getStringExtra("la"));
                params.put("lo",j.getStringExtra("lo"));
                params.put("ename", name.getText().toString());
                params.put("eid",spiner.getSelectedItem().toString());
                params.put("eph",phone.getText().toString());

// Toast.makeText(MainActivity.this,"submitted",Toast.LENGTH_LONG).show();

//returning parameter
                return params;
            }

        };

// m = Integer.parseInt(ba) - Integer.parseInt(result.getContents());
// balance.setText(m+"");


//Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(Employassign.this);
        requestQueue.add(stringRequest);





    }
});

    }





    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
//        Toast.makeText(getApplicationContext(),dance[position] , Toast.LENGTH_LONG).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }

}
