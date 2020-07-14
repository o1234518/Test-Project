package com.example.githubuserlist.databinding

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("avatar")
fun loadImage(imageView: ImageView, url: String) {
    Log.e("item", "${url}")
    Glide.with(imageView)
        .load(url)
        .into(imageView)
}