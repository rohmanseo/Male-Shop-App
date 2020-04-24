package com.komsi.maleshop.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.komsi.maleshop.R
import com.komsi.maleshop.activity.ArrivalActivity
import com.komsi.maleshop.adapter.RecyclerViewAdapter
import com.komsi.maleshop.adapter.RvArrivalAdapter
import com.komsi.maleshop.adapter.SlideshowAdapter
import com.komsi.maleshop.constant.ConstApi
import com.komsi.maleshop.constant.Credential
import com.komsi.maleshop.model.Produk
import com.komsi.maleshop.model.Slide
import kotlinx.android.synthetic.main.fragment_home.*
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {

    private lateinit var adapter: SlideshowAdapter
    private lateinit var listSlideshow: ArrayList<Slide>
    private lateinit var arrivalAdapter: RvArrivalAdapter
    private lateinit var listArrival: ArrayList<Produk>


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        text_seeArrival.setOnClickListener {
            startActivity(Intent(requireContext(), ArrivalActivity::class.java))
        }
        getSlideshow()
        getArrival()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    private fun setTimer() {
        val handler = Handler()
        val runnable = Runnable {
            var i = slider_pager.getCurrentItem()
            if (i == adapter.mList.size - 1) {
                i = 0
                slider_pager.setCurrentItem(i, true)
            } else {
                i++
                slider_pager.setCurrentItem(i, true)
            }
        }
        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(runnable)
            }
        }, 4000, 4000)
    }

    private fun getSlideshow() {
        listSlideshow = ArrayList()
        fetchSlideshow()
        adapter = SlideshowAdapter(requireContext(), listSlideshow)
        slider_pager.adapter = adapter
        indicator.setupWithViewPager(slider_pager, true)
//        setTimer()
    }

    private fun fetchSlideshow() {
        AndroidNetworking.initialize(requireContext())
        AndroidNetworking.get(ConstApi.SLIDESHOW.value)
                .addHeaders("Authorization", "Bearer ${Credential.getToken(requireContext())}")
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
                            listSlideshow.add(slide)
                            adapter.notifyDataSetChanged()
                        }
                    }

                    override fun onError(anError: ANError?) {
                        Log.d("Slideshowresponse", anError.toString())

                    }
                })
    }

    fun getArrival(){
        listArrival = ArrayList()
        arrivalAdapter = RvArrivalAdapter(requireContext(),listArrival)
        fetchArrival()
        rv_Arrival.adapter = arrivalAdapter
        rv_Arrival.setHasFixedSize(true)
        rv_Arrival.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL, false)

    }

    private fun fetchArrival() {
        AndroidNetworking.initialize(requireContext())
        AndroidNetworking.get(ConstApi.NEWPRODUCT.value)
                .addHeaders("Authorization", "Bearer ${Credential.getToken(requireContext())}")
                .setTag("newarrival")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        val jsonArr = response.getJSONArray("data")
                        for (i in 0 until jsonArr.length()) {
                            val jsonObj = jsonArr.getJSONObject(i)
                            val id = jsonObj.getInt("id")
                            val name = jsonObj.getString("nama")
                            val foto = jsonObj.getString("foto")
                            val harga = jsonObj.getString("harga")
                            val detail = jsonObj.getString("diskripsi")

                            val produk = Produk(id, name, foto, harga.toDouble(), detail)
                            listArrival.add(produk)
                            arrivalAdapter.notifyDataSetChanged()
                        }
                    }

                    override fun onError(anError: ANError?) {
                        Log.d("newarrivalresponse", anError.toString())
                    }
                })
    }
}
//
//        //data
//        val lstArrival: MutableList<Produk> = ArrayList()
//        lstArrival.add(Produk("1", "Tolliver-x", R.drawable.bg1, "Rp 400.000", "-", 0.0, "-", "-"))
//        lstArrival.add(Produk("2", "AutivFixin", R.drawable.bg2, "Rp 450.000", "-", 0.0, "-", "-"))
//        lstArrival.add(Produk("1", "Banana Republic", R.drawable.bg3, "Rp 300.000", "-", 0.0, "-", "-"))
//        lstArrival.add(Produk("1", "hemmeh", R.drawable.bg4, "Rp 500.000", "-", 0.0, "-", "-"))
//        lstArrival.add(Produk("1", "hmgoeppod", R.drawable.bg5, "Rp 650.000", "-", 0.0, "-", "-"))
//        lstArrival.add(Produk("2", "jackNicklaus", R.drawable.bg6, "Rp 325.000", "-", 0.0, "-", "-"))
//        val arrivalAdapter = RvArrivalAdapter(requireContext(), lstArrival)
//        rv_Arrival.adapter = arrivalAdapter
//        rv_Arrival.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
