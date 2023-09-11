package com.code.submissionawalfundamental.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.code.submissionawalfundamental.data.response.ItemsItem
import com.code.submissionawalfundamental.databinding.ItemRowBinding

class FollowerAdapter: RecyclerView.Adapter<FollowerAdapter.ListViewHolder>() {

    private var listItem: List<ItemsItem> = emptyList()

    class ListViewHolder (private val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: ItemsItem) {
            binding.tvName.text = item.login
            Glide.with(binding.root.context)
                .load(item.avatarUrl)
                .into(binding.imgprofile)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = listItem[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = listItem.size


}