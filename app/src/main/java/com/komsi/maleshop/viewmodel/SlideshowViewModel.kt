package com.komsi.maleshop.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.komsi.maleshop.utils.ConstApi
import com.komsi.maleshop.repository.local.Credential
import com.komsi.maleshop.model.Slide
import org.json.JSONObject


class SlideshowViewModel : ViewModel() {
    private val listSlide = MutableLiveData<ArrayList<Slide>>()
    fun setList(context: Context) {
        val list = ArrayList<Slide>()
        AndroidNetworking.initialize(context)
        AndroidNetworking.get(ConstApi.SLIDESHOW.value)
                .addHeaders("Authorization", "Bearer ${Credential.getToken(context)}")
                .setTag("slideshow")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        val jsonArr = response.getJSONArray("data")
                        for (i in 0 until jsonArr.length()) {
                            val jsonObj = jsonArr.getJSONObject(i)
                            Log.d("slideshowresponse", jsonObj.getString("foto"))
                            val foto = ConstApi.SLIDESHOW_IMAGE_URL.value + jsonObj.getString("foto")
                            val slide = Slide(foto, "")
                            list.add(slide)
                        }
                        listSlide.postValue(list)
                    }

                    override fun onError(anError: ANError?) {
                        Log.d("Slideshowresponse", anError.toString())

                    }
                })

    }

    fun getList(): LiveData<ArrayList<Slide>> {
        return listSlide
    }
}