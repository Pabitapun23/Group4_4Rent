package com.pp.a4rent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.pp.a4rent.databinding.ActivityMainBinding
import com.pp.a4rent.screens.TorontoRentalsActivity

import com.pp.a4rent.screens.AccountActivity
import com.pp.a4rent.screens.LoginActivity
import com.pp.a4rent.screens.RegisterActivity


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val TAG : String = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        // display menu bar on the screen
        setSupportActionBar(this.binding.menuToolbar)
        // Change the title
        supportActionBar?.title = "4Rent"

        // For changing the color of overflow icon
        // Get the drawable for the overflow icon
        val drawable = this.binding.menuToolbar.overflowIcon

        // Apply a tint to change the color of the overflow icon
        if (drawable != null) {
            val wrappedDrawable = DrawableCompat.wrap(drawable).mutate()
            DrawableCompat.setTint(wrappedDrawable, ContextCompat.getColor(this, R.color.white))
            binding.menuToolbar.overflowIcon = wrappedDrawable
        }

        binding.btnSearchToronto.setOnClickListener {
            this.goToTorontoRentals()
        }
    }

    // Function to navigate users to Toronto Rentals page
    // Shows list of rentals in Toronto area
    fun goToTorontoRentals() {

        // navigate to 2nd screen
        val torontoRentalsIntent = Intent(this@MainActivity, TorontoRentalsActivity::class.java)
        startActivity(torontoRentalsIntent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu_options, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId) {
            R.id.menu_item_post_rental -> {
                Log.d(TAG, "onOptionsItemSelected: Post Rental option is selected")

                // navigate to 2nd screen
                val sidebarIntent = Intent(this@MainActivity, ProfileActivity::class.java)
                startActivity(sidebarIntent)

                return true
            }
            R.id.menu_item_blog -> {
                Log.d(TAG, "onOptionsItemSelected: Blog option is selected")

                // navigate to 2nd screen
//                val sidebarIntent = Intent(this@MainActivity, AccountActivity::class.java)
//                startActivity(sidebarIntent)

                return true
            }
            R.id.menu_item_signup -> {
                Log.d(TAG, "onOptionsItemSelected: Sign Up option is selected")

                // navigate to 2nd screen
                val sidebarIntent = Intent(this@MainActivity, RegisterActivity::class.java)
                startActivity(sidebarIntent)

                return true
            }
            R.id.menu_item_login -> {
                Log.d(TAG, "onOptionsItemSelected: Log In option is selected")

                // navigate to 2nd screen
                val sidebarIntent = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(sidebarIntent)

                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}