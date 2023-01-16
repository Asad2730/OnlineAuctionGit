package com.example.onlineauctionsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class FavouriteActivity extends AppCompatActivity {

    private ListView lv;
    private SaleAdapter adapter;
    private Sales sales;
    private List<Sales> list;
    private RequestQueue que;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        lv = findViewById(R.id.listview);

        que = Volley.newRequestQueue(this);
        que.start();
        loadList();
    }

    private void loadList(){
        list = new ArrayList<>();
        String url = Helper.ip+"getFavourite?uid="+Helper.uid;
        Log.e("URL",url);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i=0;i< response.length();i++){
                    try {
                        sales = new Sales();
                        sales.id = Integer.parseInt(String.valueOf(response.getJSONObject(i).getInt("id")));
                        sales.name = response.getJSONObject(i).getString("name");
                        sales.description = response.getJSONObject(i).getString("description");
                        sales.img = response.getJSONObject(i).getString("image");
                        sales.price = Double.parseDouble(String.valueOf(response.getJSONObject(i).getDouble("price")));
                        sales.uid = Integer.parseInt(String.valueOf(response.getJSONObject(i).getInt("uid")));
                        list.add(sales);
                        adapter = new SaleAdapter(getApplicationContext(),list,true);
                        lv.setAdapter(adapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        },null);

        que.add(request);

    }
}