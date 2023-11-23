package com.pp.a4rent.screens

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity

import com.google.gson.Gson
import com.pp.a4rent.ProfileActivity
import com.pp.a4rent.MainActivity
import com.pp.a4rent.databinding.ActivityLoginBinding
import com.pp.a4rent.models.User

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("MY_APP_PREFS", Context.MODE_PRIVATE)

        binding.loginButton.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()

            val userJson = sharedPreferences.getString(email, "")
            if (userJson != "") {
                val gson = Gson()
                val user = gson.fromJson(userJson, User::class.java)
                if (user.password == password) {
                    Toast.makeText(this, "Logged in successfully", Toast.LENGTH_SHORT).show()

                    // Redirect users to different profile pages based on their role
                    if (user.role == "Landlord"){

                        // If a user is Landlord
                        val intent = Intent(this, ProfileActivity::class.java)
                        intent.putExtra("extra_userObj", user)
                        startActivity(intent)
                    } else {

                        // If a user is Tenant
                        val intent = Intent(this, TenantAccountActivity::class.java)
                        intent.putExtra("user", userJson)
                        startActivity(intent)
                    }
                } else {
                    Toast.makeText(this, "Incorrect password", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show()
            }
        }

        binding.signUpLink.setOnClickListener{
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }

        binding.returnBack.setOnClickListener{
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        }
    }
}