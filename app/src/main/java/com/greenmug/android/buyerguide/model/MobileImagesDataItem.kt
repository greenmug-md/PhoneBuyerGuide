package com.greenmug.android.buyerguide.model


import com.google.gson.annotations.SerializedName

data class MobileImagesDataItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("mobile_id")
    val mobileId: Int,
    @SerializedName("url")
    val url: String
)