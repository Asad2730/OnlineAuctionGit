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

public class ProductAdapter extends ArrayAdapter {

    private List<Sales> list;
    private ImageView img;
    private TextView name;
    private Button delete;
    private RequestQueue que;
    private Context context;

    public ProductAdapter(@NonNull Context context,List<Sales> list) {
        super(context, R.layout.product_items,list);
        this.list = list;
        this.context = context;
        que = Volley.newRequestQueue(context);
        que.start();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate( R.layout.product_items, parent, false);
        }

        img = convertView.findViewById(R.id.img);
        name = convertView.findViewById(R.id.name);
        delete = convertView.findViewById(R.id.delete);
        name.setText(list.get(position).name);

        delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String url = Helper.ip+"deleteProduct?id="+list.get(position).id;
                    StringRequest request = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                                    context.startActivity(new Intent(context,MainActivity.class));
                                }
                            },null);
                    que.add(request);
                }
            });


        Picasso.get().load(Helper.imgUrl+list.get(position).img).into(img);


        return  convertView;
    }
}
