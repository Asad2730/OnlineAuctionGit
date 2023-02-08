package com.example.onlineauctionsystem;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import java.time.LocalDateTime;

public class MainActivity extends AppCompatActivity {

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle toggle;
    public NavigationView navView;
    public Toolbar toolbar;
    private RequestQueue queue;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queue = Volley.newRequestQueue(this);
        queue.start();
        drawerLayout = findViewById(R.id.my_drawer_layout);
        navView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        loadFragment(new CategoryFragment());
        sellProduct();

       navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

               int id = item.getItemId();
               if(id == R.id.sale_product){
                   loadFragment(new CategoryFragment());
                }
               else if(id == R.id.wallet){
                 loadFragment(new WalletProduct());
                }
               else if(id == R.id.category){
                   loadFragment(new AddCategoryFragment());
               }
               else if(id == R.id.favourite){
                   startActivity(new Intent(getApplicationContext(),FavouriteActivity.class));
               } else if (id == R.id.myProducts) {
                   loadFragment(new MyProductFragment());

               }else if(id == R.id.history){
                   loadFragment(new HistoryFragment());
               }
            else if(id == R.id.notification){
               loadFragment(new NotificationFragment());
             }else if(id == R.id.wish){
                   loadFragment(new AddWishListFragment());
               }
               else{
                 startActivity(new Intent(getApplicationContext(),CreateProductListing.class));
              }

               drawerLayout.closeDrawer(GravityCompat.START);
               return true;
           }
        });

    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }



    private void loadFragment(Fragment fragment){

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.replace(R.id.container_content_main,fragment);
        //transaction.add(R.id.container_content_main,fragment);
        transaction.commit();
    }


   @RequiresApi(api = Build.VERSION_CODES.O)
   private void sellProduct(){
       final LocalDateTime nw = LocalDateTime.now();
       int year = nw.getYear();
       int month = nw.getMonthValue();
       int day = nw.getDayOfMonth();
       String date = day+"/"+month+"/"+year;
       Log.e("DATE::",date);
        String url = Helper.ip+"sellProduct?date="+date;
       StringRequest request = new StringRequest(Request.Method.GET, url,
               new Response.Listener<String>() {
           @Override
           public void onResponse(String response) {
               Log.e("RESPONSE",response);
           }
       },null);

       queue.add(request);
   }

}