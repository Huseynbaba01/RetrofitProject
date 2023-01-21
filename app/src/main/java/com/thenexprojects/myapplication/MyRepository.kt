package com.thenexprojects.myapplication

import androidx.lifecycle.LiveData
import retrofit2.Response

class MyRepository {
    val api = RetrofitHelper.getInstance().create(WebServiceAPI::class.java)
    val room = MyRoomDatabase()

    suspend fun getData(page: Int): Response<DataClass> {
        return api.getAllData(page)
    }

    fun getLocalElements(): LiveData<DataClass>{
        return room.myDao().getAllLocalElements()
    }
}