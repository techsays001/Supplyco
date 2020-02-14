package com.msg91.sendotp.sample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

public class Signinnurse extends AppCompatActivity {
TextView signin,fpass;
    Button login;
EditText uph,passok;
String abc;
    private boolean loggedIn = false;
    SharedPreferences sharedPreferences,sh;
    CheckBox check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signinnurse);

        login = findViewById(R.id.login12);
        uph = findViewById(R.id.dw12);
    check=findViewById(R.id.checkBox12);
        passok=findViewById(R.id.passok12);





        sh=getSharedPreferences("Official23",MODE_PRIVATE);
        loggedIn=sh.getBoolean("ph23",false);
        sharedPreferences=getSharedPreferences("phone",MODE_PRIVATE);
        if (loggedIn) {
            startActivity(new Intent(Signinnurse.this,MainActivityhomeemp.class));
            // Snackbar.make(v,"Enter emergency number",Snackbar.LENGTH_SHORT).show();

        }
//        fpass.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//               Intent h=new Intent(getApplicationContext(),MainActivityforgotnuse.class);
//                startActivity(h);
//            }
//        });

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
           else if (uph.getText().toString().isEmpty()){

                uph.setError("enter a valid phone number ");

            }


            else {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://androidprojectstechsays.000webhostapp.com/Supplyco_manegument_sydtem/emp_login.php",
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
                                        abc=json_obj.getString("idd");





                                    }
//Toast.makeText(Recharge.this, "your new balnce is "+ba, Toast.LENGTH_LONG).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                // Toast.makeText(Signin.this, "success", Toast.LENGTH_LONG).show();
                                if (response.contains("success")) {

                                    Intent in = new Intent(Signinnurse.this, MainActivityhomeemp.class);
                                    SharedPreferences shh=getSharedPreferences("dataemp",MODE_PRIVATE);
                                    SharedPreferences.Editor ee=shh.edit();
                                    ee.putString("eid",abc);



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
           params.put("pass",passok.getText().toString());

//returning parameter
                        return params;
                    }

                };

// m = Integer.parseInt(ba) - Integer.parseInt(result.getContents());
// balance.setText(m+"");


//Adding the string request to the queue
                RequestQueue requestQueue = Volley.newRequestQueue(Signinnurse.this);
                requestQueue.add(stringRequest);
            }

        }


        });

//
//        signin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                    Intent i = new Intent(getApplicationContext(), MainActivitynuse.class);
//                    startActivity(i);
//
//            }
//        });
    }


}
//   params.put("phone", uph.getText().toString());
//           params.put("pass",passok.getText().toString());
//https://androidprojectstechsays.000webhostapp.com/Supplyco_manegument_sydtem/emp_login.php",