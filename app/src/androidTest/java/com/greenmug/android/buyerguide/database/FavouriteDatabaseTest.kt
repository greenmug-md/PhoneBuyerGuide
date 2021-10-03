package com.greenmug.android.buyerguide.database

import androidx.test.ext.junit.runners.AndroidJUnit4
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider

import com.greenmug.android.buyerguide.model.Favourites
import com.greenmug.android.buyerguide.dao.FavouriteMobileDao
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FavouriteDatabaseTest : TestCase() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var db: FavouriteMobileDatabase
    private lateinit var dbdao: FavouriteMobileDao

    @Before
    public override fun setUp() {
        val application = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(application,FavouriteMobileDatabase::class.java).allowMainThreadQueries().build() //only for testing
        dbdao = db.mobileFavouritesDao()
    }

    @Test
    fun writeAndReadFavourites() {
        val favourite = Favourites("Flip", "Samsung", "https:://url", 100.00, 4.8 ,1, "Flip Phone")
        dbdao.addToFavourites(favourite).blockingAwait()
        dbdao.selectAll().test().assertValue { list ->
            list.isNotEmpty()
        }
    }


    @Test
    fun writeAndDeleteBookmarks() {
        val channel = Favourites("Flip", "Samsung", "https:://url", 100.00, 4.8 ,1, "Flip Phone")
        dbdao.addToFavourites(channel).blockingAwait()
        dbdao.removeFavourites(channel?.id).blockingAwait()
        dbdao.selectAll().test().assertValue { list ->
            list.isEmpty()
        }
    }

    @After
    fun closeDB() {
        db.clearAllTables()
        db.close()
    }
}