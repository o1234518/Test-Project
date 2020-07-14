package com.example.githubuserlist.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.githubuserlist.R
import com.example.githubuserlist.databinding.UserListItemBinding
import com.example.githubuserlist.model.User
import com.example.githubuserlist.viewModel.DataItemViewModel
import io.reactivex.annotations.Nullable
import java.util.*

class DataAdapter :
    RecyclerView.Adapter<DataAdapter.DataViewHolder?>() {

    private val data: MutableList<User>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(
            R.layout.user_list_item,
            FrameLayout(parent.context), false
        )
        return DataViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val dataModel: User = data[position]
        holder.setViewModel(DataItemViewModel(dataModel))
    }

    override fun getItemCount(): Int {
        return this.data.size
    }

    override fun onViewAttachedToWindow(holder: DataViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.bind()
    }

    override fun onViewDetachedFromWindow(holder: DataViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.unbind()
    }

    fun updateData(@Nullable data: List<User>?) {
        if (data == null || data.isEmpty()) {
            this.data.clear()
        } else {
            for (i in 0 .. (data.size-1)) {
                if (!this.data.contains(data[i]) && (this.data.size <= 100)) {
                    this.data.add(data[i])
                }
            }
        }
        notifyDataSetChanged()
    }

    /* package */
    inner class DataViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        /* package */
        var binding: UserListItemBinding? = null

        /* package */
        fun bind() {
            if (binding == null) {
                binding = DataBindingUtil.bind<UserListItemBinding>(itemView)
            }
        }

        /* package */
        fun unbind() {
            if (binding != null) {
                binding!!.unbind() // Don't forget to unbind
            }
        }

        /* package */
        fun setViewModel(viewModel: DataItemViewModel?) {
            if (binding != null) {
                binding!!.setViewModel(viewModel)
            }
        }

        /* package */
        init {
            bind()
        }
    }

    init {
        data = ArrayList<User>()
    }
}