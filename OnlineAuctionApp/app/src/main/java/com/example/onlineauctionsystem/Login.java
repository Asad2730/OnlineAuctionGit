package com.example.onlineauctionsystem;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    private Button signIn,register;
    private  Intent intent;
    private EditText email,password;
    private RequestQueue que;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signIn = findViewById(R.id.signIn);
        register = findViewById(R.id.register);
        
        email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);

        que = Volley.newRequestQueue(this);
        que.start();


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    logIn();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(),SignUp.class);
                startActivity(intent);
            }
        });
    }

    private void logIn() throws JSONException {

        JSONObject ob = new JSONObject();
        ob.put("email",email.getText().toString());
        ob.put("password",password.getText().toString());

        String url = Helper.ip+"login";

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, ob, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Toast.makeText(getApplicationContext(), "Login success!", Toast.LENGTH_SHORT).show();
                    int id  = response.getInt("Id");
                    Helper.uid = id;
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                VolleyLog.d(TAG, "Error:" + error.getMessage());
//                Log.d(TAG, "onErrorResponse: "+error.getMessage());
//                Log.d(TAG, "Cause: "+error.getCause());
//                Log.d(TAG, "Cause: "+error.getLocalizedMessage());
//                Log.d(TAG, "Cause: "+error.networkResponse.data);
//                Log.d(TAG, "Cause: "+error.networkResponse.headers);
//                Log.d(TAG, "Cause: "+error.networkResponse.allHeaders);
//                Log.d(TAG, "Cause: "+error.networkResponse.statusCode);

            }
        });

        que.add(req);
    }
}