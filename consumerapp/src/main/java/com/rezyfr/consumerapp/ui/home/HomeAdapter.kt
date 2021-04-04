package com.rezyfr.consumerapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.rezyfr.consumerapp.R
import com.rezyfr.consumerapp.data.response.UserDetailResponse
import com.rezyfr.consumerapp.databinding.ItemUserLayoutBinding

class HomeAdapter(
    private val listener: ItemClickListener
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private var data: List<UserDetailResponse> = listOf()

    fun updateData(list: List<UserDetailResponse>) {
        this.data = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_user_layout,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = data[position]

        holder.binding.favEntity = data
        holder.itemView.setOnClickListener {
            listener.onUserClicked(it, data)
        }
    }

    inner class ViewHolder(val binding: ItemUserLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface ItemClickListener {
        fun onUserClicked(view: View, data: UserDetailResponse)
    }
}