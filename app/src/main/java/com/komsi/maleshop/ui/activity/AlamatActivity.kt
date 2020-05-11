package com.komsi.maleshop.ui.activity

import android.app.Activity
import android.content.Intent
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.Place
import com.komsi.maleshop.R
import com.rtchagas.pingplacepicker.PingPlacePicker
import kotlinx.android.synthetic.main.activity_alamat.*
import org.jetbrains.anko.toast
import java.util.*

class AlamatActivity : AppCompatActivity() {
    private val pingActivityRequestCode = 1001
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alamat)

        btnOpenPlacePicker.setOnClickListener {
            showPlacePicker()
        }

    }

    private fun showPlacePicker() {
        val builder = PingPlacePicker.IntentBuilder()

        builder.setAndroidApiKey(getString(R.string.key_google_apis_android))
                .setMapsApiKey(getString(R.string.key_google_apis_maps))
                .setLatLng(LatLng(-7.771440,110.377191))

        // If you want to set a initial location
        // rather then the current device location.
        // pingBuilder.setLatLng(LatLng(37.4219999, -122.0862462))

        try {
            val placeIntent = builder.build(this)
            startActivityForResult(placeIntent, pingActivityRequestCode)
        } catch (ex: Exception) {
            toast( "Google Play Services is not Available")
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if ((requestCode == pingActivityRequestCode) && (resultCode == Activity.RESULT_OK)) {

            val place: Place? = PingPlacePicker.getPlace(data!!)

            val lat = place?.latLng?.latitude
            val long = place?.latLng?.longitude

            val myAddress = getRealAddress(lat ?: 0.0, long ?: 0.0)
            toast("You selected: $myAddress")


        }
    }

    fun getRealAddress(lat: Double, long: Double): String {
        val geocoder = Geocoder(this, Locale.getDefault())
        val addresses = geocoder.getFromLocation(
                lat, long, 1
        )
        val result = addresses[0].getAddressLine(0)
        return result
    }
}
