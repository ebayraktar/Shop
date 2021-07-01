package com.bayraktar.shop.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Featured(
    @SerializedName("id")
    @Expose val id: String,

    @SerializedName("type")
    @Expose val type: String,

    @SerializedName("cover")
    @Expose val cover: Cover,

    @SerializedName("title")
    @Expose val title: String,

    @SerializedName("sub_title")
    @Expose val subTitle: String,


    ) : Parcelable
