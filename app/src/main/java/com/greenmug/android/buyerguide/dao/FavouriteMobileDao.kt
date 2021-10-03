package com.greenmug.android.buyerguide.dao

import androidx.annotation.VisibleForTesting
import androidx.room.*
import com.greenmug.android.buyerguide.model.Favourites
import com.greenmug.android.buyerguide.model.MobilePhoneData
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface FavouriteMobileDao {

    @Query("SELECT * from favourites")
    suspend fun getAllFavourites(): List<Favourites>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addToFavourites(favourites: Favourites): Completable

    @Query("DELETE FROM favourites WHERE id = :id")
    fun removeFavourites(id : Int?): Completable

    @VisibleForTesting
    @Query("SELECT * FROM favourites")
    fun selectAll(): Flowable<List<Favourites>>
}