package com.greenmug.android.buyerguide.repository

import android.app.Application
import android.content.Context
import com.greenmug.android.buyerguide.database.FavouriteMobileDatabase
import com.greenmug.android.buyerguide.model.Favourites
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FavouritesRepository(private val context: Context) {

    var favouriteShowDatabase: FavouriteMobileDatabase?=null
    init {
        favouriteShowDatabase = FavouriteMobileDatabase.getFavouritesDatabase(context)
    }

    fun remove(favourites: Favourites) {
        favouriteShowDatabase?.mobileFavouritesDao()?.removeFavourites(favourites?.id)?.subscribeOn(
            Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe {
            }
    }

    fun save(favourites: Favourites) {
        favouriteShowDatabase?.mobileFavouritesDao()?.addToFavourites(favourites)?.subscribeOn(
            Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe {
            }
    }
}