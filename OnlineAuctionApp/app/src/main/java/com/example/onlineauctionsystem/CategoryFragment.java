package com.example.onlineauctionsystem;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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


public class CategoryFragment extends Fragment {

    private ListView lv;
    private View view;
    private CategoryAdapter adapter;
    private ImageButton searchView;
    private EditText search_txt;

    private Category category;

    private RequestQueue que;

    public CategoryFragment() {
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


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Helper.cid = Helper.list .get(i).id;
                FragmentManager manager = getParentFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.container_content_main,new SaleProduct());
                transaction.commit();
            }
        });


        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt = search_txt.getText().toString();
                for(int i=0;i<Helper.list .size();i++){
                    if(txt.trim().equals(Helper.list .get(i).name)){
                        lv.smoothScrollToPosition(i);
                    }

                }

            }
        });
        return view;
    }


    private void loadList(){
        Helper.list = new ArrayList<>();
        String url = Helper.ip+"getCategory";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i=0;i< response.length();i++){

                    try {
                        category = new Category();
                        category.id = Integer.parseInt(String.valueOf(response.getJSONObject(i).getInt("Id")));
                        category.name = response.getJSONObject(i).getString("name");
                        Helper.list.add(category);
                        Toast.makeText(requireContext(), "OK:"+Helper.list.get(i).name, Toast.LENGTH_SHORT).show();
                        adapter = new CategoryAdapter(getContext(),Helper.list );
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