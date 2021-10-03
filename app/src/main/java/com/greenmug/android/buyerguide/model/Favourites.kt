package com.greenmug.android.buyerguide.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/*
Data class for Favourites
 */
@Entity(tableName = "favourites", primaryKeys = ["id"])
data class Favourites(
    @SerializedName("name") var name: String,
    @SerializedName("brand") var brand: String,
    @SerializedName("thumbImageURL") var thumbImageURL: String,
    @SerializedName("price") var price: Double,
    @SerializedName("rating") var rating: Double,
    @SerializedName("id") var id: Int,
    @SerializedName("description") var description: String
): Serializable