package com.bayraktar.shop.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Medium(
    @SerializedName("width")
    @Expose val width: Number,

    @SerializedName("height")
    @Expose val height: Number,

    @SerializedName("url")
    @Expose val url: String,

    ) : Parcelable