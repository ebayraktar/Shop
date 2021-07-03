package com.bayraktar.shop.adapter.base

import android.view.View
import androidx.constraintlayout.helper.widget.Carousel
import com.bayraktar.shop.model.Shop

class NewShopAdapter() : Carousel.Adapter {
    private var mutableList: MutableList<Shop> = ArrayList()

    fun setItems(items: MutableList<Shop>) {
        mutableList = items
    }

    override fun count() = mutableList.size

    override fun populate(view: View?, index: Int) {
        TODO("Not yet implemented")
    }

    override fun onNewItem(index: Int) {
        TODO("Not yet implemented")
    }
}