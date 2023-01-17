package com.thenexprojects.myapplication

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private var INSTANCE: Retrofit? = null
    fun getInstance(): Retrofit{
        if(INSTANCE == null){
            INSTANCE = Retrofit.Builder()
                .baseUrl("https://quotable.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return INSTANCE!!
    }

}