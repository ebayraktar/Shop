package com.bayraktar.shop.model

import android.os.Parcelable
import com.bayraktar.shop.model.base.BaseCover
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Logo(
    @SerializedName("width")
    @Expose override val width: Number,

    @SerializedName("height")
    @Expose override val height: Number,

    @SerializedName("url")
    @Expose override val url: String,

    @SerializedName("medium")
    @Expose override val medium: Medium,

    @SerializedName("thumbnail")
    @Expose override val thumbnail: Thumbnail,

    ) : BaseCover(), Parcelable