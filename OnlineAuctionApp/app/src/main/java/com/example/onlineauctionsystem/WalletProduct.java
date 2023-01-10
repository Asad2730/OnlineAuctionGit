package com.example.onlineauctionsystem;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class WalletProduct extends Fragment {

    private RequestQueue queue;
    private Button send;
    private EditText balance;
    private TextView pkr;

    public WalletProduct() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


         View view = inflater.inflate(R.layout.fragment_wallet_product, container, false);

         balance = view.findViewById(R.id.balance);
         pkr = view.findViewById(R.id.pkr);
         send = view.findViewById(R.id.send);

        try{
            getPkr();
        }catch (Exception ex){
//            Toast.makeText(requireContext(), "EX:"+ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

         queue = Volley.newRequestQueue(requireContext());
         queue.start();

         send.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 try {
                     add();
                 } catch (JSONException e) {
                     e.printStackTrace();
                 }
             }
         });
        return view;
    }


    private void getPkr(){

        String url = Helper.ip+"getPkr?uid="+Helper.uid;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.length() != 0){
                        double ttl = response.getDouble("balance");
                        pkr.setText("PKR " + ttl);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse: "+error.getMessage() );

            }
        });

        queue.add(request);
    }


    private void add() throws JSONException {

        String url = Helper.ip+"insertWallet";
        JSONObject obj = new JSONObject();
        obj.put("uid",Helper.uid);
        obj.put("balance",Double.parseDouble(balance.getText().toString()));

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, obj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
               getPkr();
            }
        },null);

        queue.add(request);
    }
}