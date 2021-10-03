package com.greenmug.android.buyerguide.repository

import com.greenmug.android.buyerguide.model.MobileImagesData
import com.greenmug.android.buyerguide.model.MobilePhoneData
import retrofit2.Response

interface MobileDataRepository {

    suspend fun getAllMobile(): Response<MobilePhoneData>

    suspend fun getMobileImages(id: String): Response<MobileImagesData>
}