package com.bayraktar.shop.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Logo(
    @SerializedName("width")
    @Expose val width: Int?,

    @SerializedName("height")
    @Expose val height: Int?,

    @SerializedName("url")
    @Expose val url: String?,

    @SerializedName("medium")
    @Expose val medium: Medium?,

    @SerializedName("thumbnail")
    @Expose val thumbnail: Thumbnail?,

    ) : Parcelable