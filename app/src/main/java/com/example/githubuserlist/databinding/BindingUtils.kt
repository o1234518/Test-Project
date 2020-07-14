package com.example.githubuserlist.databinding

import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.githubuserlist.model.User

@BindingAdapter("avatar")
fun loadImage(imageView: ImageView, url: String) {
    Glide.with(imageView)
        .load(url)
        .into(imageView)
}

@BindingAdapter("loading")
fun setButton(btn: Button, list: List<User>) {
    if (list.size >= 100) {
        btn.setText("Data collection size is to 100")
        btn.isEnabled = false
    }
}