package com.example.githubuserlist.viewModel

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.githubuserlist.BR
import com.example.githubuserlist.adapter.DataAdapter
import com.example.githubuserlist.model.User
import java.util.*

class DataViewModel : BaseObservable() {
    @get:Bindable
    val adapter: DataAdapter
    private val data: MutableList<User>
    fun setUp(list: List<User>) {
        // perform set up tasks, such as adding listeners, data population, etc.
        data.addAll(list)
        Log.e("testViewModel1", "${list.size} , ${data.size}")
        populateData()
    }

    fun tearDown() {
        // perform tear down tasks, such as removing listeners
    }

    @Bindable
    fun getData(): List<User> {
        return data
    }

    private fun populateData() {
        notifyPropertyChanged(BR.data)
    }

    companion object {
        private const val TAG = "DataViewModel"
    }

    init {
        data = ArrayList<User>()
        adapter = DataAdapter()
    }
}