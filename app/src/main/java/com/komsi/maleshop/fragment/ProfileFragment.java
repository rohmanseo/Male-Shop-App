package com.komsi.maleshop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.komsi.maleshop.R;
import com.komsi.maleshop.activity.LoginActivity;

public class ProfileFragment extends Fragment {
    View view;
    Button btnLogout;
    ImageView btnEdtName;
    TextView tvName;
    EditText edtName;
    ImageView btnEdtSuccess;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile,container,false);
        btnLogout = view.findViewById(R.id.btn_logout);
        btnEdtName = view.findViewById(R.id.btn_edit_name);
        tvName = view.findViewById(R.id.tv_name);
        edtName = view.findViewById(R.id.edt_name);
        btnEdtSuccess = view.findViewById(R.id.btn_edit_name_success);


        btnEdtName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvName.setVisibility(View.GONE);
                edtName.setVisibility(View.VISIBLE);
                btnEdtName.setVisibility(View.GONE);
                btnEdtSuccess.setVisibility(View.VISIBLE);
            }
        });
        btnEdtSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvName.setVisibility(View.VISIBLE);
                btnEdtName.setVisibility(View.VISIBLE);
                btnEdtSuccess.setVisibility(View.GONE);
                edtName.setVisibility(View.GONE);
            }
        });


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), LoginActivity.class));
            }
        });

        return view;
    }
}
