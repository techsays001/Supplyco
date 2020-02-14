package com.msg91.sendotp.sample;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class regtwouser extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {

    String[] danc = { "Alappuzha", "Ernakulam", "Idukki", "Kannur", "Kasaragod","Kollam","Kottayam","Kozhikode","Malappuram","Palakkad","Pathanamthitta","Thiruvananthapuram","Thrissur","Wayanad"};
       String[] neww = { "male","female","other"};
    SharedPreferences sp;
    TextView nametxt, emailtxt, dobtxt,addresstxt, login_title;
    TextView logo;
    EditText pass,cpass,farm;
    private static int RESULT_LOAD_IMAGE = 1;
    private Bitmap bitmap;
    private Uri filePath;
    Button imgbtn;
    ImageView imgview;
    LinearLayout already_have_account_layout;
    CardView register_card;
    Intent i;
    CheckBox pchek,cpchek;
    Spinner gender;
    SharedPreferences sh;
    Spinner dancetype;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regtwouser);
        i=getIntent();
        gender=findViewById(R.id.gender123);
        pchek=findViewById(R.id.checkBox5);
        cpchek=findViewById(R.id.checkBox6);
        pass= findViewById(R.id.pass123);
        cpass=findViewById(R.id.cpass123);
        nametxt = findViewById(R.id.nametxt);
        emailtxt = findViewById(R.id.emailtxt);
        imgview = findViewById(R.id.imgview123);
        dancetype=findViewById(R.id.room123);
        sp=getSharedPreferences("reg",MODE_PRIVATE);
        dobtxt = findViewById(R.id.dobtxt);
        addresstxt= findViewById(R.id.addresstxt);
        already_have_account_layout = findViewById(R.id.already_have_account_text);
        register_card = findViewById(R.id.register_card);
        //Creating the ArrayAdapter instance having the country list
        sh=getSharedPreferences("loc",MODE_PRIVATE);
        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aas = new ArrayAdapter(this,android.R.layout.simple_spinner_item,danc);
        aas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        dancetype.setAdapter(aas);

        ArrayAdapter as = new ArrayAdapter(this,android.R.layout.simple_spinner_item,neww);
        as.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        gender.setAdapter(as);
      // Toast.makeText(getApplicationContext(),sh.getString("la",null), Toast.LENGTH_LONG).show();
        imgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);

            }


//                Intent intent = new Intent(Intent.ACTION_PICK);
//                intent.setType("image/*");
//                startActivityForResult(intent,IMAGE_PICK_CODE);

        });


        pchek.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {

                    pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    pchek.setText("Hide");
                }
                else
                {

                    pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    pchek.setText("Show");
                }
            }
        });

        cpchek.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {

                    cpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    cpchek.setText("Hide");
                }
                else
                {

                    cpass .setTransformationMethod(PasswordTransformationMethod.getInstance());
                    cpchek.setText("Show");
                }
            }
        });

    }


    public void registerButtonf(View view) { {


    if (pass.getText().toString().isEmpty()){

            pass.setError("Empty Field");
        }

        else if (cpass.getText().toString().isEmpty()){
            cpass.setError("Empty Field");
        }
        else if (pass.getText().toString().length()<=6){

            pass.setError("Password Must Contain 6 Digits");
        }

        else if (cpass.getText().toString().length()<=6){

            cpass.setError("Password Must Contain 6 Digits");
        }

        else if (!(pass.getText().toString().equals(cpass.getText().toString()))) {

            Toast.makeText(regtwouser.this,"Password not match",Toast.LENGTH_LONG).show();

        }

        else

        {
            class UploadImage extends AsyncTask<Bitmap, Void, String> {

                ProgressDialog loading;
                RequestHandler rh = new RequestHandler();

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    loading = ProgressDialog.show(regtwouser.this, "Uploading...", null, true, false);
                }


                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    loading.dismiss();
                    Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();

                    if(s.equals("success"))
                    {

                        new SweetAlertDialog(regtwouser.this, SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("Registration Success")
                                .setContentText("Back To Login!")
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
                                                        Intent in=new Intent(regtwouser.this,Signin.class);
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

                @SuppressLint("WrongThread")
                @Override
                protected String doInBackground(Bitmap... params) {
                    bitmap = params[0];
                    String uploadImage = getStringImage(bitmap);

                    HashMap<String, String> data = new HashMap<>();

                    data.put("sname",i.getStringExtra("name"));
                    data.put("semail",i.getStringExtra("email"));
                    data.put("phone",sp.getString("ph",null));
                    data.put("add",i.getStringExtra("address"));
                    data.put("g",gender.getSelectedItem().toString());
                    data.put("da",dancetype.getSelectedItem().toString());
                    data.put("passw", cpass.getText().toString());
                    data.put("la", sh.getString("la",null));
                    data.put("lo", sh.getString("lo",null));
                    data.put("img",uploadImage);
                    String result = rh.sendPostRequest("https://androidprojectstechsays.000webhostapp.com/Orphanage_manegument_system/user_registration.php", data);

                    return result;
                }
            }
            UploadImage ui = new UploadImage();
            ui.execute(bitmap);
        }
    }





    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
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
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
//        Toast.makeText(getApplicationContext(),dance[position] , Toast.LENGTH_LONG).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }

}



