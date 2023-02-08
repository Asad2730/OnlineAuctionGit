package com.example.onlineauctionsystem;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

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


public class AddWishListFragment extends Fragment {

   private EditText name;
   private Button add;

   private RequestQueue que;
    public AddWishListFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_add_wish_list, container, false);
        que = Volley.newRequestQueue(requireContext());
        que.start();
        name = v.findViewById(R.id.name);
        add = v.findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = Helper.ip+"addWish";
                JSONObject obj = new JSONObject();
                try {
                    obj.put("name",name.getText().toString());
                    JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, obj, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(requireContext(), "Added!", Toast.LENGTH_SHORT).show();
                            name.setText("");
                        }
                    },null);
                    que.add(req);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        return v;
    }
}