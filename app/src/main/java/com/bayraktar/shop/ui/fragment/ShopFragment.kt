package com.bayraktar.shop.ui.fragment

import android.content.res.Configuration
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.bayraktar.shop.*
import com.bayraktar.shop.adapter.ShopPagerAdapter
import com.bayraktar.shop.enums.ShopType
import com.bayraktar.shop.model.Shop
import com.bayraktar.shop.ui.base.BaseListFragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_shop.*
import kotlinx.android.synthetic.main.item_header.*

class ShopFragment : BaseListFragment<Shop>() {
    private lateinit var shopAdapter: ShopPagerAdapter
    private var shopType: ShopType? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            shopType = when (it.getString(ARG_SHOP_TYPE)) {
                ShopType.EDITOR_SHOP.name -> ShopType.EDITOR_SHOP
                else -> ShopType.NEW_SHOP
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shop, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        if (shopType == null) {
            return
        }

        if (shopType == ShopType.EDITOR_SHOP) {
            editorShopAction()
        }

        tvItemTitle.text = title

        shopAdapter = ShopPagerAdapter(this, list as List<Shop>, shopType!!)
        vpEditorShops.adapter = shopAdapter

        vpEditorShops.offscreenPageLimit = 2

        vpEditorShops.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            //override method(s) what you need it
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                backgroundImageOperation(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        })
    }

    private fun editorShopAction() {
        context?.let { _it ->
            tvItemTitle.setTextColor(
                ContextCompat.getColor(
                    _it,
                    R.color.white
                )
            )

            tvAllItems.setTextColor(
                ContextCompat.getColor(
                    _it,
                    R.color.white
                )
            )
            clRoot.setBackgroundColor(ContextCompat.getColor(_it, R.color.black))
        }
    }

    private fun backgroundImageOperation(position: Int) {
        if (shopType == ShopType.EDITOR_SHOP) {
            val newUrl = when (App.SCREEN_SIZE) {
                Configuration.SCREENLAYOUT_SIZE_LARGE -> (list?.get(position) as Shop).cover?.url
                Configuration.SCREENLAYOUT_SIZE_NORMAL -> (list?.get(position) as Shop).cover?.medium?.url
                Configuration.SCREENLAYOUT_SIZE_SMALL -> (list?.get(position) as Shop).cover?.thumbnail?.url
                else -> null
            }

            context?.let {
                Glide.with(it)
                    .load(newUrl)
                    .fitCenter()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_broken_image_24)
                    .into(ivBackground)
            }
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param shopList Parameter 1.
         * @param title Parameter 2.
         * @return A new instance of fragment EditorShopFragment.
         */

        @JvmStatic
        fun newInstance(shopList: List<Parcelable>, title: String, shopType: ShopType) =
            ShopFragment().apply {
                val arrayList: ArrayList<out Parcelable?> = if (shopList is ArrayList) {
                    shopList
                } else {
                    ArrayList(shopList)
                }

                arguments = Bundle().apply {
                    putParcelableArrayList(ARG_LIST, arrayList)
                    putString(ARG_TITLE, title)
                    putString(ARG_SHOP_TYPE, shopType.name)
                }
            }
    }
}