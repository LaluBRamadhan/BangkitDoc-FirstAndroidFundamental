package com.code.submissionawalfundamental.util

import androidx.recyclerview.widget.DiffUtil
import com.code.submissionawalfundamental.database.FavoriteUser

class DiffCallBack(private val oldList: List<FavoriteUser>, private val newList: List<FavoriteUser>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldUser = oldList[oldItemPosition]
        val newUser = newList[newItemPosition]
        return oldUser.name == newUser.name && oldUser.url == newUser.url
    }
}