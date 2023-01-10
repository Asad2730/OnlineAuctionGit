package com.example.onlineauctionsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

public class ProductList extends AppCompatActivity {

    private ListView lv;
    private ListAdapter adapter;
    private List<Sales> list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        lv = findViewById(R.id.listview);
        list = new ArrayList<>();
        list.add(Helper.selected_product);
        adapter = new ListAdapter(getApplicationContext(),list);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),ProductDetail.class);
                startActivity(intent);

            }
        });
    }




}