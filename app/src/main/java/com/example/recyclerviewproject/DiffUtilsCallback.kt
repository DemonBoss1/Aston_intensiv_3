package com.example.recyclerviewproject

import androidx.recyclerview.widget.DiffUtil

class DiffUtilsCallback(
    private val oldList: List<UserWithId>,
    private val newList: List<UserWithId>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].Id == newList[newItemPosition].Id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}