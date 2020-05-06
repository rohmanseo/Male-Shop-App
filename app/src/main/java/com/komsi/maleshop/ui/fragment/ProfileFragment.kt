package com.komsi.maleshop.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.androidnetworking.interfaces.StringRequestListener
import com.komsi.maleshop.R
import com.komsi.maleshop.helper.ToolbarHelper
import com.komsi.maleshop.repository.local.Credential
import com.komsi.maleshop.ui.activity.LoginActivity
import com.komsi.maleshop.utils.ConstApi
import com.komsi.maleshop.utils.bindTextViewWithEditText
import com.komsi.maleshop.utils.visible
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.fragment_profile.*
import org.json.JSONObject

class ProfileFragment : Fragment(), View.OnClickListener {

    var edtMode = false
    private lateinit var tvName: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvaddress: TextView
    private lateinit var tvTelp: TextView
    private lateinit var edtName: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtAddress: EditText
    private lateinit var edtTelp: EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onResume() {
        ToolbarHelper.setTitle("Profile")
        super.onResume()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_logout.setOnClickListener(this)
        btnEdit.setOnClickListener(this)

        tvName = view.findViewById(R.id.tv_name) as TextView
        tvEmail = view.findViewById(R.id.tv_email) as TextView
        tvaddress = view.findViewById(R.id.tv_address) as TextView
        tvTelp = view.findViewById(R.id.tv_telp) as TextView

        edtName = view.findViewById(R.id.edt_name) as EditText
        edtEmail = view.findViewById(R.id.edt_email) as EditText
        edtAddress = view.findViewById(R.id.edt_address) as EditText
        edtTelp = view.findViewById(R.id.edt_telp) as EditText

        fetchProfileData()
    }

    private fun updateProfile(nama:String,noTelp:String,alamat:String,foto:String){

        //            'nama' => 'required|min:6',
        //            'no_telp'=>'required',
        //            'alamat'=>'required',
        //            'foto'=>'required',

        AndroidNetworking.initialize(requireContext())
        AndroidNetworking.put(ConstApi.UPDATE_PROFILE.value)
                .addHeaders("Authorization", "Bearer ${Credential.getToken(requireContext())}")
                .setTag("update profile")
                .addUrlEncodeFormBodyParameter("nama",nama)
                .addUrlEncodeFormBodyParameter("no_telp",noTelp)
                .addUrlEncodeFormBodyParameter("alamat",alamat)
                .addUrlEncodeFormBodyParameter("foto",foto)
                .setPriority(Priority.HIGH)
                .build()
                .getAsString(object : StringRequestListener{
                    override fun onResponse(response: String?) {
                        Log.d("profileResponse", response.toString())
                        fetchProfileData()
                    }

                    override fun onError(anError: ANError?) {
                        Log.d("profileResponse", anError.toString())
                    }
                })
    }

    private fun fetchProfileData() {
        AndroidNetworking.initialize(requireContext())
        AndroidNetworking.post(ConstApi.PROFILE.value)
                .addHeaders("Authorization", "Bearer ${Credential.getToken(requireContext())}")
                .setTag("profile")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        Log.d("profileResponse", response.toString())
                        val nama = response.getString("nama")
                        val email = response.getString("email")
                        val alamat = response.getString("alamat")
                        val noTelp = response.getString("no_telp")

                        tvName.bindTextViewWithEditText(edtName,nama)
                        tvaddress.bindTextViewWithEditText(edtAddress,alamat)
                        tvEmail.bindTextViewWithEditText(edtEmail,email)
                        tvTelp.bindTextViewWithEditText(edtTelp,noTelp)

                    }

                    override fun onError(anError: ANError?) {
                        Log.d("profileResponse", anError.toString())
                    }
                })
    }

    override fun onClick(p0: View) {
        val id = p0.id
        if (id == R.id.btn_logout) {
            Credential.deleteToken(requireContext())
            startActivity(Intent(requireContext(), LoginActivity::class.java))
            requireActivity().finish()
        } else if (id == R.id.btnEdit) {
            editMode(edtMode)
            if (edtMode) {
                btnEdit.text = "Edit"
                updateProfile(edtName.text.toString(),edtTelp.text.toString(),edtAddress.text.toString(),"iniasal")
            } else {
                btnEdit.text = "Save"
            }
            edtMode = !edtMode
        }
    }

    private fun editMode(state: Boolean) {
        tvName.visible(state)
        tvaddress.visible(state)
        tvTelp.visible(state)
        tvEmail.visible(state)

        edtAddress.visible(!state)
        edtName.visible(!state)
        edtTelp.visible(!state)
        edtEmail.visible(!state)
    }
}