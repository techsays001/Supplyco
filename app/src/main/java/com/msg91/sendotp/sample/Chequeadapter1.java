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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static android.content.Context.MODE_PRIVATE;

public class Chequeadapter1 extends RecyclerView.Adapter<Chequeadapter1.ProductViewHolder> {
    SharedPreferences sh;

    private Context mCtx;
    private List<Cheque1> productList;

    public Chequeadapter1(Context mCtx, List<Cheque1> productList) {
        sh = Objects.requireNonNull(mCtx).getSharedPreferences("data", MODE_PRIVATE);
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.activity_event, null);
        return new ProductViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        final   Cheque1 cheque;   cheque = productList.get(position);

        //loading the image
        Picasso.get().load(cheque.getImage()).into(holder.image);
        holder.name1.setText(cheque.getUser());
        holder.age.setText(cheque.getPrize());
        holder.dob.setText(cheque.getStatus());
        holder.gender.setText(cheque.getDes());
        holder.rs.setText(cheque.getph());
        holder.idd.setText(cheque.getph1());








        holder.apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent j = new Intent(mCtx, Vechilepayment.class);
                j.putExtra("rs",cheque.getph());
                j.putExtra("img",cheque.getImage());
                j.putExtra("kl",cheque.getStatus());
                j.putExtra("sc",cheque.getUser());
                j.putExtra("place",cheque.getPrize());
                j.putExtra("d",cheque.getDes());
                j.putExtra("idd",cheque.getph1());
                mCtx.startActivity(j);



            }








        });


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {



        TextView name1,age,dob,gender,phh,rs,idd;
ImageView image;
EditText sms;
Button apply;
        public ProductViewHolder(View itemView) {
            super(itemView);




           image=itemView.findViewById(R.id.cimage);
           name1=itemView.findViewById(R.id.cname1);
           age=itemView.findViewById(R.id.cage);
         dob=itemView.findViewById(R.id.cdob);
            gender=itemView.findViewById(R.id.cgender);
           phh=itemView.findViewById(R.id.cph);
            apply=itemView.findViewById(R.id.apply);
           rs=itemView.findViewById(R.id.crs);
            idd=itemView.findViewById(R.id.iddq);
        }

    }



}