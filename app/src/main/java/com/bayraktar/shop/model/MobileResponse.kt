package com.bayraktar.shop.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MobileResponse(
    @SerializedName("type")
    @Expose val type: String,
    @SerializedName("title")
    @Expose val title: String,

    @SerializedName("featured")
    @Expose val featured: List<Featured>,

    @SerializedName("products")
    @Expose val products : List<Product>,

    @SerializedName("categories")
    @Expose val categories : List<Category>,

    @SerializedName("collections")
    @Expose val collections : List<Collection>,

    @SerializedName("shops")
    @Expose val shops : List<Shop>,

) : Parcelable

/*

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("featured")
    @Expose
    private List<Featured> featured = null;

    @SerializedName("products")
    @Expose
    private List<Product> products = null;

    @SerializedName("categories")
    @Expose
    private List<Category__1> categories = null;

    @SerializedName("collections")
    @Expose
    private List<Collection> collections = null;

    @SerializedName("shops")
    @Expose
    private List<Shop__1> shops = null;

 */