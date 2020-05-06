package com.komsi.maleshop.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.komsi.maleshop.R
import com.komsi.maleshop.helper.ToolbarHelper
import com.komsi.maleshop.model.Produk
import com.komsi.maleshop.ui.activity.MainActivity
import com.komsi.maleshop.utils.ConstApi
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    companion object {
        private lateinit var detailFragment: DetailFragment
        fun newInstance(produk: Produk): DetailFragment {
            detailFragment = DetailFragment()
            val args = Bundle()
            args.putParcelable("product", produk)
            detailFragment.arguments = args

            return detailFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val produk = arguments?.getParcelable<Produk>("product")
        if (produk!=null){
            tv_nama_produk.text = produk.nama
            tv_harga_produk.text = produk.harga.toString()
            tvDetail.text = produk.detail
            val url = ConstApi.PRODUCT_IMAGE_URL.value + produk.foto

            Glide.with(requireActivity()).load(url).into(img_produk)
            ToolbarHelper.setTitle("Detail").displayBackArrow(true)
            btnAddtoCart.setOnClickListener {
                AddToCartDialogFragment.newInstance(produk).show(requireFragmentManager(),"dialog add to cart")
            }

            val btmNav = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation)
            MainActivity.hideBottomNavigationView(btmNav)
        }
    }
}
