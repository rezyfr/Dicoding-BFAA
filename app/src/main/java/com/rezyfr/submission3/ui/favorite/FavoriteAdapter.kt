package com.rezyfr.submission3.ui.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.rezyfr.submission3.R
import com.rezyfr.submission3.data.entity.UserFavoriteEntity
import com.rezyfr.submission3.databinding.ItemUserLayoutBinding

class FavoriteAdapter(
    private val listener: ItemClickListener
) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    private var data: List<UserFavoriteEntity> = listOf()

    fun updateData(list: List<UserFavoriteEntity>) {
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
        fun onUserClicked(view: View, data: UserFavoriteEntity)
    }
}