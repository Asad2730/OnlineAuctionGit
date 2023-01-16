package com.example.onlineauctionsystem;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SaleAdapter extends ArrayAdapter {

    private Context context;
    private List<Sales> list;
    private ImageView img;
    private TextView name;
    private Button favourite;
    private boolean hideBtn;
    private RequestQueue que;
    private Button detail;


    public SaleAdapter(@NonNull Context context, List<Sales> list,boolean hideBtn) {
        super(context, R.layout.sales_row,list);
        this.context = context;
        this.list = list;
        this.hideBtn = hideBtn;
        que = Volley.newRequestQueue(context);
        que.start();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.sales_row, parent, false);

        }

        img = convertView.findViewById(R.id.img);
        name = convertView.findViewById(R.id.name);
        favourite = convertView.findViewById(R.id.favourite);
        detail = convertView.findViewById(R.id.detail);

        name.setText(list.get(position).name);
        Log.e("URL",list.get(position).name);

        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ProductList.class);
                Helper.selected_product = list.get(position);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        if(!hideBtn){
           favourite.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   String url = Helper.ip+"insertFavourite?uid="+Helper.uid+"&pid="+list.get(position).id;
                   StringRequest request = new StringRequest(Request.Method.POST, url,
                           new Response.Listener<String>() {
                       @Override
                       public void onResponse(String response) {
                           Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                       }
                   },null);
                   que.add(request);
               }
           });

        }else{
            favourite.setVisibility(View.GONE);
        }

        Picasso.get().load(Helper.imgUrl+list.get(position).img).into(img);

        return  convertView;
    }
}
