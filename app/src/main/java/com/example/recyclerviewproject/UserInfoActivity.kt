package com.example.recyclerviewproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclerviewproject.databinding.ActivityUserInfoBinding

class UserInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            buttonOk.setOnClickListener {
                val Id = intent.getIntExtra("Id", -1)
                if (Id == -1) return@setOnClickListener
                val FirstName = firstNameTextView.text.toString()
                val LastName = lastNameTextView.text.toString()
                val Phone = phoneTextView.text.toString()
                val user = UserWithId(
                    Id = Id,
                    FirstName = FirstName,
                    LastName = LastName,
                    Phone = Phone
                )
                DataList.addUser(user)

                val intent = Intent(this@UserInfoActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}