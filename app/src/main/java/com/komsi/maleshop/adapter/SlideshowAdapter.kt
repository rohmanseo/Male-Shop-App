package com.komsi.maleshop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.komsi.maleshop.R
import com.komsi.maleshop.model.Slide
import java.util.*

class SlideshowAdapter(private val mcontext: Context) : PagerAdapter() {
    var mList = ArrayList<Slide>()
    fun setData(list: ArrayList<Slide>) {
        mList.clear()
        mList.addAll(list)
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return mList.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val slideLayout = inflater.inflate(R.layout.slider_item, null)
        val slideImg = slideLayout.findViewById<ImageView>(R.id.slide_img)
        val slideText = slideLayout.findViewById<TextView>(R.id.slide_title)
        Glide.with(mcontext).load(mList[position].image).into(slideImg)
        slideText.text = mList[position].title
        container.addView(slideLayout)
        return slideLayout
    }

    override fun isViewFromObject(view: View, o: Any): Boolean {
        return view === o
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

}