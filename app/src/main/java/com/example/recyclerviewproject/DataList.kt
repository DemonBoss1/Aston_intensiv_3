package com.example.recyclerviewproject

class DataList {
    private var userList: MutableList<UserWithId> = mutableListOf()

    companion object {
        private var dataList = lazy {
            DataList()
        }

        fun listIsEmpty() = dataList.value.userList.isEmpty()

        fun updateList(newList: List<UserWithId>) = with(dataList.value) {
            if (userList.isEmpty()) userList += newList
        }

        fun getList() = dataList.value.userList
    }
}