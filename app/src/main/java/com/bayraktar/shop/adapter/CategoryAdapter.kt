package com.bayraktar.shop.adapter

import android.content.res.Configuration
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
import com.bayraktar.shop.model.Category
import com.bumptech.glide.Glide

class CategoryAdapter(private val screenSize: Int, private val animation: Animation) :
    BaseAdapter<CategoryAdapter.ViewHolder>() {
    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_category, viewGroup, false)

        return ViewHolder(view, listener)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.startAnimation(animation)

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val category = mutableList[position] as Category

        val imageUrl = when (screenSize) {
            Configuration.SCREENLAYOUT_SIZE_LARGE -> category.logo?.url
            Configuration.SCREENLAYOUT_SIZE_NORMAL -> category.logo?.medium?.url
            Configuration.SCREENLAYOUT_SIZE_SMALL -> category.logo?.thumbnail?.url
            else -> null
        }

        if (imageUrl != null)
            Glide.with(viewHolder.itemView)
                .load(imageUrl)
                .fitCenter()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_broken_image_24)
                .into(viewHolder.ivCategory)
        else
            viewHolder.ivCategory.setImageResource(R.drawable.ic_broken_image_24)

        viewHolder.tvCategoryTitle.text = category.name ?: "UNKNOWN CATEGORY"
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View, listener: IBaseListener?) : RecyclerView.ViewHolder(view) {
        val ivCategory: ImageView = view.findViewById(R.id.ivCategory)
        val tvCategoryTitle: TextView = view.findViewById(R.id.tvCategoryTitle)

        init {
            view.setOnClickListener { listener?.onClick(adapterPosition) }
            // Define click listener for the ViewHolder's View.
        }
    }
}