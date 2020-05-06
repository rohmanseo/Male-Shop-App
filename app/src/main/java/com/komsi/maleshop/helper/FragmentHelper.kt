package com.komsi.maleshop.helper

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.komsi.maleshop.R
import com.komsi.maleshop.model.Produk
import com.komsi.maleshop.ui.fragment.DetailFragment

object FragmentHelper {
    fun replaceFragmentToDetail(fragmentManager: FragmentManager,produk:Produk){
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, DetailFragment.newInstance(produk),"DETAIL")
                .addToBackStack("null").commit()
    }
}