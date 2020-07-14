package com.example.githubuserlist.model

data class User(
    val id: Int,
    val avatare_url: String,
    val login_id: String,
    val site_admin: Boolean,
    val name: String,
    val location: String,
    val blog: String,
    val bio: String)