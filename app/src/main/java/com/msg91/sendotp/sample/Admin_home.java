package com.msg91.sendotp.sample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Admin_home extends AppCompatActivity {
Button phycologist_btn,terapy_btn,event,c,em,log;
    SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);


c=findViewById(R.id.co);
        phycologist_btn=findViewById(R.id.phycologist_btn);
        terapy_btn=findViewById(R.id.terapy_btn);
         event=findViewById(R.id.event);
        em=findViewById(R.id.em);
        sh=getSharedPreferences("Official1",MODE_PRIVATE);
       log=findViewById(R.id.log11);
        SharedPreferences.Editor e=sh.edit();
        e.putBoolean("ph",true);

        e.apply();

log.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
            SharedPreferences.Editor e = sh.edit();
            e.clear();
            e.apply();
            startActivity(new Intent(getApplication(), Admin_login.class).setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
});



//        event.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent jj= new Intent(getApplicationContext(),Event_admin.class);
//                startActivity(jj );
//            }
//        });
//
//
//
//terapy_btn.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View view) {
//        Intent j= new Intent(getApplicationContext(),Eventokadminview.class);
//        startActivity(j);
//    }
//});
//
//        phycologist_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i= new Intent(getApplicationContext(), Teachers_detailes_Admin.class);
//                startActivity(i);
//            }
//        });
//
//
//
//
//        c.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent ie= new Intent(getApplicationContext(), Eventokadminview1.class);
//                startActivity(ie);
//            }
//        });
//        em.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent ie= new Intent(getApplicationContext(), Phycology.class);
//                startActivity(ie);
//            }
//        });

    }



}

