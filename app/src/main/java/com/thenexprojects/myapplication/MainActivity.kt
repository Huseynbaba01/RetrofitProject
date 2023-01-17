package com.thenexprojects.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private lateinit var button: MaterialButton
    private lateinit var textView: TextView
    private lateinit var items: RecyclerView
    private val adapter by lazy { MyAdapter() }
    private var _mLiveData = MutableLiveData<DataClass>()
    var liveData = _mLiveData
    private var api = RetrofitHelper.getInstance().create(WebServiceAPI::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.text)
        button = findViewById(R.id.btn)
        items = findViewById(R.id.items)
        items.layoutManager = LinearLayoutManager(this)
        items.adapter = adapter
        observeData()

        button.setOnClickListener {
            val coroutineExceptionHandler = CoroutineExceptionHandler{ context, t->
                Log.d("MyTagHere", "onCreate: ${t.message}")
            }
            lifecycleScope.launch(coroutineExceptionHandler) {
                val data = api.getAllData(1)
                if(data.isSuccessful){
                    _mLiveData.postValue(data.body())
                    try {
                        adapter.updateElements(data.body()!!.results)
                    }catch (e: Exception){
                        e.printStackTrace()
                    }
                }
                else if(data.code() == 401){
                    //refresh token request
                }
            }
        }
    }

    private fun observeData() {
        liveData.observe(this){
            adapter.updateElements(it.results)
        }
    }
}