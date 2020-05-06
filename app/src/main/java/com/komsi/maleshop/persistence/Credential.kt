package com.komsi.maleshop.persistence

import android.content.Context

class Credential {
    companion object{

        fun getToken(context: Context): String{
            val pref = context.getSharedPreferences("USER", Context.MODE_PRIVATE)
            return pref.getString("Token","").toString()
        }

        fun setToken(context: Context,accessToken: String){
            val prefEditor = context.getSharedPreferences("USER",Context.MODE_PRIVATE).edit()
            prefEditor.putString("Token",accessToken)
            prefEditor.apply()
        }
        fun deleteToken(context: Context){
            val prefEditor = context.getSharedPreferences("USER",Context.MODE_PRIVATE).edit()
            prefEditor.remove("Token")
            prefEditor.apply()
        }
    }
}