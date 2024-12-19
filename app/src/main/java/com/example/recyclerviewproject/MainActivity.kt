package com.example.recyclerviewproject

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewproject.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var adapter = UserListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        create()

        binding.apply {
            userRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            userRecyclerView.adapter = adapter
        }
    }

    fun create() {
        if (DataList.listIsEmpty()) {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.randomdatatools.ru")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val dataApi = retrofit.create(DataApi::class.java)
            CoroutineScope(Dispatchers.IO).launch {
                val list = dataApi.getData()
                val userList = list.mapIndexed { index, user ->
                    UserWithId(
                        Id = index,
                        FirstName = user.FirstName,
                        LastName = user.LastName,
                        Phone = user.Phone
                    )
                }
                DataList.updateList(userList)
                runOnUiThread {
                    binding.apply {
                        adapter.notifyDataSetChanged()
                    }
                }

            }
        }
    }
}