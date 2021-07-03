package com.bayraktar.shop.ui

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.view.animation.AnimationUtils
import com.bayraktar.shop.App
import com.bayraktar.shop.R
import com.bayraktar.shop.adapter.CollectionAdapter
import com.bayraktar.shop.ui.base.ARG_LIST
import com.bayraktar.shop.ui.base.ARG_TITLE
import com.bayraktar.shop.ui.base.BaseListFragment
import kotlinx.android.synthetic.main.fragment_base_list.*
import kotlinx.android.synthetic.main.item_header.*

class CollectionFragment : BaseListFragment() {
    private lateinit var collectionAdapter: CollectionAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvItemTitle.text = title

        val animation = AnimationUtils.loadAnimation(context, R.anim.left_to_right)
        collectionAdapter = CollectionAdapter(App.SCREEN_SIZE, animation)
        rvItems.adapter = collectionAdapter

        collectionAdapter.renewItems(list)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param collectionList Parameter 1.
         * @param title Parameter 2.
         * @return A new instance of fragment CollectionFragment.
         */

        @JvmStatic
        fun newInstance(collectionList: List<Parcelable>, title: String) =
            CollectionFragment().apply {
                val arrayList: ArrayList<out Parcelable?> = if (collectionList is ArrayList) {
                    collectionList
                } else {
                    ArrayList(collectionList)
                }

                arguments = Bundle().apply {
                    putParcelableArrayList(ARG_LIST, arrayList)
                    putString(ARG_TITLE, title)

                }
            }
    }

}