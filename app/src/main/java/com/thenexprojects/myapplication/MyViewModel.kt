package com.thenexprojects.myapplication

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel: ViewModel() {
    private var myRepository = MyRepository()
    var elements: MutableLiveData<DataClass> = MutableLiveData()

    fun getRemoteData(page: Int){
        val coroutineExceptionHandler = CoroutineExceptionHandler{ context, t->
            Log.d("MyTagHere", "onCreate: ${t.message}")
        }
        viewModelScope.launch(coroutineExceptionHandler + Dispatchers.IO) {
            val response = myRepository.getData(page)
            if(response.isSuccessful && response.code() == 200){
                elements.postValue(response.body())
            }
        }
    }
    fun getLocalData(): LiveData<DataClass>{
        return myRepository.getLocalElements()
    }

}