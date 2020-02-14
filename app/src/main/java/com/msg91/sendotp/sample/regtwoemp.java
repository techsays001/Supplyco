package com.msg91.sendotp.sample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
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

import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class regtwoemp extends AppCompatActivity implements
    AdapterView.OnItemSelectedListener {
        String[] genderr = { "Male", "Female","Other"};
    String[] danc = { "Alappuzha", "Ernakulam", "Idukki", "Kannur", "Kasaragod","Kollam","Kottayam","Kozhikode","Malappuram","Palakkad","Pathanamthitta","Thiruvananthapuram","Thrissur","Wayanad"};
//    String[] neww = { "Keral","Thamilnadu","karnataka","Maharashtra","Goa","Rajasthan","Punjab","Himachal Pradesh"};

SharedPreferences sp;
    TextView nametxt, emailtxt, dobtxt,addresstxt, login_title;
    TextView logo;
    EditText pass,age;

    LinearLayout already_have_account_layout;
    CardView register_card;
    Intent i;

    Spinner dancetype,gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regtwoemp);


        ConstraintLayout constraintLayout = findViewById(R.id.rt);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();



        i=getIntent();

        pass= findViewById(R.id.pass);

        nametxt = findViewById(R.id.nametxt);
        emailtxt = findViewById(R.id.emailtxt);
//        newstudent=findViewById(R.id.newst);
        dancetype=findViewById(R.id.dancetyyy);
        gender=findViewById(R.id.gender);
        age=findViewById(R.id.age);
        sp=getSharedPreferences("reg",MODE_PRIVATE);
        dobtxt = findViewById(R.id.dobtxt);
        addresstxt= findViewById(R.id.addresstxt);
        gender.setOnItemSelectedListener(this);
        already_have_account_layout = findViewById(R.id.already_have_account_text);
        register_card = findViewById(R.id.register_card);
        //Creating the ArrayAdapter instance having the country list


        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aas = new ArrayAdapter(this,android.R.layout.simple_spinner_item,danc);
        aas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        dancetype.setAdapter(aas);



//        Toast.makeText(regtwo.this,projectname.getSelectedItem().toString(),Toast.LENGTH_LONG).show();
//        ArrayAdapter ap = new ArrayAdapter(this,android.R.layout.simple_spinner_item,neww);
//        ap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        //Setting the ArrayAdapter data on the Spinner
//        newstudent.setAdapter(ap);



        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,genderr);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        gender.setAdapter(aa);
//        Toast.makeText(regtwo.this,projectname.getSelectedItem().toString(),Toast.LENGTH_LONG).show();




    }


    public void registerButtonf(View view) { {

        if (age.getText().toString().isEmpty()) {

            age.setError("Empty Field");
        }
       else if (pass.getText().toString().isEmpty()){

            pass.setError("Empty Field");
        }

        else if (pass.getText().toString().isEmpty()){

            pass.setError("empty field");
        }



     else

        {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://androidprojectstechsays.000webhostapp.com/Vehicle_Managemen_system/Emp_registration.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
//If we are getting success from server
                            Toast.makeText(regtwoemp.this,response,Toast.LENGTH_LONG).show();
                            if(response.equals("Registration Successful"))
                            {

                                new SweetAlertDialog(regtwoemp.this, SweetAlertDialog.WARNING_TYPE)
                                        .setTitleText("Registration Success")
                                        .setContentText("Back to Home")
                                        .setConfirmText("OK")
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sDialog) {
                                                sDialog
                                                        .setTitleText("Logining...!")

                                                        .setConfirmText("OK")

                                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                            @Override
                                                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                                Intent in=new Intent(regtwoemp.this, Signin.class);
                                                                 startActivity(in);
                                                            }
                                                        })
                                                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                            }
                                        })
                                        .show();




//
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



                    params.put("sname",i.getStringExtra("name"));
                    params.put("semail",i.getStringExtra("email"));
                    params.put("phone",sp.getString("ph",null));
                    params.put("sdob",i.getStringExtra("dob"));
                    params.put("add",i.getStringExtra("address"));



                    params.put("age", age.getText().toString());
                    params.put("g",gender.getSelectedItem().toString().toLowerCase());
//                    params.put("nst",newstudent.getSelectedItem().toString().toLowerCase());
                    params.put("da",dancetype.getSelectedItem().toString().toLowerCase());
                    params.put("passw", pass.getText().toString());

// Toast.makeText(MainActivity.this,"submitted",Toast.LENGTH_LONG).show();

//returning parameter
                    return params;
                }

            };

// m = Integer.parseInt(ba) - Integer.parseInt(result.getContents());
// balance.setText(m+"");


//Adding the string request to the queue
            RequestQueue requestQueue = Volley.newRequestQueue(regtwoemp.this);
            requestQueue.add(stringRequest);
        }



    }

}

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
//        Toast.makeText(getApplicationContext(),dance[position] , Toast.LENGTH_LONG).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }

}

