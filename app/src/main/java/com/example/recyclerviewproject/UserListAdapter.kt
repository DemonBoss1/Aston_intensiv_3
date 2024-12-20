package com.example.recyclerviewproject

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewproject.UserListAdapter.UserItemHolder
import com.example.recyclerviewproject.databinding.UserItemBinding

class UserListAdapter(
    val userListAdapterListener: UserListAdapterListener
) : RecyclerView.Adapter<UserItemHolder>() {
    private lateinit var diffResult: DiffUtil.DiffResult

    private val userList: List<UserWithId> = DataList.userList
    private val changesItem by lazy {
        userList.map {
            false
        }.toMutableList()
    }
    private var editingMode = false

    class UserItemHolder(val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var savedUser: UserWithId? = null

        fun bind(user: UserWithId) = with(binding) {
            savedUser = user
            textViewName.text = user.FirstName + " " + user.LastName
            textViewPhone.text = user.Phone
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = UserItemBinding.inflate(inflater, parent, false)
        val holder = UserItemHolder(binding)
        binding.root.setOnClickListener {
            if (editingMode) {
                userListAdapterListener.changeItem(holder)
                val position = holder.position
                changesItem[position] = !changesItem[position]
            } else userListAdapterListener.onClickItem(holder)
        }
        return holder
    }

    override fun getItemCount() = userList.size

    override fun onBindViewHolder(holder: UserItemHolder, position: Int) {
        holder.bind(userList[position])
        with(holder.binding){
            checkBox.isChecked = changesItem[position]
            if(checkBox.isChecked) root.setBackgroundColor(Color.LTGRAY)
            else root.setBackgroundColor(Color.WHITE)
        }
    }

    fun enableEditingMode() {
        editingMode = true
    }

    fun turnOfEditingMode() {
        editingMode = false
        resetChange()
    }

    fun resetChange() {
        changesItem.fill(false)
    }

    fun deleteItems(items: MutableList<UserWithId>) {
        val oldList = userList.map { it.copy() }
        changesItem.removeAll { it }
        DataList.removeAll(items)

        diffResult = DiffUtil.calculateDiff(DiffUtilsCallback(oldList, userList))
        diffResult.dispatchUpdatesTo(this)
    }
}

interface UserListAdapterListener {
    fun onClickItem(holder: UserItemHolder)
    fun changeItem(holder: UserItemHolder)
}
