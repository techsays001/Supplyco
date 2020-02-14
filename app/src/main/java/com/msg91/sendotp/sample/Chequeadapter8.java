package com.msg91.sendotp.sample;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
import java.util.List;
import java.util.Map;
import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static android.content.Context.MODE_PRIVATE;

public class Chequeadapter8 extends RecyclerView.Adapter<Chequeadapter8.ProductViewHolder> {
    Intent i;
SharedPreferences sh,shp;
String a,b,c;
    private Context mCtx;
    private List<Cheque8> productList;

    public Chequeadapter8(Context mCtx, List<Cheque8> productList) {

      //  Toast.makeText(mCtx,shp.getString("rid",null),Toast.LENGTH_LONG).show();
        this.mCtx = mCtx;
        this.productList = productList;

    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.recycler_c8, null);
        return new ProductViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        final Cheque8 cheque = productList.get(position);

        holder.name.setText(cheque.getImage());
        holder.address.setText(cheque.getStatus());

        holder.pho.setText(cheque.getUser1());
        holder.laa.setText(cheque.getUser2());
        holder.loo.setText(cheque.getUser3());









holder.call.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {


        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+cheque.getUser1()));
        mCtx.startActivity(intent);




    }
});

        holder.mapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q="+cheque.getUser2()+","+cheque.getUser3()));
                mCtx.startActivity(intent);







            }
        });



//        holder.share.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//
//
//                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
//                sharingIntent.setType("text/plain");
//                String shareBody = cheque.getUser1()+"\t\t\t\t"+"phone number :"+"\t\t\t"+cheque.getStatus()+"\t\t\t\t"+"busname :"+"\t\t\t"+cheque.getUser2()+"\t\t\t\t"+"oner name :"+"\t\t\t"+cheque.getImage();
//                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
//                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
//                mCtx.startActivity(sharingIntent);
//
//            }
//
//        });



//
//        holder.msg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//if(holder.sms.getText().toString().isEmpty()){
//
//    holder.sms.setError("Enter a message");
//}
//else {
//
//
//    Uri uri = Uri.parse("smsto:" + cheque.getStatus());
//    Intent i = new Intent(Intent.ACTION_SENDTO, uri);
//    i.putExtra("sms_body", holder.sms.getText().toString());
//    i.setPackage("com.android.mms");
//    mCtx.startActivity(i);
//    holder.sms.getText().clear();
//}
//
//            }
//        });


    }


    @Override
    public int getItemCount() {
        return productList.size();
    }



    class ProductViewHolder extends RecyclerView.ViewHolder {


        TextView name, address, pho, laa, loo,call,mapp;

        Button buy, del;


        public ProductViewHolder(View itemView) {
            super(itemView);


            name = itemView.findViewById(R.id.username12);
            pho = itemView.findViewById(R.id.userphone12);
            address = itemView.findViewById(R.id.useraddress12);
           laa = itemView.findViewById(R.id.la35);
          loo = itemView.findViewById(R.id.lo35);
         call = itemView.findViewById(R.id.call35);
           mapp = itemView.findViewById(R.id.map35);

        }
    }}