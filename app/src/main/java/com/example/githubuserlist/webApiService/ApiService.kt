package com.example.githubuserlist.webApiService

import android.content.Context
import android.util.Log
import com.example.githubuserlist.MainActivity
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    fun getUserList(@Query("since") since: Int): Call<Any>

    private class NormalOkHttpInterceptor() : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()
            val newRequest = originalRequest.newBuilder()
//                .header("Accept-Language",  Locale.getDefault().language+"-"+Locale.getDefault().country)
                .header("Accept-Language",  "zh-TW")
                .build()

            return chain.proceed(newRequest)
        }
    }


    object Factory {
        val httpClient = OkHttpClient.Builder()
        var retrofit_directAccess: Retrofit? = null
        var apiService: ApiService?=null
        fun create(client: OkHttpClient? = null): ApiService {
            val web_api_url  = "https://api.github.com/"
            var retrofit: Retrofit? =null
            if(client!=null)
            {
                Log.e("NGDRApiService", "Factory getUserInfoRequest")
                retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .baseUrl(web_api_url)
                    .client(client)
                    .build()
                return retrofit!!.create<ApiService>(
                    ApiService::class.java)
            }else {
                if(retrofit_directAccess == null) {
                    val new_client = OkHttpClient.Builder().addInterceptor(NormalOkHttpInterceptor()).build()
                    retrofit_directAccess = Retrofit.Builder()
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .baseUrl(web_api_url)
                        .client(new_client)
                        .build()
                    retrofit =
                        retrofit_directAccess

                    apiService = retrofit!!.create<ApiService>(
                        ApiService::class.java)
                }
                return apiService!!
            }
        }
    }
}