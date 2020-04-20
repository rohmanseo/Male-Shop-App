package com.komsi.maleshop.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.komsi.maleshop.R
import com.komsi.maleshop.activity.LoginActivity
import com.komsi.maleshop.constant.Credential
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
//        btnLogout = view.findViewById(R.id.btn_logout)
//        btnEdtName = view.findViewById(R.id.btn_edit_name)
//        tvName = view.findViewById(R.id.tv_name)
//        edtName = view.findViewById(R.id.edt_name)
//        btnEdtSuccess = view.findViewById(R.id.btn_edit_name_success)
//        btnEdtName.setOnClickListener(View.OnClickListener {
//            tvName.setVisibility(View.GONE)
//            edtName.setVisibility(View.VISIBLE)
//            btnEdtName.setVisibility(View.GONE)
//            btnEdtSuccess.setVisibility(View.VISIBLE)
//        })
//        btnEdtSuccess.setOnClickListener(View.OnClickListener {
//            tvName.setVisibility(View.VISIBLE)
//            btnEdtName.setVisibility(View.VISIBLE)
//            btnEdtSuccess.setVisibility(View.GONE)
//            edtName.setVisibility(View.GONE)
//        })
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_logout.setOnClickListener {

            Credential.deleteToken(requireContext())
            startActivity(Intent(view.context, LoginActivity::class.java))
        }
    }
}