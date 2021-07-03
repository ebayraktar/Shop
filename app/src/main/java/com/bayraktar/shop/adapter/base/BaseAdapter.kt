package com.bayraktar.shop.adapter.base

import androidx.recyclerview.widget.RecyclerView
import com.bayraktar.shop.interfaces.IBaseListener
import com.bayraktar.shop.model.base.BaseList

abstract class BaseAdapter<V> : RecyclerView.Adapter<V>() where  V : RecyclerView.ViewHolder {
    protected var mutableList: MutableList<BaseList> = ArrayList()
    protected var listener: IBaseListener? = null

    fun setOnClickListener(listener: IBaseListener?) {
        this.listener = listener
    }

    fun setItems(list: List<BaseList>?) {
        mutableList = (list ?: ArrayList()) as MutableList<BaseList>
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        mutableList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun addItem(sliderItem: BaseList) {
        mutableList.add(sliderItem)
        notifyItemInserted(itemCount)
    }

    override fun onViewDetachedFromWindow(holder: V) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.clearAnimation()
    }

    override fun getItemCount() = mutableList.size

}