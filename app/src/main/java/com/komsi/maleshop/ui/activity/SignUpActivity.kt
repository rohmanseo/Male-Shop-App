package com.komsi.maleshop.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.komsi.maleshop.R
import com.komsi.maleshop.utils.ConstApi
import com.komsi.maleshop.persistence.Credential
import com.komsi.maleshop.ui.fragment.DialogFragmentLoading
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.json.JSONObject

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val api = Credential.getToken(this)

        if(!api.equals("")){
            startActivity(Intent(this@SignUpActivity,MainActivity::class.java))
            finish()
        }

        btn_signup.setOnClickListener {
//            startActivity(Intent(this@SignUpActivity, MainActivity::class.java))
            register(edt_nama.text.toString(),edt_email.text.toString(),edt_password.text.toString())
//            finish()
        }
    }

    private fun register(nama: String, email:String, password:String) {
        AndroidNetworking.initialize(this)
        val dialog = DialogFragmentLoading()
        dialog.show(supportFragmentManager,"Dialog Loading")
        AndroidNetworking.post(ConstApi.REGISTER.value)
                .addBodyParameter("nama",nama)
                .addBodyParameter("no_telp","9786")
                .addBodyParameter("alamat","default")
                .addBodyParameter("foto","default")
                .addBodyParameter("email",email)
                .addBodyParameter("password",password)
                .setTag("Signup")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        Log.d("Signupresponse", response.toString())
                        val token = response.getString("access_token").toString()
                        Credential.setToken(this@SignUpActivity,token)
                        startActivity(Intent(this@SignUpActivity,MainActivity::class.java))
                        dialog.dismiss()
                        finish()
                    }

                    override fun onError(anError: ANError?) {
                        Toast.makeText(this@SignUpActivity,"Error ${anError.toString()}",Toast.LENGTH_SHORT).show()
                        Log.d("Signupresponse", anError.toString())
                        dialog.dismiss()
                    }
                })
    }

}