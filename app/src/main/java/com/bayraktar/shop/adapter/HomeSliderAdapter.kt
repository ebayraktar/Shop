package com.bayraktar.shop.adapter

import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bayraktar.shop.R
import com.bayraktar.shop.interfaces.ISliderListener
import com.bayraktar.shop.model.Featured
import com.bayraktar.shop.model.base.BaseList
import com.bumptech.glide.Glide
import com.smarteist.autoimageslider.SliderViewAdapter

class HomeSliderAdapter(private val screenSize: Int) :
    SliderViewAdapter<HomeSliderAdapter.SliderAdapterVH>() {
    private var mSliderItems: MutableList<Featured> = ArrayList()
    private var listener: ISliderListener? = null
    fun setOnClickListener(listener: ISliderListener?) {
        this.listener = listener
    }

    fun renewItems(sliderItems: List<BaseList>) {
        mSliderItems = if (sliderItems.firstOrNull() is Featured)
            sliderItems as MutableList<Featured>
        else
            ArrayList()
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        mSliderItems.removeAt(position)
        //does not support item removed
        notifyDataSetChanged()
    }

    fun addItem(sliderItem: Featured) {
        mSliderItems.add(sliderItem)
        //does not support item inserted
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.slider_item, null)
        return SliderAdapterVH(inflate)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterVH, position: Int) {
        val featured = mSliderItems[position]
        viewHolder.tvTitle.text = featured.title
        viewHolder.tvSubtitle.text = featured.subTitle

        val imageUrl = when (screenSize) {
            Configuration.SCREENLAYOUT_SIZE_LARGE -> featured.cover?.url
            Configuration.SCREENLAYOUT_SIZE_NORMAL -> featured.cover?.medium?.url
            Configuration.SCREENLAYOUT_SIZE_SMALL -> featured.cover?.thumbnail?.url
            else -> null
        }

        if (imageUrl != null)
            Glide.with(viewHolder.itemView)
                .load(imageUrl)
                .fitCenter()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_broken_image_24)
                .into(viewHolder.ivSlider)
        else
            viewHolder.ivSlider.setImageResource(R.drawable.ic_broken_image_24)
    }

    override fun getCount(): Int {
        //slider view count could be dynamic size
        return mSliderItems.size
    }

    class SliderAdapterVH(itemView: View) : ViewHolder(itemView) {
        val ivSlider: ImageView = itemView.findViewById(R.id.ivSlider)
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvSubtitle: TextView = itemView.findViewById(R.id.tvSubtitle)
    }
}