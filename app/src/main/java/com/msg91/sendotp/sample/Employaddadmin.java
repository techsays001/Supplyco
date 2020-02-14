package com.msg91.sendotp.sample;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Employaddadmin extends AppCompatActivity {
    SharedPreferences shq, shh, logout;
    Button btn, im;
    Location location;
    private static int RESULT_LOAD_IMAGE = 1;
    private Bitmap bitmap;
    private Uri filePath;
    EditText name,ph,idd,details;
    SharedPreferences sh;
    Spinner spiner;
    final int RequestPermissionCode = 1;
    ImageView imgview;
    String address, city, state, country, postalCode, knownName;
    Button update;
    static final int REQUEST_CODE = 1;
    List<String> imagesEncodedList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employaddadmin);




        imgview=findViewById(R.id.imgtravell12);
        name=findViewById(R.id.tvame12);
        ph=findViewById(R.id.tvph12);
        update=findViewById(R.id.tvbt12);
        idd=findViewById(R.id.tvid12);





        imgview.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

                if (Employaddadmin.this.checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
                }
                else
                {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, 2);

                    //startActivityForResult(image, 200);
                }

            }



        });







        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (name.getText().toString().isEmpty()) {

                    name.setError("null");
                } else if (ph.getText().toString().isEmpty()) {

                    ph.setError("null");
                }
                else if (idd.getText().toString().isEmpty()) {

                    idd.setError("null");
                }



                else {


                    class UploadImage extends AsyncTask<Bitmap, Void, String> {

                        ProgressDialog loading;
                        RequestHandler rh = new RequestHandler();

                        @Override
                        protected void onPreExecute() {
                            super.onPreExecute();
                            loading = ProgressDialog.show(Employaddadmin.this, "Uploading...", null, true, false);
                        }


                        @Override
                        protected void onPostExecute(String s) {
                            super.onPostExecute(s);
                            loading.dismiss();
                            Toast.makeText(Employaddadmin.this, s, Toast.LENGTH_LONG).show();

                            if (s.equals("success")) {

                                new SweetAlertDialog(Employaddadmin.this, SweetAlertDialog.WARNING_TYPE)
                                        .setTitleText("UPLOADED")
                                        .setContentText("Back To Home!")
                                        .setConfirmText("Yes")
                                        .show();

                                name.getText().clear();
                                ph.getText().clear();
                                idd.getText().clear();

                            }
                            else {

                                new SweetAlertDialog(Employaddadmin.this, SweetAlertDialog.WARNING_TYPE)
                                        .setTitleText("Id Already Enterd")
                                        .setContentText("Back To Home!")
                                        .setConfirmText("Yes")
                                        .show();


                            }

                        }



                        @SuppressLint("WrongThread")
                        @Override
                        protected String doInBackground(Bitmap... params) {
                            bitmap = params[0];
                            String uploadImage = getStringImage(bitmap);

                            HashMap<String, String> data = new HashMap<>();


                            data.put("name", name.getText().toString());
                            data.put("ph", ph.getText().toString());
                            data.put("idd", idd.getText().toString());
                            data.put("img", uploadImage);
                            String result = rh.sendPostRequest("https://androidprojectstechsays.000webhostapp.com/Supplyco_manegument_sydtem/Employ_add.php ", data);

                            return result;
                        }
                    }
                    UploadImage ui = new UploadImage();
                    ui.execute(bitmap);

                }
                {



                    StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://androidprojectstechsays.000webhostapp.com/Supplyco_manegument_sydtem/empsms.php",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
//If we are getting success from server
                                    //  Toast.makeText(mCtx,response,Toast.LENGTH_LONG).show();

                                    if (response.equals("Password sent to your registerd phonenumber")) {
//
                                        new SweetAlertDialog(Employaddadmin.this, SweetAlertDialog.WARNING_TYPE)
                                                .setTitleText("MSG sent ")
                                                .setContentText("Back To Home!")
                                                .show();

                                    } else {


                                        new SweetAlertDialog(Employaddadmin.this, SweetAlertDialog.WARNING_TYPE)
                                                .setTitleText("MSG sent")
                                                .setContentText("Back To Home!")
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
//Adding parameters to request
                            params.put("idd",idd.getText().toString());
                            params.put("ph",ph.getText().toString());
//returning parameter
                            return params;
                        }

                    };

// m = Integer.parseInt(ba) - Integer.parseInt(result.getContents());
// balance.setText(m+"");


//Adding the string request to the queue
                    RequestQueue requestQueue = Volley.newRequestQueue(Employaddadmin.this);
                    requestQueue.add(stringRequest);


                }

            }

        });


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == 2 && resultCode == Activity.RESULT_OK)
        {
            bitmap = (Bitmap) data.getExtras().get("data");
            imgview.setImageBitmap(bitmap);
            getStringImage(bitmap);
        }
    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }


}

