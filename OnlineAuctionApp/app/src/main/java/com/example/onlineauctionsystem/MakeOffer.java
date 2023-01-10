package com.example.onlineauctionsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class MakeOffer extends AppCompatActivity {

    private EditText offer ;
    private Button submit;
    private RequestQueue queue;
    private int pid,sellerId,buyerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_offer);
         pid = Helper.selected_product.id;
          sellerId = Helper.selected_product.uid;
         buyerId = Helper.uid;

        offer = findViewById(R.id.offer_price);
        submit = findViewById(R.id.submit);
        queue = Volley.newRequestQueue(this);
        queue.start();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    add();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }


    public void add() throws JSONException {
        String url = Helper.ip+"makeOffer";
        JSONObject obj = new JSONObject();
        obj.put("sid",sellerId);
        obj.put("bid",buyerId);
        obj.put("pid",pid);
        obj.put("price",Integer.parseInt(offer.getText().toString()));

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, obj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getApplicationContext(), "Request Send!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        },null);

        queue.add(request);
    }
}