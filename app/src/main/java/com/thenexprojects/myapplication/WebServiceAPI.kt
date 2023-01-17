package com.thenexprojects.myapplication

import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface WebServiceAPI {
    @GET("quotes")
    suspend fun getAllData(@Query("page") page: Int): Response<DataClass>
}
