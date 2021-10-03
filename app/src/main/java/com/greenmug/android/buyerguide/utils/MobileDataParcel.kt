package com.greenmug.android.buyerguide.utils

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class MobileDataParcel(val id: Int, val brand: String,
                            val name: String, val price: Double,
                            val thumbImageURL: String, val description: String,
                            val rating: Double) : Parcelable