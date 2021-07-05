package com.bayraktar.shop.adapter

import android.content.res.Configuration
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bayraktar.shop.R
import com.bayraktar.shop.adapter.base.BaseAdapter
import com.bayraktar.shop.interfaces.IBaseListener
import com.bayraktar.shop.model.Product
import com.bumptech.glide.Glide
import java.util.*


class ProductAdapter(private val screenSize: Int, private val animation: Animation) :
    BaseAdapter<ProductAdapter.ViewHolder>() {

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_product, viewGroup, false)

        return ViewHolder(view, listener)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.startAnimation(animation)

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val product = mutableList[position] as Product

        val imageUrl = when (screenSize) {
            Configuration.SCREENLAYOUT_SIZE_LARGE -> product.images?.firstOrNull()?.url
            Configuration.SCREENLAYOUT_SIZE_NORMAL -> product.images?.firstOrNull()?.medium?.url
            Configuration.SCREENLAYOUT_SIZE_SMALL -> product.images?.firstOrNull()?.thumbnail?.url
            else -> null
        }

        if (imageUrl != null)
            Glide.with(viewHolder.itemView)
                .load(imageUrl)
                .fitCenter()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_broken_image_24)
                .into(viewHolder.ivProduct)
        else
            viewHolder.ivProduct.setImageResource(R.drawable.ic_broken_image_24)

        viewHolder.tvProductTitle.text = product.title
        viewHolder.tvProductSubtitle.text = product.shop?.name
        viewHolder.tvProductPrice.text = "${product.price} TL"

        viewHolder.tvProductOldPrice.paintFlags += Paint.STRIKE_THRU_TEXT_FLAG
        if (product.oldPrice != null)
            viewHolder.tvProductOldPrice.text = "${product.oldPrice} TL"
        else
            viewHolder.tvProductOldPrice.visibility = View.INVISIBLE
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.clearAnimation()
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = mutableList.size

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View, listener: IBaseListener?) : RecyclerView.ViewHolder(view) {
        val ivProduct: ImageView = view.findViewById(R.id.ivProduct)
        val tvProductTitle: TextView = view.findViewById(R.id.tvProductTitle)
        val tvProductSubtitle: TextView = view.findViewById(R.id.tvProductSubtitle)
        val tvProductPrice: TextView = view.findViewById(R.id.tvProductPrice)
        val tvProductOldPrice: TextView = view.findViewById(R.id.tvProductOldPrice)

        init {
            view.setOnClickListener { listener?.onClick(adapterPosition) }
            // Define click listener for the ViewHolder's View.
        }
    }
}