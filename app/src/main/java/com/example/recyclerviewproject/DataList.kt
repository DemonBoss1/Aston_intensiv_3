package com.example.recyclerviewproject

class DataList {
    private var userList: MutableList<UserWithId> = mutableListOf()

    companion object {
        private val dataList by lazy {
            DataList()
        }
        val userList: List<UserWithId> = dataList.userList

        fun listIsEmpty() = dataList.userList.isEmpty()

        fun updateList(newList: List<UserWithId>) = with(dataList) {
            if (userList.isEmpty()) userList += newList
        }
        fun addUser(user: UserWithId) {
            dataList.userList.add(user)
        }
    }
}