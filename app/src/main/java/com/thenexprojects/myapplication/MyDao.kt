package com.thenexprojects.myapplication

import androidx.lifecycle.LiveData

interface MyDao {
    fun getAllLocalElements(): LiveData<DataClass>
}