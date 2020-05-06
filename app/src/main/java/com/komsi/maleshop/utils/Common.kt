package com.komsi.maleshop.utils

import android.content.Context
import android.widget.Toast

fun String.toaster(context: Context){
    Toast.makeText(context,this,Toast.LENGTH_SHORT).show()
}