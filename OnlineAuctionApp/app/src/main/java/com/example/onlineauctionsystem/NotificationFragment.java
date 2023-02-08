package com.example.onlineauctionsystem;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

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


public class NotificationFragment extends Fragment {

    private View view;
    private ListView lv,lv2;
    private RequestQueue queue;
    private History h,h2;
    List<History> list,list2;
    HistoryAdapter adapter,adapter2;
    public NotificationFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_notification, container, false);
        lv = view.findViewById(R.id.listview);
        lv2 = view.findViewById(R.id.listview);
        queue = Volley.newRequestQueue(requireContext());
        queue.start();
        loadList();
        loadList2();
        return view;
    }

    private  void loadList(){
        list = new ArrayList<>();
        String url = Helper.ip+"notification?id="+Helper.uid;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i< response.length();i++){
                    try {
                        h = new History();
                        h.name = response.getJSONObject(i).getString("name");
                        h.image = response.getJSONObject(i).getString("image");
                        h.price = Double.parseDouble(String.valueOf(response.getJSONObject(i).getDouble("price")));
                        list.add(h);
                        adapter = new HistoryAdapter(requireContext(),list,0);
                        lv.setAdapter(adapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        },null);

        queue.add(request);
    }

    private  void loadList2(){
        list2= new ArrayList<>();
        String url = Helper.ip+"notificationWish";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i< response.length();i++){
                    try {
                        h2 = new History();
                        h2.name = response.getJSONObject(i).getString("name");
                        h2.image = response.getJSONObject(i).getString("image");
                        h2.price = Double.parseDouble(String.valueOf(response.getJSONObject(i).getDouble("price")));
                        list2.add(h);
                        adapter2 = new HistoryAdapter(requireContext(),list,2);
                        lv2.setAdapter(adapter2);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        },null);

        queue.add(request);
    }
}