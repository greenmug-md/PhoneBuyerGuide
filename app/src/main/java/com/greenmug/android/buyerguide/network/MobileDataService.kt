package com.greenmug.android.buyerguide.network

import com.greenmug.android.buyerguide.model.MobileImagesData
import com.greenmug.android.buyerguide.model.MobilePhoneData
import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Path

interface MobileDataService {

    @GET("api/mobiles")
    suspend fun getAllMobile(): Response<MobilePhoneData>

    @GET("api/mobiles/{id}/images")
    suspend fun getMobileImages( @Path("id") mobile_id: String): Response<MobileImagesData>

}