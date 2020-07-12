package com.example.githubuserlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.githubuserlist.webApiService.request.GetUserLsitRequest
import com.example.githubuserlist.webApiService.webClient.GetUserListWebClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener(View.OnClickListener {
            val getUserListClient = GetUserListWebClient(
                GetUserLsitRequest(0),
                null
            )
            var users = getUserListClient.request()
        })
    }
}
