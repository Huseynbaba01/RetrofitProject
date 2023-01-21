package com.thenexprojects.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    private lateinit var button: MaterialButton
    private lateinit var textView: TextView
    private lateinit var items: RecyclerView
    private val adapter by lazy { MyAdapter() }
    private lateinit var viewModel: MyViewModel
    private var api = RetrofitHelper.getInstance().create(WebServiceAPI::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.text)
        button = findViewById(R.id.btn)
        items = findViewById(R.id.items)
        items.layoutManager = LinearLayoutManager(this)
        items.adapter = adapter
        viewModel = ViewModelProvider(this)[MyViewModel::class.java]
        observeData()
        button.setOnClickListener {
            //todo (pagination should be used here)
            viewModel.getRemoteData(1)
        }
    }

    private fun observeData() {
        viewModel.elements.observe(this){
            adapter.updateElements(it.results)
            Toast.makeText(this, "Data has been set from remote data source", Toast.LENGTH_SHORT).show()
        }
        viewModel.getLocalData().observe(this){
            adapter.updateElements(it.results)
            Toast.makeText(this, "Data has been set from storage", Toast.LENGTH_SHORT).show()
        }
    }
}