package com.bayraktar.shop.ui.fragment

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bayraktar.shop.ARG_LIST
import com.bayraktar.shop.ARG_TITLE
import com.bayraktar.shop.App
import com.bayraktar.shop.R
import com.bayraktar.shop.adapter.CategoryAdapter
import com.bayraktar.shop.interfaces.IBaseListener
import com.bayraktar.shop.model.Category
import com.bayraktar.shop.ui.base.BaseListFragment
import kotlinx.android.synthetic.main.fragment_base_list.*
import kotlinx.android.synthetic.main.item_header.*

class CategoryFragment : BaseListFragment<Category>() {
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvItemTitle.text = title
        tvAllItems.visibility = View.INVISIBLE
        context?.let {
            clRoot.setBackgroundColor(
                ContextCompat.getColor(
                    it,
                    R.color.background_category
                )
            )
        }

        val animation = AnimationUtils.loadAnimation(context, R.anim.left_to_right)
        categoryAdapter = CategoryAdapter(App.SCREEN_SIZE, animation)
        categoryAdapter.setOnClickListener(this)
        rvItems.adapter = categoryAdapter

        categoryAdapter.setItems(list)
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
            CategoryFragment().apply {
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