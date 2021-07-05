package com.bayraktar.shop.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bayraktar.shop.ARG_LIST
import com.bayraktar.shop.ARG_TITLE
import com.bayraktar.shop.R
import com.bayraktar.shop.interfaces.IBaseListener
import com.bayraktar.shop.model.Category
import com.bayraktar.shop.model.Collection
import com.bayraktar.shop.model.Product
import com.bayraktar.shop.model.Shop
import com.bayraktar.shop.model.base.BaseList


/**
 * A simple [Fragment] subclass.
 */
abstract class BaseListFragment<V> : Fragment(), IBaseListener {
    var list: List<BaseList>? = null
    var title: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            list = it.getParcelableArrayList(ARG_LIST)
            title = it.getString(ARG_TITLE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_base_list, container, false)
    }

    override fun onClick(index: Int) {
        val text = when (list?.firstOrNull()) {
            is Category -> (list?.get(index) as Category).name ?: "Unknown Category"
            is Product -> (list?.get(index) as Product).title ?: "Unknown Product"
            is Collection -> (list?.get(index) as Collection).title ?: "Unknown Product"
            else -> "Unknown Model"
        }
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}