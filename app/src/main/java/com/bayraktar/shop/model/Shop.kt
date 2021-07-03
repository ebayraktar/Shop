package com.bayraktar.shop.model

import android.os.Parcelable
import com.bayraktar.shop.model.base.BaseList
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

//Data classes does not support inheritance but support prohibiting which I don't use.
@Parcelize
data class Shop(
    @SerializedName("id")
    @Expose val id: Int?,

    @SerializedName("name")
    @Expose val name: String?,

    @SerializedName("slug")
    @Expose val slug: String?,

    @SerializedName("definition")
    @Expose val definition: String?,

    @SerializedName("name_updateable")
    @Expose val name_updatable: Boolean?,

    @SerializedName("vacation_mode")
    @Expose val vacationMode: Int?,

    @SerializedName("created_at")
    @Expose val createdAt: String?,

    @SerializedName("shop_payment_id")
    @Expose val shopPaymentId: Int?,

    @SerializedName("product_count")
    @Expose val productCount: Int?,

    @SerializedName("shop_rate")
    @Expose val shopRate: Int?,

    @SerializedName("comment_count")
    @Expose val commentCount: Int?,

    @SerializedName("follower_count")
    @Expose val followerCount: Int?,

    @SerializedName("is_editor_choice")
    @Expose val isEditorChoice: Boolean?,

    @SerializedName("is_following")
    @Expose val isFollowing: Boolean?,

    @SerializedName("cover")
    @Expose val cover: Cover?,

    @SerializedName("share_url")
    @Expose val shareUrl: String?,

    @SerializedName("logo")
    @Expose val logo: Logo?,

    @SerializedName("popular_products")
    @Expose val popularProducts: List<Product>?,

    ) : Parcelable, BaseList