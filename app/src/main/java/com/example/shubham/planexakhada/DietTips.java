package com.example.shubham.planexakhada;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.ramotion.foldingcell.FoldingCell;

public class DietTips extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setting  enter animation
        Fade s = new Fade();
        s.setDuration(300);
        getWindow().setEnterTransition(s);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_diet_tips);



        // get our folding cell
        final FoldingCell fc = (FoldingCell) findViewById(R.id.folding_cell);
        fc.initialize(1000, Color.DKGRAY, 5);
        fc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fc.toggle(false);
            }
        });
        final FoldingCell fc2 = (FoldingCell) findViewById(R.id.folding_cell2);
        fc2.initialize(1000, Color.DKGRAY, 6);
        fc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fc2.toggle(false);
            }
        });
        final FoldingCell fc3 = (FoldingCell) findViewById(R.id.folding_cell3);
        fc3.initialize(1000, Color.DKGRAY, 5);
        fc3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fc3.toggle(false);
            }
        });
        final FoldingCell fc4 = (FoldingCell) findViewById(R.id.folding_cell4);
        fc4.initialize(1000, Color.DKGRAY, 5);
        fc4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fc4.toggle(false);
            }
        });
        final FoldingCell fc5 = (FoldingCell) findViewById(R.id.folding_cell5);
        fc5.initialize(1000, Color.DKGRAY, 5);
        fc5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fc5.toggle(false);
            }
        });
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3020470730447296~1995942962");
        AdView mAdView = (AdView) findViewById(R.id.adViewdiet);
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


    }

}
