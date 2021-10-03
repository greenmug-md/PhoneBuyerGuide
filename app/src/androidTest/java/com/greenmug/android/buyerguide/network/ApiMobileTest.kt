package com.greenmug.android.buyerguide.network

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.greenmug.android.buyerguide.di.ApiClient.getRetrofitClient
import com.greenmug.android.buyerguide.utils.Constants

import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

/*
Tests for Api EdgeNetService
 */
@RunWith(AndroidJUnit4::class)
class ApiMobileTest {

    var apiClient = getRetrofitClient(Constants.MOBILE_CLOUD);
    var api = apiClient.create(MobileDataService::class.java)

    @Test
    fun getMobile() {
        runBlocking {
            var s = api.getAllMobile();
            Assert.assertNotNull(s?.body())
        }
    }

    @Test
    fun getMobileImages() {
        runBlocking {
            var s = api.getMobileImages("1")
            Assert.assertNotNull(s?.body())
        }
    }

    @Test
    fun getMobileImagesId() {
        runBlocking {
            var s = api.getMobileImages("10")
            Assert.assertNull(s?.body())
        }
    }
}