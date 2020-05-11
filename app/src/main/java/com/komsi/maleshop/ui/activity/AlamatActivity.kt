package com.komsi.maleshop.ui.activity

import android.app.Activity
import android.content.Intent
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.androidnetworking.interfaces.StringRequestListener
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.Place
import com.komsi.maleshop.R
import com.komsi.maleshop.persistence.Credential
import com.komsi.maleshop.utils.ConstApi
import com.rtchagas.pingplacepicker.PingPlacePicker
import org.jetbrains.anko.toast
import org.json.JSONObject
import java.util.*

class AlamatActivity : AppCompatActivity() {

    private val pingActivityRequestCode = 1001
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showPlacePicker()
    }

    private fun showPlacePicker() {
        val builder = PingPlacePicker.IntentBuilder()

        builder.setAndroidApiKey(getString(R.string.key_google_apis_android))
                .setMapsApiKey(getString(R.string.key_google_apis_maps))
                .setLatLng(LatLng(-7.771440, 110.377191))

        // If you want to set a initial location
        // rather then the current device location.
        // pingBuilder.setLatLng(LatLng(37.4219999, -122.0862462))

        try {
            val placeIntent = builder.build(this)
            startActivityForResult(placeIntent, pingActivityRequestCode)
        } catch (ex: Exception) {
            toast("Google Play Services is not Available")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if ((requestCode == pingActivityRequestCode) && (resultCode == Activity.RESULT_OK)) {

            val place: Place? = PingPlacePicker.getPlace(data!!)
            val lat = place?.latLng?.latitude
            val long = place?.latLng?.longitude

            val address = getRealAddress(lat ?: 0.0, long ?: 0.0)
            fetchProfileData(address)

        }
    }

    override fun onResume() {
        super.onResume()
    }

    private fun updateProfile(name: String, phone: String, address: String, foto: String) {
        AndroidNetworking.initialize(this)
        AndroidNetworking.put(ConstApi.UPDATE_PROFILE.value)
                .addHeaders("Authorization", "Bearer ${Credential.getToken(this)}")
                .setTag("update profile")
                .addUrlEncodeFormBodyParameter("nama", name)
                .addUrlEncodeFormBodyParameter("no_telp", phone)
                .addUrlEncodeFormBodyParameter("alamat", address)
                .addUrlEncodeFormBodyParameter("foto", foto)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsString(object : StringRequestListener {
                    override fun onResponse(response: String?) {
                        Log.d("profileResponse", response.toString())
                        onBackPressed()
                    }

                    override fun onError(anError: ANError?) {
                        Log.d("profileResponse", anError.toString())
                        onBackPressed()
                    }
                })
    }

    private fun fetchProfileData(address: String) {
        AndroidNetworking.initialize(this)
        AndroidNetworking.post(ConstApi.PROFILE.value)
                .addHeaders("Authorization", "Bearer ${Credential.getToken(this)}")
                .setTag("profile")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        Log.d("profileResponse", response.toString())
                        val name = response.getString("nama")
                        val phone = response.getString("no_telp")
                        val foto = response.getString("foto")

                        updateProfile(name, phone, address, foto)

                    }

                    override fun onError(anError: ANError?) {
                        Log.d("profileResponse", anError.toString())
                        onBackPressed()
                    }
                })


    }

    private fun getRealAddress(lat: Double, long: Double): String {
        val geocoder = Geocoder(this, Locale.getDefault())
        val addresses = geocoder.getFromLocation(
                lat, long, 1
        )
        val result = addresses[0].getAddressLine(0)
        return result
    }
}
