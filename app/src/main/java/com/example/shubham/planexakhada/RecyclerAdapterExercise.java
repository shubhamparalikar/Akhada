package com.example.shubham.planexakhada;

import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by shubham on 12/18/2016.
 */

public class RecyclerAdapterExercise extends RecyclerView.Adapter<RecyclerAdapterExercise.RecyclerItemViewHolder> {
    ImageButton delete;
    private ArrayList<RecyclerDataExercise> myList;
    int mLastPosition = 0;
    private RemoveClickListener mListner;
    Context ct;

    public RecyclerAdapterExercise(ArrayList<RecyclerDataExercise> myList, RemoveClickListener listner, Context ct) {
        this.myList = myList;
        mListner = listner;
        this.ct = ct;

    }


    @Override
    public RecyclerItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exrows, parent, false);
        RecyclerItemViewHolder holder = new RecyclerItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerItemViewHolder holder, int position) {
        RecyclerDataExercise data = myList.get(position);
        holder.title.setText(data.getTitle());
        holder.tvsets.setText(data.getSets());
        holder.tvreps.setText(data.getReps());
        holder.tvweight.setText(data.getWeights());
        mLastPosition = position;
    }

    @Override
    public int getItemCount() {
        return (null != myList ? myList.size() : 0);
    }

    public void notifyData(ArrayList<RecyclerDataExercise> myList) {
        Log.d("CHCECKDB ", "notifydata" + myList.size() + "");
        this.myList = myList;
        notifyDataSetChanged();
    }

    public class RecyclerItemViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout mainLayout;
        ImageButton delete, edit;
        private TextView title, tvsets, tvreps, tvweight;


        public RecyclerItemViewHolder(final View parent) {
            super(parent);
            title = (TextView) parent.findViewById(R.id.cardtitle);
            tvsets = (TextView) parent.findViewById(R.id.setsvalue);
            tvreps = (TextView) parent.findViewById(R.id.repsvalue);
            tvweight = (TextView) parent.findViewById(R.id.weightsvalue);
            delete = (ImageButton) parent.findViewById(R.id.deleteimage);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Log.d("CHECKDB", "onClick: " + title.getText().toString());
                    final String deletetext = title.getText().toString();
                    new AlertDialog.Builder(ct)
                            .setTitle(Html.fromHtml("<font color='#39cedf'>ALERT!! </font>"))
                            .setMessage(Html.fromHtml("<font color='#39cedf'>ARE YOU SURE YOU WANT TO DELETE </font>")
                            ).setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            ScheduleLayout.delete(deletetext);
                            mListner.OnRemoveClick(getAdapterPosition());
                            Log.d("CHECKDB", "YES CLICKED: " + title.getText().toString());

                        }
                    }).setNegativeButton("NO", null).show();


                }
            });


        }
    }


}
