package com.example.shubham.planexakhada;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by shubham on 12/17/2016.
 */

public class InputDialog extends DialogFragment {

    EditText name, sets, reps, weights;
    Button addbuton, cancel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.input_dialog, container, false);

        name = (EditText) rootView.findViewById(R.id.edexercisename);
        sets = (EditText) rootView.findViewById(R.id.edsets);
        reps = (EditText) rootView.findViewById(R.id.edreps);
        weights = (EditText) rootView.findViewById(R.id.edweight);
        addbuton = (Button) rootView.findViewById(R.id.addtoschedule);
        cancel = (Button) rootView.findViewById(R.id.cancel);
        addbuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String exname = name.getText().toString();
                String exsets = sets.getText().toString();
                String exreps = reps.getText().toString();
                String exweights = weights.getText().toString();

                if (exname.length()==0) {
                    name.setHint("THIS FEILD IS COMPULSORY");
                } else {
                    EditNameDialogListener act = (EditNameDialogListener) getActivity();
                    act.onFinishEditDialog(exname, exsets, exreps, exweights);

                    dismiss();

                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


        return rootView;
    }


    public interface EditNameDialogListener {
        void onFinishEditDialog(String exname, String exsets, String exreps, String exweights);
    }


}
