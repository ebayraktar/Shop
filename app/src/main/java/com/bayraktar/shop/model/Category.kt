package com.bayraktar.shop.model

import android.os.Parcelable
import com.bayraktar.shop.model.base.BaseList
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Category(
    @SerializedName("id")
    @Expose val id: Int?,

    @SerializedName("name")
    @Expose val name: String?,

    @SerializedName("parent_id")
    @Expose val parentId: Int?,

    @SerializedName("order")
    @Expose val order: Int?,

    @SerializedName("parent_category")
    @Expose val parentCategory: ParentCategory?,

    @SerializedName("logo")
    @Expose val logo: Logo?,

    @SerializedName("cover")
    @Expose val cover: Cover?,
) : Parcelable, BaseList