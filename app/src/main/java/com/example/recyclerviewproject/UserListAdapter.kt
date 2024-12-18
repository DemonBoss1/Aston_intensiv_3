package com.example.recyclerviewproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewproject.databinding.UserItemBinding

class UserListAdapter : RecyclerView.Adapter<UserListAdapter.UserItemHolder>() {
    private var userList: List<User> = listOf()

    class UserItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = UserItemBinding.bind(itemView)

        fun bind(user: User) = with(binding){
            textViewName.text = user.FirstName+" "+user.LastName
            textViewPhone.text = user.Phone
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return UserItemHolder(view)
    }

    override fun getItemCount() = userList.size

    override fun onBindViewHolder(holder: UserItemHolder, position: Int) {
        holder.bind(userList[position])
    }

    public fun submitList(newList: List<User>){
        userList = newList
    }

}
