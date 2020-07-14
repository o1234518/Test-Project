package com.example.githubuserlist.viewModel

import android.R
import android.text.TextUtils
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubuserlist.model.User


class DataItemViewModel(dataModel: User) : BaseObservable() {
    private val dataModel: User
    val avatare_url = dataModel.avatare_url
    val login_id = dataModel.login_id
    fun setUp() {
        // perform set up tasks, such as adding listeners
        Log.e("item", "setUp")
    }

    fun tearDown() {
        // perform tear down tasks, such as removing listeners
        Log.e("item", "tearDown")
    }

    @get:Bindable
    val title: String
        get() = if (!TextUtils.isEmpty(login_id)) login_id else ""

    init {
        this.dataModel = dataModel
        Log.e("item", "init")
    }
}