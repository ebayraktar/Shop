package com.bayraktar.shop.ui.fragment

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bayraktar.shop.ARG_SHOP
import com.bayraktar.shop.ARG_SHOP_TYPE
import com.bayraktar.shop.App
import com.bayraktar.shop.R
import com.bayraktar.shop.enums.ShopType
import com.bayraktar.shop.model.Shop
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_item_editor_shop.*
import kotlinx.android.synthetic.main.fragment_item_new_shop.*


/**
 * A simple [Fragment] subclass.
 * Use the [ItemShopFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

open class ItemShopFragment : Fragment() {
    private var shop: Shop? = null
    private var shopType: ShopType? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            shop = it.getParcelable(ARG_SHOP)
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
        return when (shopType) {
            ShopType.EDITOR_SHOP ->
                inflater.inflate(R.layout.fragment_item_editor_shop, container, false)
            else -> inflater.inflate(R.layout.fragment_item_new_shop, container, false)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when (shopType) {
            ShopType.EDITOR_SHOP ->
                initEditorShop(view)
            else -> initNewShop(view)
        }
    }

    private fun initEditorShop(view: View) {

        shop?.let {
            tvEditorShopTitle.text = it.name ?: "UNKNOWN NAME"
            tvEditorShopSubtitle.text = it.definition ?: "UNKNOWN DEFINITION"

            val logoUrl = when (App.SCREEN_SIZE) {
                Configuration.SCREENLAYOUT_SIZE_LARGE -> it.logo?.url
                Configuration.SCREENLAYOUT_SIZE_NORMAL -> it.logo?.medium?.url
                Configuration.SCREENLAYOUT_SIZE_SMALL -> it.logo?.thumbnail?.url
                else -> null
            }

            val popularUrlList = when (App.SCREEN_SIZE) {
                Configuration.SCREENLAYOUT_SIZE_LARGE -> it.popularProducts?.map { _it -> _it.images?.firstOrNull()?.url }
                Configuration.SCREENLAYOUT_SIZE_NORMAL -> it.popularProducts?.map { _it -> _it.images?.firstOrNull()?.medium?.url }
                Configuration.SCREENLAYOUT_SIZE_SMALL -> it.popularProducts?.map { _it -> _it.images?.firstOrNull()?.thumbnail?.url }
                else -> null
            }

            Glide.with(view)
                .load(logoUrl)
                .fitCenter()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_broken_image_24)
                .into(ivShopLogo)

            Glide.with(view)
                .load(popularUrlList?.get(0))
                .fitCenter()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_broken_image_24)
                .into(ivPopular1)

            Glide.with(view)
                .load(popularUrlList?.get(1))
                .fitCenter()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_broken_image_24)
                .into(ivPopular2)

            Glide.with(view)
                .load(popularUrlList?.get(2))
                .fitCenter()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_broken_image_24)
                .into(ivPopular3)
        }
    }

    private fun initNewShop(view: View) {

        shop?.let {

            tvNewShopTitle.text = it.name ?: "UNKNOWN NAME"
            tvNewShopSubtitle.text = it.definition ?: "UNKNOWN DEFINITION"
            tvProductCount.text = "${it.productCount} ÜRÜN"

            val coverUrl = when (App.SCREEN_SIZE) {
                Configuration.SCREENLAYOUT_SIZE_LARGE -> it.cover?.url
                Configuration.SCREENLAYOUT_SIZE_NORMAL -> it.cover?.medium?.url
                Configuration.SCREENLAYOUT_SIZE_SMALL -> it.cover?.thumbnail?.url
                else -> null
            }

            Glide.with(view)
                .load(coverUrl)
                .fitCenter()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_broken_image_24)
                .into(ivShopBackground)


            val logoUrl = when (App.SCREEN_SIZE) {
                Configuration.SCREENLAYOUT_SIZE_LARGE -> it.logo?.url
                Configuration.SCREENLAYOUT_SIZE_NORMAL -> it.logo?.medium?.url
                Configuration.SCREENLAYOUT_SIZE_SMALL -> it.logo?.thumbnail?.url
                else -> null
            }

            Glide.with(view)
                .load(logoUrl)
                .fitCenter()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_broken_image_24)
                .into(ivNewShopLogo)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param shop Parameter 1.
         * @return A new instance of fragment ItemEditorShopFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(shop: Shop, shopType: ShopType) =
            ItemShopFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_SHOP, shop)
                    putString(ARG_SHOP_TYPE, shopType.name)
                }
            }
    }
}