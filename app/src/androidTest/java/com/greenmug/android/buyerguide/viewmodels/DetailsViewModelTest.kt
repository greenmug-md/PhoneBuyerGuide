package com.greenmug.android.buyerguide.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.greenmug.android.buyerguide.di.ApiClient
import com.greenmug.android.buyerguide.network.MobileDataService
import com.greenmug.android.buyerguide.repository.MobileDataRepository
import com.greenmug.android.buyerguide.repository.MobileDataRepositoryImpl
import com.greenmug.android.buyerguide.utils.Constants
import com.greenmug.android.buyerguide.viewmodel.DetailsViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

/*
Tests for Details View  Model
 */
class DetailsViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    var apiClient = ApiClient.getRetrofitClient(Constants.MOBILE_CLOUD);
    var api = apiClient.create(MobileDataService::class.java)
    var detailsViewModel = DetailsViewModel(MobileDataRepositoryImpl(api));


    @Test
    fun getAvailableImages(){
        runBlocking {
            detailsViewModel?.getImages("1")
            delay(3000)
            Assert.assertTrue(detailsViewModel?.imagesData?.value?.isNotEmpty()!!)
        }
    }



}