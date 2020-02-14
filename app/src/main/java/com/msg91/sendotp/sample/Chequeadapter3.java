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

public class Chequeadapter3 extends RecyclerView.Adapter<Chequeadapter3.ProductViewHolder> {
    Intent i;
SharedPreferences sh;

    private Context mCtx;
    private List<Cheque3> productList;

    public Chequeadapter3(Context mCtx, List<Cheque3> productList) {
        sh = Objects.requireNonNull(mCtx).getSharedPreferences("data", MODE_PRIVATE);
        this.mCtx = mCtx;
        this.productList = productList;
       // sh=mCtx.getSharedPreferences("Official1",MODE_PRIVATE);
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.recycler_c3, null);
        return new ProductViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        final Cheque3 cheque = productList.get(position);

        holder.name.setText(cheque.getImage());
        holder.detalis.setText(cheque.getStatus());

        Picasso.get().load(cheque.getUser1()).into(holder.image);
        holder.ph.setText(cheque.getUser2());
        holder.idd.setText(cheque.getUser3());

        sh = Objects.requireNonNull(mCtx).getSharedPreferences("data", MODE_PRIVATE);

holder.call.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {


        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+cheque.getStatus()));
        mCtx.startActivity(intent);




    }
});

//        holder.map.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q="+cheque.getUser3()+","+cheque.getUser4()));
//                mCtx.startActivity(intent);
//
//
//
//
//
//
//
//            }
//        });


        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j = new Intent(mCtx, Packegeadd.class);
                j.putExtra("idd",cheque.getUser3());
                mCtx.startActivity(j);


            }
        });





        holder.msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
if(holder.sms.getText().toString().isEmpty()){

    holder.sms.setError("Enter a message");
}
else {


    Uri uri = Uri.parse("smsto:" + cheque.getStatus());
    Intent i = new Intent(Intent.ACTION_SENDTO, uri);
    i.putExtra("sms_body", holder.sms.getText().toString());
    i.setPackage("com.android.mms");
    mCtx.startActivity(i);
    holder.sms.getText().clear();
}

            }
        });


    }


    @Override
    public int getItemCount() {
        return productList.size();
    }



    class ProductViewHolder extends RecyclerView.ViewHolder {


        TextView name,view, detalis, a, ph,app,call,msg,lo,la, map,idd;
        ImageView image;
        EditText sms;
        Button buy, del;


        public ProductViewHolder(View itemView) {
            super(itemView);

            sh = Objects.requireNonNull(mCtx).getSharedPreferences("data", MODE_PRIVATE);
            name = itemView.findViewById(R.id.t_discription15);
            ph = itemView.findViewById(R.id.pph25);
            detalis = itemView.findViewById(R.id.pph15);
            image = itemView.findViewById(R.id.t_name115);

        call = itemView.findViewById(R.id.phcall5);
          msg= itemView.findViewById(R.id.phmsg5);
          idd= itemView.findViewById(R.id.idd15);
           sms= itemView.findViewById(R.id.smstxt5);

          view= itemView.findViewById(R.id.view5);
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

    public void filterList1(ArrayList<Cheque3> filteredList) {
        productList = filteredList;
        notifyDataSetChanged();
    }

}
