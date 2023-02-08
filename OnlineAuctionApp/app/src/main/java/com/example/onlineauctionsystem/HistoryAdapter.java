package com.example.onlineauctionsystem;

import android.content.Context;
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

public class HistoryAdapter extends ArrayAdapter {
    private Context context;
    private List<History> list;
    public HistoryAdapter(@NonNull Context context, List<History> list) {
        super(context, R.layout.history_item,list);
        this.context = context;
        this.list = list;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.history_item, parent, false);
        }

        TextView name = convertView.findViewById(R.id.name);
        TextView price = convertView.findViewById(R.id.price);
        TextView offer = convertView.findViewById(R.id.offer);
        ImageView img = convertView.findViewById(R.id.image);

        name.setText("Name:"+list.get(position).name);
        price.setText("Price:"+list.get(position).price);
        offer.setText("Bid Price:"+list.get(position).offer);
        Picasso.get().load(Helper.imgUrl+list.get(position).image).into(img);
        return  convertView;
    }

}
