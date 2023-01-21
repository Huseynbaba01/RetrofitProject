package com.thenexprojects.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MyRoomDatabase {
    fun myDao(): MyDao{
        return object: MyDao{
            //just to suppress problems
            override fun getAllLocalElements(): LiveData<DataClass> {
                return MutableLiveData()
            }
        }
    }
}