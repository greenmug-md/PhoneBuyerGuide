package com.greenmug.android.buyerguide.adapter

import com.greenmug.android.buyerguide.fragments.FavouriteFragment
import com.greenmug.android.buyerguide.fragments.ProductFragment

interface ResourceStore {
    companion object {
        val tabList = listOf(
            "All", "Favourites"
        )
        val pagerFragments = listOf(
            ProductFragment.create(), FavouriteFragment.create(),
        )
    }
}