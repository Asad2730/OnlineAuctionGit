package com.example.onlineauctionsystem;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class MyProductFragment extends Fragment {

    private ListView lv;
    private List<Sales> list;
    private View view;
    private RequestQueue que;
    private Sales sales;

    private ProductAdapter adapter;

    public MyProductFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        que = Volley.newRequestQueue(requireContext());
        que.start();
        view = inflater.inflate(R.layout.fragment_my_product, container, false);
        lv = view.findViewById(R.id.listview);
        loadList();
        return view;
    }

    private void loadList(){
        list = new ArrayList<>();
        String url = Helper.ip+"getMyProducts?uid="+Helper.uid;
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
                        adapter = new ProductAdapter(requireContext(),list);
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