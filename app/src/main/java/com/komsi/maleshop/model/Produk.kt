package com.komsi.maleshop.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Produk (val id:Int,val nama:String,val foto:String, val harga: Double, val detail:String): Parcelable