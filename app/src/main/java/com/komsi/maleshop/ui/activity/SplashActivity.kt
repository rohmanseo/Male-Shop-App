package com.komsi.maleshop.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.komsi.maleshop.R
import com.komsi.maleshop.persistence.Credential

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val api = Credential.getToken(this)

        Log.d("API",api)

        val timer: Thread = object : Thread() {
            override fun run() {
                try {
                    sleep(2000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                    if(api.equals("")){
                        startActivity(Intent(this@SplashActivity,LoginActivity::class.java))
                    }else{
                        startActivity(Intent(this@SplashActivity,MainActivity::class.java))
                    }
                    finish()
                }
            }
        }
        timer.start()
    }
}