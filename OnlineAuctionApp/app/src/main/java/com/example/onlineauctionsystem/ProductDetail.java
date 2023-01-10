package com.example.onlineauctionsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ProductDetail extends AppCompatActivity {

    private TextView name,price,description;
    private Button make;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        name = findViewById(R.id.name);
        price = findViewById(R.id.price);
        description = findViewById(R.id.description);
        make = findViewById(R.id.offer);
        img = findViewById(R.id.img);


        name.setText(Helper.selected_product.name);
        description.setText(Helper.selected_product.description);
        price.setText("Rs "+Helper.selected_product.price);
        Picasso.get().load(Helper.imgUrl+Helper.selected_product.img).into(img);

        make.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),MakeOffer.class);
                startActivity(intent);
            }
        });

    }
}