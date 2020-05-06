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
import com.komsi.maleshop.repository.local.Credential
import com.komsi.maleshop.ui.fragment.DialogFragmentLoading
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val api = Credential.getToken(this)

        if(api != ""){
            startActivity(Intent(this@LoginActivity,MainActivity::class.java))
            finish()
        }

        tv_daftar.setOnClickListener { startActivity(Intent(this@LoginActivity, SignUpActivity::class.java)) }
        btn_login.setOnClickListener {
            login(edt_email.text.toString(),edt_password.text.toString())
        }
    }

    private fun login(email: String, password:String) {
        AndroidNetworking.initialize(this)
        val dialog = DialogFragmentLoading()
        dialog.show(supportFragmentManager,"Dialog Loading")
        AndroidNetworking.post(ConstApi.LOGIN.value)
                .addBodyParameter("email",email)
                .addBodyParameter("password",password)
                .setTag("login")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener{
                    override fun onResponse(response: JSONObject) {
                        val token = response.getString("access_token").toString()
                        Credential.setToken(this@LoginActivity,token)
                        startActivity(Intent(this@LoginActivity,MainActivity::class.java))
                        dialog.dismiss()
                        finish()
                    }

                    override fun onError(anError: ANError?) {
                        Toast.makeText(this@LoginActivity,"Error ${anError.toString()}",Toast.LENGTH_SHORT).show()
                        Log.d("Loginresponse", anError.toString())
                        dialog.dismiss()
                    }
                })
    }
}