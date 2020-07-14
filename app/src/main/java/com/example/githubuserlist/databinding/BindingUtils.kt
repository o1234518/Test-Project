package com.example.githubuserlist.databinding

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
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

@BindingAdapter("admin")
fun checkAdmin(tv: TextView, user: User) {
    if (!user.type.equals("User")) {
        tv.visibility = View.VISIBLE
        tv.setText(user.type)
    } else {
        tv.visibility = View.GONE
    }
}