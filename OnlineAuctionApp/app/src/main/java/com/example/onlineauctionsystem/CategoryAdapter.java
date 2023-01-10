package com.example.onlineauctionsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends ArrayAdapter {

    private Context context;
    private List<Category> list;
    public CategoryAdapter(@NonNull Context context,List<Category> list) {
        super(context, R.layout.row_category,list);
        this.context = context;
        this.list = list;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_category, parent, false);
        }

        TextView name = convertView.findViewById(R.id.name);
        name.setText(list.get(position).name);
        return  convertView;
    }

}
