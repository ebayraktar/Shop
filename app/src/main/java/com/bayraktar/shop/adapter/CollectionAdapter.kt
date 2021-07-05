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
import com.bayraktar.shop.model.Collection
import com.bumptech.glide.Glide

class CollectionAdapter(private val screenSize: Int, private val animation: Animation) :
    BaseAdapter<CollectionAdapter.ViewHolder>() {
    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_collection, viewGroup, false)

        return ViewHolder(view, listener)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.startAnimation(animation)

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val collection = mutableList[position] as Collection

        val imageUrl = when (screenSize) {
            Configuration.SCREENLAYOUT_SIZE_LARGE -> collection.cover?.url
            Configuration.SCREENLAYOUT_SIZE_NORMAL -> collection.cover?.medium?.url
            Configuration.SCREENLAYOUT_SIZE_SMALL -> collection.cover?.thumbnail?.url
            else -> null
        }

        if (imageUrl != null)
            Glide.with(viewHolder.itemView)
                .load(imageUrl)
                .fitCenter()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_broken_image_24)
                .into(viewHolder.ivCollection)
        else
            viewHolder.ivCollection.setImageResource(R.drawable.ic_broken_image_24)

        viewHolder.tvCollectionTitle.text = collection.title ?: "UNKNOWN TITLE"
        viewHolder.tvCollectionSubtitle.text = collection.definition ?: "UNKNOWN DEFINITION"
    }

    /**
     * Provides a reference to the type of views that we are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View, listener: IBaseListener?) : RecyclerView.ViewHolder(view) {
        val ivCollection: ImageView = view.findViewById(R.id.ivCollection)
        val tvCollectionTitle: TextView = view.findViewById(R.id.tvCollectionTitle)
        val tvCollectionSubtitle: TextView = view.findViewById(R.id.tvCollectionSubtitle)

        init {
            view.setOnClickListener { listener?.onClick(adapterPosition) }
            // Define click listener for the ViewHolder's View.
        }
    }
}