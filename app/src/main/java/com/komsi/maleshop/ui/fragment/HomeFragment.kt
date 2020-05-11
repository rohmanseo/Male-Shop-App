package com.komsi.maleshop.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.komsi.maleshop.R
import com.komsi.maleshop.adapter.ProductAdapter
import com.komsi.maleshop.adapter.ProductCallback
import com.komsi.maleshop.adapter.SlideshowAdapter
import com.komsi.maleshop.helper.ToolbarHelper
import com.komsi.maleshop.model.Produk
import com.komsi.maleshop.model.Slide
import com.komsi.maleshop.helper.FragmentHelper
import com.komsi.maleshop.viewmodel.ArrivalViewModel
import com.komsi.maleshop.viewmodel.SlideshowViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment(), ProductCallback {

    private lateinit var adapter: SlideshowAdapter
    private lateinit var listSlideshow: ArrayList<Slide>
    private lateinit var arrivalAdapter: ProductAdapter
    private lateinit var listArrival: ArrayList<Produk>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        text_seeArrival.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer, ArrivalFragment()).addToBackStack(null).commit()
        }
        getSlideshow()
        getArrival()


        imgSetting.setOnClickListener {
            DialogSettingFragment().show(requireActivity().supportFragmentManager.beginTransaction(), "setting")
        }
        svProduct.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query != "") {
                    val transaction = requireActivity().supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.fragmentContainer, SearchFragment.newInstance(query)).addToBackStack(null).commit()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return true
            }
        })
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
        adapter = SlideshowAdapter(requireContext())
        fetchSlideshow()
        slider_pager.adapter = adapter
        indicator.setupWithViewPager(slider_pager, true)
//        setTimer()
    }

    private fun fetchSlideshow() {
        val slideShowViewModel = ViewModelProvider(
                requireActivity(),
                ViewModelProvider.NewInstanceFactory()
        ).get(SlideshowViewModel::class.java)
        slideShowViewModel.setList(requireActivity())
        slideShowViewModel.getList().observe(requireActivity(), androidx.lifecycle.Observer { contents ->
            adapter.setData(contents)
        })
    }

    private fun getArrival() {
        listArrival = ArrayList()
        arrivalAdapter = ProductAdapter(requireContext(), this)
        fetchArrival()
        rv_Arrival.itemAnimator = null
        rv_Arrival.adapter = arrivalAdapter
        rv_Arrival.setHasFixedSize(true)
        rv_Arrival.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

    }

    override fun onResume() {
        ToolbarHelper.setTitle(getString(R.string.home))
        super.onResume()
    }

    private fun fetchArrival() {
        val arrivalViewModel = ViewModelProvider(
                requireActivity(),
                ViewModelProvider.NewInstanceFactory()
        ).get(ArrivalViewModel::class.java)
        arrivalViewModel.setList(requireActivity())
        arrivalViewModel.getList().observe(requireActivity(), androidx.lifecycle.Observer { contents ->
            arrivalAdapter.setData(contents)
        })
    }

    override fun productOnClick(produk: Produk) {
        FragmentHelper.replaceFragmentToDetail(requireActivity().supportFragmentManager, produk)
    }

    override fun onFavoriteClick() {
        fetchArrival()
    }
}
