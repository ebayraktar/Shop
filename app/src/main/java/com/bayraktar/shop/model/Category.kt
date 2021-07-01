package com.bayraktar.shop.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Category(
    @SerializedName("id")
    @Expose val id: Number,

    @SerializedName("name")
    @Expose val name: String,

    @SerializedName("parent_id")
    @Expose val parentId: Number,

    @SerializedName("order")
    @Expose val order: Number,

    @SerializedName("parent_category")
    @Expose val parentCategory: Category?,

    ) : Parcelable