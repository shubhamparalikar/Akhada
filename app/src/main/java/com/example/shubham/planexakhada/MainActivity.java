package com.example.shubham.planexakhada;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.WindowCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.*;
import java.util.jar.Manifest;

import static android.support.v7.appcompat.R.styleable.ActionBar;

public class MainActivity extends AppCompatActivity {
    Toolbar ab;
    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(WindowCompat.FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.activity_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //setting uo actionbar with background texture and title
        ab = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(ab);
        BitmapDrawable background = new BitmapDrawable(BitmapFactory.decodeResource(getResources(), R.drawable.patternblue));
        ab.setBackgroundDrawable(background);
        getSupportActionBar().setTitle("Home");

        //creating admob reference, requesting ads on it and implemented callbacks for admob
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3020470730447296~1995942962");
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest request = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)        // All emulators
                .addTestDevice("672F01F0BF7318A65431A9AE4E7CB593")  // My  test phone
                .build();
        mAdView.loadAd(request);


        //Setting adListeners callback methods
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                Log.d("adshere", "onAdLoaded: ");
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                Log.d("adshere", "onAdClosed: ");
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                Log.d("adshere", "onAdFailedToLoad: " + i);
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
                Log.d("adshere", "onAdLeftApplication: ");
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
                Log.d("adshere", "onAdOpened: ");
            }
        });


        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);           //fab menu button

        floatingActionButton2 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item2);       //fab menu item 2
        floatingActionButton3 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item3);       //fab menu item 3

        //setting background color to transparent of menu items

        floatingActionButton2.setColorNormal(getResources().getColor(R.color.fabmenu));
        floatingActionButton3.setColorNormal(getResources().getColor(R.color.fabmenu));

        final RelativeLayout obstrucuterView = (RelativeLayout) findViewById(R.id.obstructor);          //this view is for dimming the background

        //if dimmed background is clicked then close the fab menu item buttons.
        obstrucuterView.setOnTouchListener(new View.OnTouchListener() {                                 //when fab menu is clicked.
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (obstrucuterView.getVisibility() == View.VISIBLE) {
                    materialDesignFAM.close(true);
                    return true;
                }
                return false;
            }
        });

        //if menu item is clicked then show the dimmed background relative layout so that background appears dark and vice-versa
        materialDesignFAM.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean opened) {
                if (opened) {
                    if (obstrucuterView.getVisibility() == View.INVISIBLE)
                        obstrucuterView.setVisibility(View.VISIBLE);
                }
                if (!opened) {
                    if (obstrucuterView.getVisibility() == View.VISIBLE)
                        obstrucuterView.setVisibility(View.INVISIBLE);
                }
            }
        });



        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu second item clicked
                Uri uri = Uri.parse("market://details?id=" + MainActivity.this.getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                // To count with Play market backstack, After pressing back button,
                // to taken back to our application, we need to add following flags to intent.
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + MainActivity.this.getPackageName())));
                }

            }
        });
        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu third item clicked
                Intent i=new Intent(MainActivity.this,Info.class);
                startActivity(i);

            }
        });


    }


    //this method is callled when the card containing the your schedule title it clicked and it opens the ScheduleLayout Activity
    public void openSchedule(View v) {
        ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, null);


        startActivity(new Intent(MainActivity.this, ScheduleLayout.class), compat.toBundle());

    }

    //called when diet tips card is clicked and opens the DietTips Activity.
    public void openDietTips(View view) {
        Intent i = new Intent(this, DietTips.class);
        startActivity(i);
    }

    //called when Cardio tips card is clicked and opens the Cardiotips Activity.
    public void openCardio(View view) {
        Intent i = new Intent(this, CardioTips.class);
        startActivity(i);
    }

    //called when dosdonts tips card is clicked and opens the DosDonts Activity.
    public void openDosDonts(View view) {
        Intent i = new Intent(this, DosDonts.class);
        startActivity(i);
    }
}


