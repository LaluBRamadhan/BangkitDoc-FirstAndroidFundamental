package com.code.submissionawalfundamental.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.code.submissionawalfundamental.data.response.ItemsItem
import com.code.submissionawalfundamental.databinding.ItemRowBinding

class FavoriteAdapter: RecyclerView.Adapter<FavoriteAdapter.ListViewHolder>() {
    private var listItem: List<ItemsItem> = emptyList()
    class ListViewHolder(private val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}