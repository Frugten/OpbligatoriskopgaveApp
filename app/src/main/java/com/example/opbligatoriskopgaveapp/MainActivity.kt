package com.example.opbligatoriskopgaveapp

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.view.*
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.widget.Toast

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.ui.setupWithNavController
import com.example.opbligatoriskopgaveapp.databinding.ActivityMainBinding

import com.example.opbligatoriskopgaveapp.MainActivity
import com.example.opbligatoriskopgaveapp.databinding.FragmentItemListBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import kotlinx.android.synthetic.main.activity_main.*
import androidx.appcompat.app.AlertDialog.Builder
import com.example.opbligatoriskopgaveapp.repository.FirebaseRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val toolbar = getSupportActionBar();

        loadFragment(FirstFragment.newInstance())

        bottom_navigation.setOnItemSelectedListener { item ->
            val fragment: Fragment
            when (item.itemId) {
                R.id.home -> {
                    //toolbar?.setTitle("Home")
                    fragment = FirstFragment()
                    loadFragment(fragment)
                    true
                }
                R.id.list -> {
                    //toolbar?.setTitle("List")
                    fragment = ItemFragment()
                    loadFragment(fragment)
                    true

                }
                R.id.login -> {
                    //toolbar?.setTitle("Login")
                    fragment = LoginFragment()
                    loadFragment(fragment)
                    true

                }

                else -> false
            }

        }*/

        //Initialize the bottom navigation view
        //create bottom navigation view object
        val bottomNavigationView = findViewById<BottomNavigationView
                >(R.id.bottom_navigation)
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        bottomNavigationView.setupWithNavController(
            navController
        )


            // No user is signed in
        }
    }
/*
    private fun loadFragment(fragment: Fragment) {
        // load fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }*/

