package com.sumit.hindishayari;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    AlertDialog.Builder builder;

    private AdView mAdView;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //for navigationnnnnnnnnnnnnnnnnnnnn
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

                    case R.id.nav_dev:
                        Intent dev= new Intent(getBaseContext(),DeveloperSumit.class);
                        startActivity(dev);
                        break;
                    case R.id.nav_exit:
                        //for clearing all previous activity
                        finishAffinity();
                        System.exit(0);
                        break;
                }
                return false;
            }
        });

        //for love shayari
        ImageButton luvbtn= findViewById(R.id.lovebtn);
        luvbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }
                Intent luvintent= new Intent(getBaseContext(),LoveShayari.class);
                startActivity(luvintent);
            }
        });


        //for friendship shayari
        ImageButton friendshipbtn= findViewById(R.id.friendshipbtn);
        friendshipbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }
                Intent friendshipintent= new Intent(getBaseContext(),FriendshipShayari.class);
                startActivity(friendshipintent);
            }
        });


        //for Comedy shayari
        ImageButton comedybtn= findViewById(R.id.comedybtn);
        comedybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }
                Intent comedyintent= new Intent(getBaseContext(),ComedyShayari.class);
                startActivity(comedyintent);
            }
        });

        //for Bewafa shayari
        ImageButton bewafabtn= findViewById(R.id.bewafabtn);
        bewafabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }
                Intent bewafaintent= new Intent(getBaseContext(),BewafaShayari.class);
                startActivity(bewafaintent);
            }
        });

        //for sorry shayari
        ImageButton sorrybtn= findViewById(R.id.sorrybtn);
        sorrybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }
                Intent sorryintent= new Intent(getBaseContext(),SorryShayari.class);
                startActivity(sorryintent);
            }
        });

        //for Deshbhakti shayari
        ImageButton deshbhaktibtn= findViewById(R.id.deshbhaktibtn);
        deshbhaktibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }
                Intent deshbhaktiintent= new Intent(getBaseContext(),DeshbhaktiShayari.class);
                startActivity(deshbhaktiintent);
            }
        });


        // for banner ad
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        //for full screen ad
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.inter));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }

        });






    }


    //for opening tool bar or navigation bar
    private void setUpToolbar(){
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer1);
        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }


    //for onback pressed
    @Override
    public void onBackPressed()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Thank You For Using Hindi Shayari \uD83D\uDE0D \n \n Are you sure you want to exit ? \uD83D\uDE14")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finishAffinity();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
