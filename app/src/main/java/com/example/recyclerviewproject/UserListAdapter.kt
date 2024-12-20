package com.example.recyclerviewproject

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

        fun bind(user: UserWithId, editingMode: Boolean) = with(binding) {
            savedUser = user
            textViewName.text = user.FirstName + " " + user.LastName
            textViewPhone.text = user.Phone
            if (editingMode) checkBox.visibility = View.VISIBLE
            else checkBox.visibility = View.GONE
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
        holder.bind(userList[position], editingMode)
        holder.binding.checkBox.isChecked = changesItem[position]
    }

    fun enableEditingMode() {
        editingMode = true
        notifyDataSetChanged()
    }

    fun turnOfEditingMode() {
        editingMode = false
        //notifyDataSetChanged()
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

        //notifyDataSetChanged()
    }
}

interface UserListAdapterListener {
    fun onClickItem(holder: UserItemHolder)
    fun changeItem(holder: UserItemHolder)
}
