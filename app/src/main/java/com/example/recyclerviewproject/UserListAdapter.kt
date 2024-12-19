package com.example.recyclerviewproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewproject.databinding.UserItemBinding

class UserListAdapter(val onClick: (holder: UserItemHolder) -> Any) :
    RecyclerView.Adapter<UserListAdapter.UserItemHolder>() {

    private var userList: List<UserWithId> = DataList.userList
    private var editingMode = false

    class UserItemHolder(val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var id = -1

        fun bind(user: UserWithId, editingMode: Boolean) = with(binding) {
            id = user.Id
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
            onClick(holder)
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
