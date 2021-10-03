package com.greenmug.android.buyerguide.repository

import com.greenmug.android.buyerguide.model.MobileImagesData
import com.greenmug.android.buyerguide.model.MobilePhoneData
import com.greenmug.android.buyerguide.network.MobileDataService
import retrofit2.Response

class MobileDataRepositoryImpl(private val mobileDataService: MobileDataService) : MobileDataRepository{
    override suspend fun getAllMobile(): Response<MobilePhoneData> {
        val response = mobileDataService.getAllMobile()
        return response;
    }

    override suspend fun getMobileImages(id: String): Response<MobileImagesData> {
        val response = mobileDataService.getMobileImages(id)
        return response;
    }
}