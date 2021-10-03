package com.greenmug.android.buyerguide.model


import com.google.gson.annotations.SerializedName

data class MobilePhoneDataItem(
    @SerializedName("brand")
    val brand: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("thumbImageURL")
    val thumbImageURL: String,
    var favs: Boolean
)