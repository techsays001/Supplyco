package com.msg91.sendotp.sample;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Signin extends AppCompatActivity {
TextView signin,admin_btn,fpass,emp;
    Button login;
EditText uph,passok;
String a,b,c,d,e,f,g,h,ii;
    private boolean loggedIn = false;
    SharedPreferences sharedPreferences,sh;
    CheckBox check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

EnableRuntimePermission();




emp=findViewById(R.id.emp12);
        signin = findViewById(R.id.signin);
        login = findViewById(R.id.login);
        uph = findViewById(R.id.dw);
    check=findViewById(R.id.checkBox);
        passok=findViewById(R.id.passok);
        admin_btn = findViewById(R.id.admin_btn);
        fpass=findViewById(R.id.fro);
        sh=getSharedPreferences("Official21",MODE_PRIVATE);
        loggedIn=sh.getBoolean("ph21",false);
        sharedPreferences=getSharedPreferences("phone",MODE_PRIVATE);
        if (loggedIn) {
            startActivity(new Intent(Signin.this, MainActivity2.class));
            // Snackbar.make(v,"Enter emergency number",Snackbar.LENGTH_SHORT).show();

        }
        fpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent h=new Intent(getApplicationContext(), MainActivityforgot.class);
                startActivity(h);
            }
        });


        emp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hh=new Intent(getApplicationContext(),Signinnurse.class);
                startActivity(hh);
            }
        });

check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if(b)
        {

            passok.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            check.setText("Hide");
        }
        else
        {

            passok.setTransformationMethod(PasswordTransformationMethod.getInstance());
            check.setText("Show");
        }
    }
});
//emp.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View view) {
//        Intent hh=new Intent(getApplicationContext(),MainActivityemp.class);
//        startActivity(hh);
//    }
//});

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
            if (uph.getText().toString().isEmpty())
              {
                uph.setError("enter a avlid phone number ");
            }
           else if (passok.getText().toString().isEmpty()){
               passok.setError("enter a avlid phone  password");

            }
           else if (uph.getText().toString().length()<10){

                uph.setError("enter a valid phone number ");

            }


            else {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://androidprojectstechsays.000webhostapp.com/Supplyco_manegument_sydtem/user_login.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
//Toast.makeText(Signin.this, response, Toast.LENGTH_LONG).show();

                                uph.getText().clear();

                              passok.getText().clear();
                              try {
                                    JSONArray jsonArray = new JSONArray(response);
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject json_obj = jsonArray.getJSONObject(i);
//ba = json_obj.getString("balance");
                                        a=json_obj.getString("phone");
                                        b=json_obj.getString("la");
                                        c=json_obj.getString("name");
                                        d=json_obj.getString("lo");
                                        e=json_obj.getString("address");
                                        f=json_obj.getString("gender");
                                        g=json_obj.getString("dob");
                                        h=json_obj.getString("idd");
                                        ii=json_obj.getString("email");



                                    }
//Toast.makeText(Recharge.this, "your new balnce is "+ba, Toast.LENGTH_LONG).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                               // Toast.makeText(Signin.this, "success", Toast.LENGTH_LONG).show();
                                if (response.contains("success")) {

                                    Intent in = new Intent(Signin.this, MainActivity2.class);
                                   SharedPreferences sh=getSharedPreferences("data",MODE_PRIVATE);
                                   SharedPreferences.Editor ee=sh.edit();
                                   ee.putString("phone",a);
                                    ee.putString("la",b);
                                    ee.putString("name",c);
                                    ee.putString("lo",d);
                                    ee.putString("address",e);
                                    ee.putString("gender",f);
                                    ee.putString("dob",g);
                                    ee.putString("idd",h);
                                    ee.putString("email",ii);
ee.apply();

                                    startActivity(in);
                                }
                                else
                                {
                                    passok.setError("incorrect credentials");
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
//Adding parameters to request
                        params.put("phone", uph.getText().toString());
params.put("passok",passok.getText().toString());
// Toast.makeText(MainActivity.this,"submitted",Toast.LENGTH_LONG).show();

//returning parameter
                        return params;
                    }

                };

// m = Integer.parseInt(ba) - Integer.parseInt(result.getContents());
// balance.setText(m+"");


//Adding the string request to the queue
                RequestQueue requestQueue = Volley.newRequestQueue(Signin.this);
                requestQueue.add(stringRequest);
            }

        }


        });





admin_btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        Intent j= new Intent(getApplicationContext(), Admin_login.class);
        startActivity(j);
    }
});

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);

            }
        });
    }

    public void EnableRuntimePermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(Objects.requireNonNull(Signin.this),
                Manifest.permission.CAMERA)) {

// Toast.makeText(Cpature_image.this,"CAMERA permission allows us to Access CAMERA app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(Signin.this, new String[]{
                    Manifest.permission.CAMERA, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.READ_EXTERNAL_STORAGE}, 12);


        }
    }

    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {

        switch (RC) {

            case 12:

                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {

// Toast.makeText(Cpature_image.this,"Permission Granted, Now your application can access CAMERA.", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(Signin.this, "Permission Canceled, Now your application cannot access CAMERA.", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }}
