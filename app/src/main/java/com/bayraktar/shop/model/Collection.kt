package com.bayraktar.shop.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Collection(
    @SerializedName("id")
    @Expose var id: Number,

    @SerializedName("title")
    @Expose var title: String,

    @SerializedName("definition")
    @Expose var definition: String,

    @SerializedName("start")
    @Expose var start: String,

    @SerializedName("end")
    @Expose var end: String?,

    @SerializedName("share_url")
    @Expose var shareUrl: String,

    @SerializedName("cover")
    @Expose var cover: Cover,

    @SerializedName("logo")
    @Expose var logo: Logo,

    ) : Parcelable