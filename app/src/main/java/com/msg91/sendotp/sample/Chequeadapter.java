package com.msg91.sendotp.sample;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static android.content.Context.MODE_PRIVATE;

public class Chequeadapter extends RecyclerView.Adapter<Chequeadapter.ProductViewHolder> {
    Intent i;
SharedPreferences sh,shp;
String a,b,c;
    private Context mCtx;
    private List<Cheque> productList;

    public Chequeadapter(Context mCtx, List<Cheque> productList) {
        sh = Objects.requireNonNull(mCtx).getSharedPreferences("data", MODE_PRIVATE);
        shp=mCtx.getSharedPreferences("reh",MODE_PRIVATE);
      //  Toast.makeText(mCtx,shp.getString("rid",null),Toast.LENGTH_LONG).show();
        this.mCtx = mCtx;
        this.productList = productList;

    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.recycler_c, null);
        return new ProductViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        final Cheque cheque = productList.get(position);

        holder.name.setText(cheque.getImage());
        holder.detalis.setText(cheque.getStatus());

        Picasso.get().load(cheque.getUser1()).into(holder.image);
        holder.ph.setText(cheque.getUser2());
        holder.idd.setText(cheque.getUser3());
holder.rid.setText(shp.getString("rid",null));



        {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://androidprojectstechsays.000webhostapp.com/Supplyco_manegument_sydtem/reshen_view.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
//Toast.makeText(Signin.this, response, Toast.LENGTH_LONG).show();
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject json_obj = jsonArray.getJSONObject(i);
//ba = json_obj.getString("balance");
                                    a=json_obj.getString("rname");
                                    b=json_obj.getString("rid");
                                    c=json_obj.getString("image");



                                }
//Toast.makeText(Recharge.this, "your new balnce is "+ba, Toast.LENGTH_LONG).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            //
                            if (response.contains("success")) {
                                //  Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();
                                SharedPreferences shp=mCtx.getSharedPreferences("reh",MODE_PRIVATE);
                                SharedPreferences.Editor ee=shp.edit();
                                ee.putString("rname",a);
                                ee.putString("rid",b);
                                ee.putString("image",c);
                                ee.apply();

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

                    params.put("ph", sh.getString("phone",null));

                    return params;
                }

            };

            RequestQueue requestQueue = Volley.newRequestQueue(mCtx);
            requestQueue.add(stringRequest);



        }






//holder.call.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View view) {
//
//
//        Intent intent = new Intent(Intent.ACTION_DIAL);
//        intent.setData(Uri.parse("tel:"+cheque.getStatus()));
//        mCtx.startActivity(intent);
//
//
//
//
//    }
//});

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

//
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(holder.rid.getText().toString().isEmpty()){



                    new SweetAlertDialog(mCtx, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Reshen Cared Not Added")
                            .setContentText("Add!")
                            .setConfirmText("Yes")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog
                                            .setTitleText("Add cared...!")

                                            .setConfirmText("OK")

                                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                @Override
                                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                    Intent ji = new Intent(mCtx, Reshencard.class);
                                                    mCtx.startActivity(ji);
                                                }
                                            })
                                            .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                }
                            })
                            .show();


//                    Intent ji = new Intent(mCtx, Reshencard.class);
//                    mCtx.startActivity(ji);
//
                }
                else {

                    Intent j = new Intent(mCtx, Subsudypayment.class);
                    j.putExtra("pname", cheque.getImage());
                    j.putExtra("price", cheque.getStatus());
                    j.putExtra("img", cheque.getUser1());
                    j.putExtra("sub", cheque.getUser2());
                    j.putExtra("idd", cheque.getUser3());
                    mCtx.startActivity(j);

                }
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


        TextView name,view, detalis, a, ph,app,lo,la, map,idd,rid;
        ImageView image;

        Button buy, del;


        public ProductViewHolder(View itemView) {
            super(itemView);

            sh = Objects.requireNonNull(mCtx).getSharedPreferences("data", MODE_PRIVATE);
            name = itemView.findViewById(R.id.t_discription1);
            ph = itemView.findViewById(R.id.pph2);
            detalis = itemView.findViewById(R.id.pph1);
            image = itemView.findViewById(R.id.t_name11);
        idd= itemView.findViewById(R.id.id123);
            rid= itemView.findViewById(R.id.rid123);

          view= itemView.findViewById(R.id.view);
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

    public void filterList(ArrayList<Cheque> filteredList) {
        productList = filteredList;
        notifyDataSetChanged();
    }

}
