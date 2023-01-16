package com.example.onlineauctionsystem;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;


public class SaleProduct extends Fragment {

    private ListView lv;
    private View view;
    private SaleAdapter adapter;
    private ImageButton searchView;
    private EditText search_txt;

    private Sales sales;

    private List<Sales> list;

    private RequestQueue que;

    public SaleProduct() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_sale_product, container, false);
        lv = view.findViewById(R.id.listview);
        searchView = view.findViewById(R.id.searchView);
        search_txt = view.findViewById(R.id.search_txt);

        que = Volley.newRequestQueue(requireContext());
        que.start();
        loadList();





         searchView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String txt = search_txt.getText().toString();
                 for(int i=0;i<list.size();i++){
                     if(txt.trim().equals(list.get(i).name)){
                         lv.smoothScrollToPosition(i);
                     }

                 }

             }
         });
        return view;
    }


    private void loadList(){
        list = new ArrayList<>();
        String url = Helper.ip+"getProducts?uid="+Helper.uid+"&cid="+Helper.cid;
        Log.e("URL",url);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i=0;i< response.length();i++){
                    try {
                        sales = new Sales();
                        sales.id = Integer.parseInt(String.valueOf(response.getJSONObject(i).getInt("Id")));
                        sales.name = response.getJSONObject(i).getString("name");
                        sales.description = response.getJSONObject(i).getString("description");
                        sales.img = response.getJSONObject(i).getString("image");
                        sales.price = Double.parseDouble(String.valueOf(response.getJSONObject(i).getDouble("price")));
                        sales.uid = Integer.parseInt(String.valueOf(response.getJSONObject(i).getInt("uid")));
                        list.add(sales);
                        adapter = new SaleAdapter(getContext(),list,false);
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