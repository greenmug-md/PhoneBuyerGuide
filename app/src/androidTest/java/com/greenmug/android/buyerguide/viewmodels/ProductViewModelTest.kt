package com.greenmug.android.buyerguide.viewmodels

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.greenmug.android.buyerguide.dao.FavouriteMobileDao
import com.greenmug.android.buyerguide.database.FavouriteMobileDatabase
import com.greenmug.android.buyerguide.di.ApiClient
import com.greenmug.android.buyerguide.model.Favourites
import com.greenmug.android.buyerguide.network.MobileDataService
import com.greenmug.android.buyerguide.repository.FavouritesRepository
import com.greenmug.android.buyerguide.repository.MobileDataRepositoryImpl
import com.greenmug.android.buyerguide.utils.Constants
import com.greenmug.android.buyerguide.viewmodel.FavouriteViewModel
import com.greenmug.android.buyerguide.viewmodel.ProductViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/*
Tests for Details View  Model
 */
class ProductViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    var apiClient = ApiClient.getRetrofitClient(Constants.MOBILE_CLOUD);
    var api = apiClient.create(MobileDataService::class.java)
    var productViewModel = ProductViewModel(application = ApplicationProvider.getApplicationContext(),
        MobileDataRepositoryImpl(api),
        FavouritesRepository(ApplicationProvider.getApplicationContext()));

    private lateinit var db: FavouriteMobileDatabase
    private lateinit var dbdao: FavouriteMobileDao

    @Before
    public  fun setUp() {
        val application = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(application, FavouriteMobileDatabase::class.java).allowMainThreadQueries().build() //only for testing
        dbdao = db.mobileFavouritesDao()
    }

    @Test
    fun getContentProductViewModel() {
        val favourite = Favourites("Flip", "Samsung", "https:://url", 100.00, 4.8 ,1, "Flip Phone")
        dbdao.addToFavourites(favourite).blockingAwait()
        runBlocking {
            productViewModel?.getContent(1)
            delay(3000)
            Assert.assertTrue(productViewModel?._productList?.value?.data?.isNotEmpty()!!)
        }
    }




}