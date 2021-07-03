package com.bayraktar.shop.adapter

import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.recyclerview.widget.RecyclerView
import com.bayraktar.shop.R
import com.bayraktar.shop.adapter.base.BaseAdapter
import com.bayraktar.shop.model.Shop
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_editor_shop.view.*

class EditorShopAdapter(private val screenSize: Int, private val animation: Animation) :
    BaseAdapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_editor_shop, parent, false),
            screenSize,
            animation
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mutableList[position] as Shop)
    }
}

class ViewHolder(
    private val view: View,
    private val screenSize: Int,
    private val animation: Animation
) :
    RecyclerView.ViewHolder(view) {
    fun bind(item: Shop) {
        view.startAnimation(animation)
        view.tvEditorShopTitle.text = item.name ?: "UNKNOWN NAME"
        view.tvEditorShopSubtitle.text = item.definition ?: "UNKNOWN DEFINITION"

        val logoUrl = when (screenSize) {
            Configuration.SCREENLAYOUT_SIZE_LARGE -> item.logo?.url
            Configuration.SCREENLAYOUT_SIZE_NORMAL -> item.logo?.medium?.url
            Configuration.SCREENLAYOUT_SIZE_SMALL -> item.logo?.thumbnail?.url
            else -> null
        }

        val popularUrlList = when (screenSize) {
            Configuration.SCREENLAYOUT_SIZE_LARGE -> item.popularProducts?.map { it.images?.firstOrNull()?.url }
            Configuration.SCREENLAYOUT_SIZE_NORMAL -> item.popularProducts?.map { it.images?.firstOrNull()?.medium?.url }
            Configuration.SCREENLAYOUT_SIZE_SMALL -> item.popularProducts?.map { it.images?.firstOrNull()?.thumbnail?.url }
            else -> null
        }

        Glide.with(view)
            .load(logoUrl)
            .fitCenter()
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_broken_image_24)
            .into(view.ivShopLogo)

        Glide.with(view)
            .load(popularUrlList?.get(0))
            .fitCenter()
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_broken_image_24)
            .into(view.ivPopular1)

        Glide.with(view)
            .load(popularUrlList?.get(1))
            .fitCenter()
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_broken_image_24)
            .into(view.ivPopular2)

        Glide.with(view)
            .load(popularUrlList?.get(2))
            .fitCenter()
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_broken_image_24)
            .into(view.ivPopular3)
    }
}