package com.example.recyclerviewproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewproject.UserListAdapter.UserItemHolder
import com.example.recyclerviewproject.databinding.UserItemBinding

class UserListAdapter(val userListAdapterListener:UserListAdapterListener) :
    RecyclerView.Adapter<UserItemHolder>() {

    private var userList: List<UserWithId> = DataList.userList
    private var editingMode = false

    class UserItemHolder(val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var savedUser: UserWithId? = null

        fun bind(user: UserWithId, editingMode: Boolean) = with(binding) {
            savedUser = user
            textViewName.text = user.FirstName + " " + user.LastName
            textViewPhone.text = user.Phone
            if(editingMode) checkBox.visibility = View.VISIBLE
            else checkBox.visibility = View.GONE
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = UserItemBinding.inflate(inflater, parent, false)
        val holder = UserItemHolder(binding)
        binding.root.setOnClickListener {
            if(editingMode) userListAdapterListener.changeItem(holder)
            else userListAdapterListener.onClickItem(holder)
        }
        return holder
    }

    override fun getItemCount() = userList.size

    override fun onBindViewHolder(holder: UserItemHolder, position: Int) {
        holder.bind(userList[position], editingMode)
    }

    fun enableEditingMode(){
        editingMode = true
        notifyDataSetChanged()
    }
    fun turnOfEditingMode(){
        editingMode = false
        notifyDataSetChanged()
    }
}

interface  UserListAdapterListener{
    fun onClickItem(holder: UserItemHolder)
    fun changeItem(holder: UserItemHolder)
}
