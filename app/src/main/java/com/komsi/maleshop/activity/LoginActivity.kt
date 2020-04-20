package com.komsi.maleshop.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.komsi.maleshop.R
import com.komsi.maleshop.constant.ConstApi
import com.komsi.maleshop.constant.Credential
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val api = Credential.getToken(this)

        if(!api.equals("")){
            startActivity(Intent(this@LoginActivity,MainActivity::class.java))
            finish()
        }

        tv_daftar.setOnClickListener { startActivity(Intent(this@LoginActivity, SignUpActivity::class.java)) }
        btn_login.setOnClickListener {
            login(edt_email.text.toString(),edt_password.text.toString())
        }
    }

    fun login(email: String,password:String) {
        val params = JSONObject()
        params.put("email",email)
        params.put("password",password)

        val jsonObjectRequest = object : JsonObjectRequest(Request.Method.POST,
                ConstApi.LOGIN.value,
                params,
                Response.Listener { response ->
                    Log.d("LoginResponse",response.getString("access_token"))
                    Credential.setToken(this@LoginActivity,response.getString("access_token").toString())
                    startActivity(Intent(this@LoginActivity,MainActivity::class.java))
                }, Response.ErrorListener { error ->
            Log.d("LoginResponse",error.toString())


        }
        ){
            @Throws(AuthFailureError::class)
            override fun getHeaders(): MutableMap<String, String> {
                return super.getHeaders()
            }
        }

        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(jsonObjectRequest)
    }
}