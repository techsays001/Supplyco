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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class Chequeadapter4 extends RecyclerView.Adapter<Chequeadapter4.ProductViewHolder> {
    Intent i;
SharedPreferences sh;

    private Context mCtx;
    private List<Cheque4> productList;

    public Chequeadapter4(Context mCtx, List<Cheque4> productList) {

      //  sh = Objects.requireNonNull(mCtx).getSharedPreferences("data", MODE_PRIVATE);
        this.mCtx = mCtx;
        this.productList = productList;
       // sh=mCtx.getSharedPreferences("Official1",MODE_PRIVATE);
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.recycler_cno, null);
        return new ProductViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        final Cheque4 cheque = productList.get(position);

        holder.name.setText(cheque.getImage());
        holder.detalis.setText(cheque.getStatus());

        Picasso.get().load(cheque.getUser1()).into(holder.image);
        holder.ph.setText(cheque.getUser2());
        holder.idd.setText(cheque.getUser3());

        sh = Objects.requireNonNull(mCtx).getSharedPreferences("data", MODE_PRIVATE);


        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j = new Intent(mCtx, Vechilepayment.class);
                j.putExtra("pname",cheque.getImage());
                j.putExtra("price",cheque.getStatus());
                j.putExtra("img",cheque.getUser1());
                j.putExtra("sub",cheque.getUser2());
                j.putExtra("idd",cheque.getUser3());
                mCtx.startActivity(j);


            }
        });




    }


    @Override
    public int getItemCount() {
        return productList.size();
    }



    class ProductViewHolder extends RecyclerView.ViewHolder {


        TextView name,view, detalis, a, ph,app,lo,la, map,idd;
        ImageView image;

        Button buy, del;


        public ProductViewHolder(View itemView) {
            super(itemView);

            sh = Objects.requireNonNull(mCtx).getSharedPreferences("data", MODE_PRIVATE);
            name = itemView.findViewById(R.id.t_discription11);
            ph = itemView.findViewById(R.id.pph21);
            detalis = itemView.findViewById(R.id.pph111);
            image = itemView.findViewById(R.id.t_name111);

        idd= itemView.findViewById(R.id.id123);
          view= itemView.findViewById(R.id.view1);
        }

//    @Override
//    public int getItemCount() {
//        return productList.size();
//    }
//
//    class ProductViewHolder extends RecyclerView.ViewHolder {
//
//

//        public ProductViewHolder(View itemView) {
//            super(itemView);
//
//

////            review=itemView.findViewById(R.id.re);
////            viewreview=itemView.findViewById(R.id.ve);
////          //  pid=itemView.findViewById(R.id.productidd);
//
//        }
//
//    }

    }

    public void filterList4(ArrayList<Cheque4> filteredList) {
        productList = filteredList;
        notifyDataSetChanged();
    }

}
