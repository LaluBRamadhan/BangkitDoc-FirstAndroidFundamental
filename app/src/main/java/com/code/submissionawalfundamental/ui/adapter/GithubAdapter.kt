package com.code.submissionawalfundamental.ui.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.code.submissionawalfundamental.data.response.ItemsItem
import com.code.submissionawalfundamental.databinding.ItemRowBinding
import com.code.submissionawalfundamental.ui.activity.DetailProfileActivity

class GithubAdapter: RecyclerView.Adapter<GithubAdapter.ListViewHolder>() {

    private var listItem: List<ItemsItem> = emptyList()
    companion object{
        const val EXTRA_NAME = "extra"
    }

    class ListViewHolder(private val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: ItemsItem){
            //nampilin di recyclerview
            binding.tvName.text = item.login
            Glide.with(binding.root.context)
                .load(item.avatarUrl)
                .into(binding.imgprofile)

            //event ketika salah satu item di klik
            itemView.setOnClickListener{
                val intent = Intent(itemView.context, DetailProfileActivity::class.java)
                intent.putExtra(EXTRA_NAME, item.login)
                Log.e("Login", "bind: ${item.login}", )
                itemView.context.startActivity(intent)
            }
        }

    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listItem.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
       val user = listItem[position]
        holder.bind(user)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submit(user: List<ItemsItem>){
        listItem = user
        notifyDataSetChanged()
    }

}