package com.bayraktar.shop.ui.fragment

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.view.animation.AnimationUtils
import com.bayraktar.shop.ARG_LIST
import com.bayraktar.shop.ARG_TITLE
import com.bayraktar.shop.App
import com.bayraktar.shop.R
import com.bayraktar.shop.adapter.ProductAdapter
import com.bayraktar.shop.ui.base.BaseListFragment
import kotlinx.android.synthetic.main.fragment_base_list.*
import kotlinx.android.synthetic.main.item_header.*

class ProductFragment : BaseListFragment() {
    private lateinit var productAdapter: ProductAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvItemTitle.text = title

        val animation = AnimationUtils.loadAnimation(context, R.anim.left_to_right)

        productAdapter = ProductAdapter(App.SCREEN_SIZE, animation)
        rvItems.adapter = productAdapter

        productAdapter.setItems(list)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param categoryList Parameter 1.
         * @param title Parameter 2.
         * @return A new instance of fragment CategoryFragment.
         */

        @JvmStatic
        fun newInstance(categoryList: List<Parcelable>, title: String) =
            ProductFragment().apply {
                val arrayList: ArrayList<out Parcelable?> = if (categoryList is ArrayList) {
                    categoryList
                } else {
                    ArrayList(categoryList)
                }
                arguments = Bundle().apply {
                    putParcelableArrayList(ARG_LIST, arrayList)
                    putString(ARG_TITLE, title)
                }
            }
    }
}