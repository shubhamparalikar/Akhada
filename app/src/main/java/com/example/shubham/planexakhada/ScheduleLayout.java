package com.example.shubham.planexakhada;

import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;


/*  this activity has the schedule for the weekdays. the classes
which are connected to this activity are:
Dbmain, RecyclerDataExercise, RecyclerAdapterExercise, InputDialog (dialogfragment)
RemoveClickListener (interface),
 */
public class ScheduleLayout extends AppCompatActivity implements RemoveClickListener, InputDialog.EditNameDialogListener {
    static Dbmain db;
    TextView tvadd;
    static TextView selectedday;
    private RecyclerView mRecyclerView;
    Cursor cr;          //this cursor is to fill the recycler view when the cursor is returned by the database
    private static RecyclerAdapterExercise mRecyclerAdapter;

    //this is used to fill the object of RecyclerDataExercise and then passing it to RecyclerAdapterExercise
    static ArrayList<RecyclerDataExercise> myList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setting  enter animation
        Fade s = new Fade();
        s.setDuration(300);
        getWindow().setEnterTransition(s);

        setContentView(R.layout.activity_schedule_layout);
        //setting uo actionbar with background texture and title
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        db = new Dbmain(this);
        tvadd = (TextView) findViewById(R.id.tvadd);
        selectedday = (TextView) findViewById(R.id.selectedday);
        mRecyclerView = (RecyclerView) findViewById(R.id.exrowrecycler);
        mRecyclerAdapter = new RecyclerAdapterExercise(myList, ScheduleLayout.this, this);
        //setting grid layout with 2 elements in a row for recyclerview
        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mRecyclerAdapter);
        myList.clear(); //clearing the mylist so that there is no exercises seen in recyclerview.
        mRecyclerAdapter.notifyData(myList); //notifying the adapter that data has been changed(in this case blank)
        //creating admob reference, requesting ads on it and implemented callbacks for admob
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3020470730447296~1995942962");
        AdView mAdView = (AdView) findViewById(R.id.adViewschedule);
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

    /*these are 7 methods that gets called when the cards having names of weekdays are clicked
    each method issetting the current selected day textview's text
    then it is retrieving cursor from DbMain objects for the particular day then
    checking if the cursor is null or not(database is having rows or not/ exercise are present in the table or not)
    if not null then it is calling fillData to fill the desired data from the database.
    */
    public void monday(View view) {
        selectedday.setText("monday");
        Log.d("CHECKDB", "monday: ");
        cr = db.getData(" monday");
        if (cr == null) {
            myList.clear();
            mRecyclerAdapter.notifyData(myList);
        } else {
            myList.clear();
            mRecyclerAdapter.notifyData(myList);
            fillData(cr);
        }

    }

    public void tuesday(View view) {
        selectedday.setText("tuesday");
        Log.d("CHECKDB", "tuesday: ");
        cr = db.getData(" tuesday");
        if (cr == null) {
            myList.clear();
            mRecyclerAdapter.notifyData(myList);
        } else {
            myList.clear();
            mRecyclerAdapter.notifyData(myList);
            fillData(cr);
        }
    }

    public void wednesday(View view) {
        selectedday.setText("wednesday");
        Log.d("CHECKDB", "wednesday: ");
        cr = db.getData(" wednesday");
        if (cr == null) {
            myList.clear();
            mRecyclerAdapter.notifyData(myList);
        } else {
            myList.clear();
            mRecyclerAdapter.notifyData(myList);
            fillData(cr);
        }
    }

    public void thursday(View view) {
        selectedday.setText("thursday");
        Log.d("CHECKDB", "thursday: ");
        cr = db.getData(" thursday");
        Log.d("CHECKDB", "thu: cursor value=" + cr);
        if (cr == null) {
            myList.clear();
            mRecyclerAdapter.notifyData(myList);
        } else {
            myList.clear();
            mRecyclerAdapter.notifyData(myList);
            fillData(cr);
        }
    }

    public void friday(View view) {
        selectedday.setText("friday");
        Log.d("CHECKDB", "friday: ");
        cr = db.getData(" friday");
        if (cr == null) {
            myList.clear();
            mRecyclerAdapter.notifyData(myList);
        } else {
            myList.clear();
            mRecyclerAdapter.notifyData(myList);
            fillData(cr);
        }
    }

    public void saturday(View view) {
        selectedday.setText("saturday");
        Log.d("CHECKDB", "saturday: ");
        cr = db.getData(" saturday");
        if (cr == null) {
            myList.clear();
            mRecyclerAdapter.notifyData(myList);
        } else {
            myList.clear();
            mRecyclerAdapter.notifyData(myList);
            fillData(cr);
        }
    }

    public void sunday(View view) {
        selectedday.setText("sunday");
        Log.d("CHECKDB", "sunday: ");
        cr = db.getData(" sunday");
        if (cr == null) {
            myList.clear();
            mRecyclerAdapter.notifyData(myList);
        } else {
            myList.clear();
            mRecyclerAdapter.notifyData(myList);
            fillData(cr);
        }
    }

    //filling data into the recycler view from the cursor
    public void fillData(Cursor cr) {
        Log.d("CHCKDB", "fillingData: ");
        cr.moveToPosition(0);
        do {
            //coloumn indexes of names, sets, reps and weights.
            int ind1 = cr.getColumnIndex("name");
            int ind2 = cr.getColumnIndex("sets");
            int ind3 = cr.getColumnIndex("reps");
            int ind4 = cr.getColumnIndex("weights");

            //getting strings from the indexes obtained above.
            String name = cr.getString(ind1);
            String sets = cr.getString(ind2);
            String reps = cr.getString(ind3);
            String weights = cr.getString(ind4);

            //setting data into the object and passing it to RecyclerAdapterExercise
            RecyclerDataExercise mlog = new RecyclerDataExercise();
            mlog.setTitle(name);
            mlog.setSets(sets);
            mlog.setReps(reps);
            mlog.setWeights(weights);
            myList.add(mlog);
            mRecyclerAdapter.notifyData(myList);
        } while (cr.moveToNext());
        cr.close();
    }


    //showing input dialog to get exercise details from the user
    public void showDialog(View view) {
        //if ny day is not selected then prompting the user to select day first.
        if (selectedday.getText().toString().length() == 0) {
            Toast.makeText(this, "Select the day first..", Toast.LENGTH_SHORT).show();

        } else {
            //creating the dialog and getting input
            FragmentManager fm = getSupportFragmentManager();
            InputDialog ip = new InputDialog();
            ip.show(fm, "sample");
        }
    }


    //this is the interface abstract method to remove the row of recyclerview when deleted.
    @Override
    public void OnRemoveClick(int index) {
        myList.remove(index);
        mRecyclerAdapter.notifyData(myList);

    }


    //when delete button is click on recyclerview row, an alert dialog is  shown, this is the callback for that dialog box.
    // declaration of this method is in RecyclerAdapterExercise class.
    @Override
    public void onFinishEditDialog(String exname, String exsets, String exreps, String exweights) {


        db.add(selectedday.getText().toString(), exname, exsets, exreps, exweights);
        Log.d("CHECKDB", "onFinishEditDialog:" + selectedday.getText().toString() + exname + exsets + exreps + exweights);
        db.close();
        RecyclerDataExercise mlog = new RecyclerDataExercise();
        mlog.setTitle(exname);
        mlog.setSets(exsets);
        mlog.setReps(exreps);
        mlog.setWeights(exweights);
        myList.add(mlog);
        mRecyclerAdapter.notifyData(myList);


    }

    public static void delete(String exname) {

        db.delete(selectedday.getText().toString(), exname);
        Log.d("CHECKDB", "delete: " + selectedday.getText().toString() + "    " + exname);
        db.close();

    }


}
