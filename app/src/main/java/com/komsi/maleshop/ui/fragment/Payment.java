package com.komsi.maleshop.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.komsi.maleshop.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Payment extends Fragment {

   View view;
   Button btnbacktohome;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_payment,container,false);

        btnbacktohome= view.findViewById(R.id.btn_back_home);

        btnbacktohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), HomeFragment.class);
                view.getContext().startActivity(intent);
                getActivity().finish();
            }
        });
        return view;



    }

}
