package com.example.onlineauctionsystem;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class AddCategoryFragment extends Fragment {

    private EditText name;
    private Button add;
    private RequestQueue queue;

    public AddCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_category, container, false);
        name = view.findViewById(R.id.name);
        add = view.findViewById(R.id.add);
        queue = Volley.newRequestQueue(requireContext());
        queue.start();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!name.getText().toString().isEmpty()){
                    JSONObject obj = new JSONObject();
                    try {
                        String url = Helper.ip+"addCategory";
                        obj.put("name",name.getText().toString());
                        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, obj,
                                new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(requireContext(), "Data Saved!", Toast.LENGTH_SHORT).show();
                                FragmentManager manager = getParentFragmentManager();
                                FragmentTransaction transaction = manager.beginTransaction();
                                transaction.replace(R.id.container_content_main,new SaleProduct());
                                transaction.addToBackStack(null);
                                transaction.commit();
                            }
                        },null);
                        queue.add(request);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        return view;
    }
}