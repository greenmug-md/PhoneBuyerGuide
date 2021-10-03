package com.greenmug.android.buyerguide.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.greenmug.android.buyerguide.dao.FavouriteMobileDao
import com.greenmug.android.buyerguide.model.Favourites

/*
    Favourite Database
 */
@Database(entities = [Favourites::class], version = 1, exportSchema = false)
abstract class FavouriteMobileDatabase : RoomDatabase() {

    companion object {
        private var favmobileDatabase: FavouriteMobileDatabase? = null

        fun getFavouritesDatabase(context: Context): FavouriteMobileDatabase? {
            if (favmobileDatabase == null) {
                favmobileDatabase =
                    Room.databaseBuilder(context, FavouriteMobileDatabase::class.java, "favourites_db").build()
            }
            return favmobileDatabase
        }
    }

    abstract fun mobileFavouritesDao(): FavouriteMobileDao
}