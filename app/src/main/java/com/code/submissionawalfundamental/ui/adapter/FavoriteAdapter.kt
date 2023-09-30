package com.code.submissionawalfundamental.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.code.submissionawalfundamental.database.FavoriteUser
import com.code.submissionawalfundamental.databinding.ItemRowBinding
import com.code.submissionawalfundamental.ui.activity.DetailProfileActivity
import com.code.submissionawalfundamental.util.DiffCallBack

class FavoriteAdapter: RecyclerView.Adapter<FavoriteAdapter.ListViewHolder>() {

    companion object{
        const val EXTRA_NAME = "extra"
        const val EXTRA_URL = "url"
    }

    private val listUser = ArrayList<FavoriteUser>()
    fun setListUser(listUser: List<FavoriteUser>){
        val diffCallBack = DiffCallBack(this.listUser, listUser)
        val diffResult = DiffUtil.calculateDiff(diffCallBack)
        this.listUser.clear()
        this.listUser.addAll(listUser)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ListViewHolder(private val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(user: FavoriteUser){
            with(binding){
                tvName.text = user.name
                Glide.with(binding.root.context)
                    .load(user.url)
                    .into(imgprofile)

                cardView.setOnClickListener{
                    val intent = Intent(it.context,DetailProfileActivity::class.java)
                    intent.putExtra(EXTRA_NAME,user.name)
                    intent.putExtra(EXTRA_URL, user.url)
                    it.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listUser.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listUser[position])
    }

}