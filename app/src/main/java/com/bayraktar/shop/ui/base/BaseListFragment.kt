package com.bayraktar.shop.ui.base

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bayraktar.shop.ARG_LIST
import com.bayraktar.shop.ARG_TITLE
import com.bayraktar.shop.R
import com.bayraktar.shop.model.base.BaseList


/**
 * A simple [Fragment] subclass.
 */
abstract class BaseListFragment : Fragment() {
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
}