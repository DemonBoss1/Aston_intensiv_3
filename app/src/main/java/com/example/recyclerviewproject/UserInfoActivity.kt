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
            val Id = intent.getIntExtra("Id", -1)
            var user = DataList.userList.find {
                Id == it.Id
            }
            if (user != null) {
                firstNameTextView.setText(user.FirstName)
                lastNameTextView.setText(user.LastName)
                phoneTextView.setText(user.Phone)
            }
            buttonOk.setOnClickListener {
                val FirstName = firstNameTextView.text.toString()
                val LastName = lastNameTextView.text.toString()
                val Phone = phoneTextView.text.toString()
                if (user == null) {
                    user = UserWithId(
                        Id = Id,
                        FirstName = FirstName,
                        LastName = LastName,
                        Phone = Phone
                    )
                    DataList.addUser(user!!)
                }
                else{
                    user!!.FirstName = FirstName
                    user!!.LastName = LastName
                    user!!.Phone = Phone
                }

                val intent = Intent(this@UserInfoActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}