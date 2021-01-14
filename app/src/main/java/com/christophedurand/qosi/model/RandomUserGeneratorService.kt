package com.christophedurand.qosi.model

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET


interface RandomUserGeneratorService {
    @GET("api/?results=10")
    suspend fun getUsersList(): Response<ResponseBody>
}