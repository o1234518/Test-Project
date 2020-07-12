package com.example.githubuserlist.webApiService.webClient

import android.util.Log
import com.example.githubuserlist.model.User
import com.example.githubuserlist.webApiService.ApiService
import com.example.githubuserlist.webApiService.request.GetUserLsitRequest
import com.example.githubuserlist.webApiService.response.GetUserListResponse
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback

class GetUserListWebClient(val request: GetUserLsitRequest, val handleKey: String? = null){

    fun request(): ArrayList<User> {
        val apiService = ApiService.Factory.create()
        var call = apiService.getUserList(request.start_index)
        val user_list = ArrayList<User>()
        call.enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: retrofit2.Response<Any>) {
                Log.e("GetUserListWebClient", "GetUserListWebClient requestHandle onResponse")
                if (response.code() == 200) {
                    val responseString = response.body()
                    val gson = Gson()
                    val tjt = gson.toJsonTree(responseString)
                    val plain_json = tjt.asJsonArray
                    val success_body = JSONArray(plain_json.toString())
                    Log.e("test api", "${success_body.length()}")

                    for (i in 0..success_body.length().minus(1)) {
                        var json = JSONObject(success_body.get(i).toString())
                        val user = User(json.getString("avatar_url"),
                                        json.getString("login"),
                                        json.getBoolean("site_admin"),"","","","")
                        user_list.add(user)
                    }

                    Log.e("test api2", "${user_list.size}")
                    val response = GetUserListResponse(user_list)
                }
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                Log.e("GetUserListWebClient", "GetUserListWebClient requestHandle onFailure")
            }
        })
        return user_list
    }
}