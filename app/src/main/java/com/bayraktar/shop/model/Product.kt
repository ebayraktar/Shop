package com.bayraktar.shop.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    @SerializedName("id")
    @Expose val id: Number,

    @SerializedName("code")
    @Expose val code: String?,

    @SerializedName("title")
    @Expose val title: String,

    @SerializedName("slug")
    @Expose val slug: String,

    @SerializedName("definition")
    @Expose val definition: String,

    @SerializedName("old_price")
    @Expose val oldPrice: Number,

    @SerializedName("price")
    @Expose val price: Number,

    @SerializedName("stock")
    @Expose val stock: Number,

    @SerializedName("max_installment")
    @Expose val maxInstallment: Number?,

    @SerializedName("commission_rate")
    @Expose val commission_rate: Number,

    @SerializedName("cargo_time")
    @Expose val cargoTime: Number,

    @SerializedName("is_cargo_free")
    @Expose val isCargoFree: Boolean,

    @SerializedName("is_new")
    @Expose val isNew: Boolean,

    @SerializedName("reject_reason")
    @Expose val rejectReason: String?,

    @SerializedName("category_id")
    @Expose val categoryId: Number,

    @SerializedName("difference")
    @Expose val difference: String,

    @SerializedName("is_editor_choice")
    @Expose val isEditorChoice: Boolean,

    @SerializedName("comment_count")
    @Expose val comment_count: Number,

    @SerializedName("is_owner")
    @Expose val isOwner: Boolean,

    @SerializedName("is_approved")
    @Expose val isApproved: Boolean,

    @SerializedName("is_active")
    @Expose val isActive: Boolean,

    @SerializedName("share_url")
    @Expose val shareUrl: String,

    @SerializedName("is_liked")
    @Expose val isLiked: Boolean,

    @SerializedName("like_count")
    @Expose val likeCount: Number,

    @SerializedName("shop")
    @Expose val shop: Shop,

    @SerializedName("category")
    @Expose val category: Category,

    @SerializedName("images")
    @Expose val images: List<Image>,

    
    ) : Parcelable

/*

    @SerializedName("category")
    @Expose
    private Category category;

    @SerializedName("images")
    @Expose
    private List<Image> images = null;

 */