package com.komsi.maleshop.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.komsi.maleshop.R
import com.komsi.maleshop.adapter.ProductAdapter
import com.komsi.maleshop.adapter.ProductCallback
import com.komsi.maleshop.helper.ToolbarHelper
import com.komsi.maleshop.model.Produk
import com.komsi.maleshop.ui.activity.MainActivity
import com.komsi.maleshop.helper.FragmentHelper
import com.komsi.maleshop.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment(), ProductCallback {

    private lateinit var adapter: ProductAdapter

    companion object {
        private lateinit var searchFragment: SearchFragment
        fun newInstance(keyword: String): SearchFragment {
            searchFragment = SearchFragment()
            val args = Bundle()
            args.putString("keyword", keyword)
            searchFragment.arguments = args
            return searchFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            val btmNav = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation)
            MainActivity.hideBottomNavigationView(btmNav)
            ToolbarHelper.setTitle("Detail").displayBackArrow(true)

            val keyword = arguments?.getString("keyword").toString()
            ToolbarHelper.setTitle("Search").displayBackArrow(true)
            fetchSearch(keyword)
            svProduct.setQuery(keyword, false)
        }

        svProduct.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query != "") {
                    fetchSearch(query)
                    svProduct.setQuery(query, false)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return true
            }
        })
    }

    private fun fetchSearch(keyword: String) {
        rvProduct.itemAnimator = null
        rvProduct.layoutManager = GridLayoutManager(requireContext(), 3)
        adapter = ProductAdapter(requireContext(), this)
        rvProduct.adapter = adapter

        val shirtViewModel = ViewModelProvider(
                requireActivity(),
                ViewModelProvider.NewInstanceFactory()
        ).get(SearchViewModel::class.java)
        shirtViewModel.setList(requireActivity(), keyword)
        shirtViewModel.getList().observe(requireActivity(), androidx.lifecycle.Observer { contents ->
            adapter.setData(contents)
        })
    }

    override fun productOnClick(produk: Produk) {
        FragmentHelper.replaceFragmentToDetail(requireActivity().supportFragmentManager,produk)
    }

    override fun onFavoriteClick() {

    }
}
