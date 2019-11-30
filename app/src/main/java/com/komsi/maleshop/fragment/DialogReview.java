package com.komsi.maleshop.fragment;


import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.komsi.maleshop.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogReview extends DialogFragment {


    public DialogReview() {
        // Required empty public constructor
    }

    View view;
    Button btnCancel;
    Button btnSend;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dialog_review, container, false);
        btnCancel = view.findViewById(R.id.btn_cancel_review);
        btnSend = view.findViewById(R.id.btn_send_review);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogReview.this.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogReview.this.dismiss();
            }
        });

        return view;
    }

}
