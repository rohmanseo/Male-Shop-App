package com.komsi.maleshop.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.komsi.maleshop.R
import com.komsi.maleshop.constant.ConstApi
import com.komsi.maleshop.constant.Credential
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

        Toast.makeText(this, ConstApi.REGISTER.value, Toast.LENGTH_LONG).show()
        btn_signup.setOnClickListener {
//            startActivity(Intent(this@SignUpActivity, MainActivity::class.java))
            register(edt_nama.text.toString(),edt_email.text.toString(),edt_password.text.toString())
//            finish()
        }
    }

    fun register(nama: String,email:String,password:String) {
        val params = JSONObject()
        params.put("nama",nama)
        params.put("no_telp","9786")
        params.put("alamat","default")
        params.put("foto","default")
        params.put("email",email)
        params.put("password",password)

        val jsonObjectRequest = object : JsonObjectRequest(Request.Method.POST,
                ConstApi.REGISTER.value,
                params,
                Response.Listener { response ->
                    Log.d("LoginResponse",response.toString())
                    Credential.setToken(this@SignUpActivity,response.getString("access_token").toString())
                    startActivity(Intent(this@SignUpActivity,MainActivity::class.java))
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