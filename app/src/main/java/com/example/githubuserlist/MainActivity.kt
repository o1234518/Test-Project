package com.example.githubuserlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubuserlist.databinding.ActivityMainBinding
import com.example.githubuserlist.databinding.AppDataBindingComponent
import com.example.githubuserlist.databinding.UserListItemBinding
import com.example.githubuserlist.model.User
import com.example.githubuserlist.viewModel.DataItemViewModel
import com.example.githubuserlist.viewModel.DataViewModel
import com.example.githubuserlist.webApiService.request.GetUserLsitRequest
import com.example.githubuserlist.webApiService.webClient.GetUserListWebClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var dataViewModel: DataViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

//        val getUserListClient = GetUserListWebClient(
//            GetUserLsitRequest(0),
//            null
//        )
//        var users = getUserListClient.request()
        DataBindingUtil.setDefaultComponent(AppDataBindingComponent())
        val view = bind()
        initRecycleView(view)

        button.setOnClickListener(View.OnClickListener {
            if (!dataViewModel.getDownloadStatus()) {
                var list = dataViewModel.getData()
                var user = list[list.size-1]
                val getUserListClient = GetUserListWebClient(
                    GetUserLsitRequest(user.id),
                    null,
                    dataViewModel
                )
                getUserListClient.request()
            }
        })

        val getUserListClient = GetUserListWebClient(
            GetUserLsitRequest(0),
            null,
            dataViewModel
        )
        getUserListClient.request()

//        val getUserListClient = GetUserListWebClient(
//            GetUserLsitRequest(0),
//            null
//        )
//        data.addAll(getUserListClient.request())
    }

    fun bind(): View{
        var binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        dataViewModel = DataViewModel()
        binding.viewModel = dataViewModel
        return binding.root
    }

    fun initRecycleView(view: View) {
        var recycleView: RecyclerView = view.findViewById<RecyclerView>(R.id.data_recycle_view)
        recycleView.layoutManager = LinearLayoutManager(recycleView.context)
        recycleView.addItemDecoration(DividerItemDecoration(recycleView.context,LinearLayoutManager.HORIZONTAL))
    }
}
