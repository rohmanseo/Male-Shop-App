package com.komsi.maleshop.utils

import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

fun View.visible(state: Boolean){
    if (state){
        this.visibility = View.VISIBLE
    }else{
        this.visibility = View.GONE
    }
}

fun RecyclerView.visible(state: Boolean){
    if (state){
        this.visibility = View.VISIBLE
    }else{
        this.visibility = View.GONE
    }
}

fun TextView.bindTextViewWithEditText(edtText: EditText,value:String){
    this.text = value
    edtText.setText(this.text)
}