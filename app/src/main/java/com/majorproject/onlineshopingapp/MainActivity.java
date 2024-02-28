package com.majorproject.onlineshopingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
//    public static final String EMAIL="Email_key";
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView icon;
    TextView username,uemail;
    DBHelper db;
    ActionBarDrawerToggle toggle;
    FrameLayout frame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frame=(FrameLayout) findViewById(R.id.framelayout);

        toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout=(DrawerLayout) findViewById(R.id.drawerlayout);
        navigationView=(NavigationView) findViewById(R.id.navigationView);

        View headerview =navigationView.getHeaderView(0);

        icon=(ImageView) headerview.findViewById(R.id.userIcon);
        username=(TextView) headerview.findViewById(R.id.userN);
        uemail=(TextView) headerview.findViewById(R.id.userE);
        db=new DBHelper(getApplicationContext());
//        Intent intent =getIntent();
//        String emailId= intent.getStringExtra(Login_page.EMAIL);

        SharedPreferences sharedPreferences=getSharedPreferences("MySharedpre", Context.MODE_PRIVATE);
        if(!sharedPreferences.getBoolean("isLogedIn",false)){
            Intent intent=new Intent(getApplicationContext(),Login_page.class);
            startActivity(intent);
            finish();
        }

        String emailId= sharedPreferences.getString("emailId_key","guest@gmail.com");
        String uname=db.getName(emailId);
        int uicon=db.getIcon(emailId);
        icon.setImageResource(uicon);
        username.setText(uname);
        uemail.setText(emailId);



        toggle= new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);

//        toggle.setDrawerIndicatorEnabled(false);
//        toggle.setHomeAsUpIndicator(R.drawable.cutom_toggle);

//        ColorFilter white = new PorterDuffColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
//        Drawable drawable = getResources().getDrawable(R.drawable.baseline_menu_24); // Use the default hamburger icon
//        drawable.setColorFilter(white);
//        toggle.setHomeAsUpIndicator(drawable);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();



        toolbar.setTitle("Home");
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new Home_fragment()).commit();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
               if(id==R.id.nav_home) {
                   toolbar.setTitle("Home");
                   getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new Home_fragment()).commit();
                   Toast.makeText(getApplicationContext(), "Home page", Toast.LENGTH_SHORT).show();
               }else if(id==R.id.nav_profile) {
                   toolbar.setTitle("Profile");
                   getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new Profile_fragment()).commit();
                   Toast.makeText(getApplicationContext(), "Profile page", Toast.LENGTH_SHORT).show();
               }
               else if(id==R.id.nav_cart) {
                   toolbar.setTitle("Cart");
                   getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new Cart_fragment()).commit();
                   Toast.makeText(getApplicationContext(), "Cart page", Toast.LENGTH_SHORT).show();
               }
               else if(id==R.id.nav_signout) {
                   Intent intent=new Intent(getApplicationContext(),Login_page.class);
                   SharedPreferences.Editor editor=sharedPreferences.edit();
                   editor.clear();
                   editor.apply();
                   startActivity(intent);
                   finish();
                   Toast.makeText(getApplicationContext(), "Sign Out", Toast.LENGTH_SHORT).show();
               }else{
                        Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_SHORT).show();
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }
}