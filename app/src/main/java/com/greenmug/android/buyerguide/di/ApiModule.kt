package com.greenmug.android.buyerguide.di

import com.greenmug.android.buyerguide.network.MobileDataService
import com.greenmug.android.buyerguide.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun getMobilePhoneData(): MobileDataService =
        ApiClient.getRetrofitClient(Constants.MOBILE_CLOUD)
            .create(MobileDataService::class.java)
}