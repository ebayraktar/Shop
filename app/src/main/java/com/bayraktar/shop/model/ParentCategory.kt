package com.bayraktar.shop.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ParentCategory(
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

    ) : Parcelable