package com.greenmug.android.buyerguide.di

import android.content.Context
import com.greenmug.android.buyerguide.network.MobileDataService
import com.greenmug.android.buyerguide.repository.FavouritesRepository
import com.greenmug.android.buyerguide.repository.MobileDataRepository
import com.greenmug.android.buyerguide.repository.MobileDataRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun getMobileAll(mobileCloudApi: MobileDataService): MobileDataRepository {
        return MobileDataRepositoryImpl(mobileCloudApi)
    }

    @Provides
    @Singleton
    fun getFavouriteRepository(@ApplicationContext context: Context): FavouritesRepository {
        return FavouritesRepository(context)
    }
}