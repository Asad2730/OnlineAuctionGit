package com.example.onlineauctionsystem;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

public class SaleAdapter extends ArrayAdapter {

    private Context context;
    private List<Sales> list;
    private ImageView img;
    private TextView name;

    public SaleAdapter(@NonNull Context context, List<Sales> list) {
        super(context, R.layout.sales_row,list);
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.sales_row, parent, false);
        }

        img = convertView.findViewById(R.id.img);
        name = convertView.findViewById(R.id.name);
        name.setText(list.get(position).name);

        Picasso.get().load(Helper.imgUrl+list.get(position).img).into(img);
        Log.e("IMG",Helper.imgUrl+list.get(position).img);
        return  convertView;
    }
}
