package com.greenmug.android.buyerguide.listeners

import com.greenmug.android.buyerguide.model.Favourites

interface FavouriteListener {
    fun remove(id: Int)
    fun save(favourites: Favourites)
}