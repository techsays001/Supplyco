package com.msg91.sendotp.sample;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Packegeadd extends AppCompatActivity {
Intent j;


    private static int RESULT_LOAD_IMAGE = 1;
    private Bitmap bitmap;
    private Uri filePath;
    EditText kl,capacity,place,duration,ammount;
    final int RequestPermissionCode = 1;
    ImageView imgview;
    Button update;
    static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packegeadd);
        j=getIntent();
      //  Toast.makeText(this,j.getStringExtra("idd"), Toast.LENGTH_SHORT).show();
        imgview=findViewById(R.id.q1);
        kl=findViewById(R.id.q2);
       capacity=findViewById(R.id.q3);
        place=findViewById(R.id.q4);
      duration=findViewById(R.id.q5);
        ammount=findViewById(R.id.q6);
        update=findViewById(R.id.qbt);
        imgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);

            }



        });







        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (kl.getText().toString().isEmpty()) {

                   kl.setError("null");
                } else if (capacity.getText().toString().isEmpty()) {

                  capacity.setError("null");
                } else if (place.getText().toString().isEmpty()) {

                   place.setError("null");
                } else if (duration.getText().toString().isEmpty()) {

                   duration.setError("null");
                }else if (ammount.getText().toString().isEmpty()) {

                    ammount.setError("null");
                }

                else {


                    class UploadImage extends AsyncTask<Bitmap, Void, String> {

                        ProgressDialog loading;
                        RequestHandler rh = new RequestHandler();

                        @Override
                        protected void onPreExecute() {
                            super.onPreExecute();
                            loading = ProgressDialog.show(Packegeadd.this, "Uploading...", null, true, false);
                        }


                        @Override
                        protected void onPostExecute(String s) {
                            super.onPostExecute(s);
                            loading.dismiss();
                           //  Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();


                            if (s.equals("success")) {

                                new SweetAlertDialog(Packegeadd.this, SweetAlertDialog.WARNING_TYPE)
                                        .setTitleText("UPLOADED")
                                        .setContentText("Back To Home!")
                                        .setConfirmText("Yes,Login")
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sDialog) {
                                                sDialog
                                                        .setTitleText("Logining...!")

                                                        .setConfirmText("OK")

                                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                            @Override
                                                            public void onClick(SweetAlertDialog sweetAlertDialog) {
//
                                                                kl.getText().clear();
                                                                capacity.getText().clear();
                                                                place.getText().clear();
                                                                duration.getText().clear();
                                                                ammount.getText().clear();

                                                            }
                                                        })
                                                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                            }
                                        })
                                        .show();


//
                            }
                            else {




                                    new SweetAlertDialog(Packegeadd.this, SweetAlertDialog.WARNING_TYPE)
                                            .setTitleText("UPLOADED FAILED")
                                            .setContentText("Back To Home!")
                                            .setConfirmText("Yes")
                                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                @Override
                                                public void onClick(SweetAlertDialog sDialog) {
                                                    sDialog
                                                            .setTitleText("Logining...!")

                                                            .setConfirmText("OK")

                                                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                                @Override
                                                                public void onClick(SweetAlertDialog sweetAlertDialog) {
//
                                                                    kl.getText().clear();
                                                                    capacity.getText().clear();
                                                                    place.getText().clear();
                                                                    duration.getText().clear();
                                                                    ammount.getText().clear();

                                                                }
                                                            })
                                                            .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                                }
                                            })
                                            .show();


//
                                }

                        }

                        @SuppressLint("WrongThread")
                        @Override
                        protected String doInBackground(Bitmap... params) {
                            bitmap = params[0];
                            String uploadImage = getStringImage(bitmap);

                            HashMap<String, String> data = new HashMap<>();


                            data.put("kl", kl.getText().toString());
                            data.put("ca", capacity.getText().toString());
                            data.put("add", place.getText().toString());
                            data.put("du", duration.getText().toString());
                            data.put("price", ammount.getText().toString());
                            data.put("idd", j.getStringExtra("idd"));
                            data.put("img", uploadImage);
                            String result = rh.sendPostRequest("https://androidprojectstechsays.000webhostapp.com/Vehicle_Managemen_system/packege_uploaded_image.php", data);

                            return result;
                        }
                    }
                    UploadImage ui = new UploadImage();
                    ui.execute(bitmap);

                }


            }

        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(Packegeadd.this.getContentResolver(), filePath);
                imgview.setImageBitmap(bitmap);
                getStringImage(bitmap);
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }
    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;

    }}



