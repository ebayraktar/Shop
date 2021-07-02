package com.bayraktar.shop.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bayraktar.shop.R
import com.bayraktar.shop.interfaces.ISliderOnClickListener
import com.bayraktar.shop.model.SliderItem
import com.bumptech.glide.Glide
import com.smarteist.autoimageslider.SliderViewAdapter
import java.util.*

class HomeSliderAdapter :
    SliderViewAdapter<HomeSliderAdapter.SliderAdapterVH>() {
    private var mSliderItems: MutableList<SliderItem> = ArrayList()
    private var listener: ISliderOnClickListener? = null
    fun setOnClickListener(listener: ISliderOnClickListener?) {
        this.listener = listener
    }

    fun renewItems(sliderItems: MutableList<SliderItem>) {
        mSliderItems = sliderItems
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        mSliderItems.removeAt(position)
        notifyDataSetChanged()
    }

    fun addItem(sliderItem: SliderItem) {
        mSliderItems.add(sliderItem)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.slider_item, null)
        return SliderAdapterVH(inflate)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterVH, position: Int) {
        val (id, _, url) = mSliderItems[position]
        Glide.with(viewHolder.itemView)
            .load(url)
            .fitCenter() //                .placeholder(R.drawable.ic_outline_terrain_24)
            //                .error(R.drawable.ic_outline_broken_image_24)
            .into(viewHolder.imageViewBackground)
        viewHolder.itemView.setOnClickListener { listener!!.onClick(id) }
    }

    override fun getCount(): Int {
        //slider view count could be dynamic size
        return mSliderItems.size
    }

    class SliderAdapterVH(itemView: View) : ViewHolder(itemView) {
        var imageViewBackground: ImageView = itemView.findViewById(R.id.iv_auto_image_slider)

    }
}