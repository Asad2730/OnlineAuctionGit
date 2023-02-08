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

public class HistoryFragment extends Fragment {

    private View view;
    private RequestQueue queue;
    History h;
    List<History> list;
    HistoryAdapter adapter;
    private ListView lv;
    public HistoryFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_history, container, false);
        queue = Volley.newRequestQueue(requireContext());
        queue.start();
        lv=view.findViewById(R.id.listview);
        loadList();
        return view;
    }


    private  void loadList(){
        list = new ArrayList<>();
        String url = Helper.ip+"history";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i< response.length();i++){
                    try {
                        h = new History();
                        h.name = response.getJSONObject(i).getString("name");
                        h.image = response.getJSONObject(i).getString("image");
                        h.price = Double.parseDouble(String.valueOf(response.getJSONObject(i).getDouble("price")));
                        h.offer = Double.parseDouble(String.valueOf(response.getJSONObject(i).getDouble("offer")));
                        list.add(h);
                       adapter = new HistoryAdapter(requireContext(),list);
                       lv.setAdapter(adapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        },null);

        queue.add(request);
    }
}