package com.bayraktar.shop.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bayraktar.shop.enums.ShopType
import com.bayraktar.shop.model.Shop
import com.bayraktar.shop.ui.fragment.ItemShopFragment

class ShopPagerAdapter(
    fragment: Fragment,
    private val shops: List<Shop>,
    private val shopType: ShopType
) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount() = shops.size

    override fun createFragment(position: Int) = when (shopType) {
        ShopType.EDITOR_SHOP -> ItemShopFragment.newInstance(shops[position], shopType)
        ShopType.NEW_SHOP -> ItemShopFragment.newInstance(shops[position], shopType)
    }
}