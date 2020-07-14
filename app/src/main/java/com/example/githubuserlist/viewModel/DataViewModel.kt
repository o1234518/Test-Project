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
        for (i in 0 .. (list.size-1)) {
//            Log.e("check data", "${data.contains(list[i])}")
            if (!data.contains(list[i]) && (data.size <= 100)) {
//                Log.e("check data", "add data")
                data.add(list[i])
            }
        }
//        Log.e("check data", "data size ${data.size}")
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