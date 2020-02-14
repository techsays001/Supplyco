
package com.msg91.sendotp.sample;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.msg91.sendotp.sample.ui.gallery.NurseFragment;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Request extends AppCompatActivity {
    TextView pname, pname2, pname3;
    ImageView pname4;
    Intent i;
    private Bitmap bitmap;
    private Uri filePath;
    Button bt;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        pname = findViewById(R.id.pname);
        bt = findViewById(R.id.p_btn);

        pname2 = findViewById(R.id.pname2);
        pname3 = findViewById(R.id.pname3);
        pname4 = findViewById(R.id.pname4);



        i = getIntent();
       //
        pname.setText(i.getStringExtra("imag"));
        pname2.setText(i.getStringExtra("name1"));
        pname3.setText(i.getStringExtra("name2"));

        Picasso.get().load(i.getStringExtra("image")).into(pname4);



        i = getIntent();
        pname.setText(i.getStringExtra("imag"));
        pname2.setText(i.getStringExtra("name1"));
        pname3.setText(i.getStringExtra("name2"));
        Picasso.get().load(i.getStringExtra("image")).into(pname4);

        sh = Objects.requireNonNull(getApplicationContext()).getSharedPreferences("data", MODE_PRIVATE);


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j = new Intent(getApplicationContext(), Purchaseconfirm.class);
                j.putExtra("amount", pname3.getText().toString());
                j.putExtra("pname", pname2.getText().toString());
                j.putExtra("pd", pname.getText().toString());

                j.putExtra("image",i.getStringExtra("image"));


                startActivity(j);
            }
        });


    }
}




































