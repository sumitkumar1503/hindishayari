package com.sumit.hindishayari;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class DeveloperSumit extends AppCompatActivity {


    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_sumit);



        //for navigationnnnn
        setUpToolbar();
        navigationView =  findViewById(R.id.nav1);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        Intent home= new Intent(getBaseContext(),MainActivity.class);
                        startActivity(home);
                        break;
                    case R.id.nav_share:
                        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        String shareBody = "Hi I have downloaded Hindi Shayari app. \nYou should try this \n" +
                                " https://play.google.com/store/apps/details?id=com.sumit.hindishayari";
                        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Hindi Shayari");
                        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                        startActivity(Intent.createChooser(sharingIntent, "Share via"));
                        break;
                    case R.id.nav_rate:
                        try {
                            Uri uri = Uri.parse("market://details?id="+"com.sumit.hindishayari");
                            Intent goMarket = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(goMarket);
                        }catch (ActivityNotFoundException e){
                            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.sumit.hindishayari");
                            Intent goMarket = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(goMarket);
                        }
                        break;
                    case R.id.nav_exit:
                        finishAffinity();
                        System.exit(0);
                        break;
                }
                return false;
            }
        });


        //for bio text
        TextView bios=findViewById(R.id.bios);
        bios.setText("Hi, my name is Sumit Kumar and I\'m the man behind this app.\n" +
                "If you have any suggestions or feedback, feel free to message me. I will reply to everyone.\n" +
                "\n" +
                "Do drop me a Hi. It really means a lot to me.❤️ \n"+
                "Looking forward to hear from you.");

        //for facebook
        Button fb=findViewById(R.id.facebook);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    getApplicationContext().getPackageManager().getPackageInfo("com.facebook.katana", 0);
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/100004564054231")));
                } catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/sumit.luv")));
                }
            }
        });

        //for Instagram
        Button insta=findViewById(R.id.insta);
        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse("http://instagram.com/_u/sumit.luv");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/sumit.luv")));
                }
            }
        });

        //for Youtube
        Button yt=findViewById(R.id.youtube);
        yt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube.com/channel/" + "UCrLWsy9v5KNZU0t0kjQ3Kuw"));
                try {
                    startActivity(appIntent);
                } catch (ActivityNotFoundException ex) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.youtube.com/channel/UCrLWsy9v5KNZU0t0kjQ3Kuw")));

                }
            }
        });





    }

    //for navigation bar
    private void setUpToolbar(){
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer1);
        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }
}
