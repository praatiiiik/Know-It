package com.example.knowit.ui.Activity


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController
import com.example.knowit.databinding.ActivityMainBinding
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.example.knowit.R
import com.example.knowit.data.viewmodels.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity()   {

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var navController: NavController
    private val viewModel : NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //setting views
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment?
        navController = navHostFragment!!.navController
        val bottomNavigationView = mainBinding.bottomNavigationView
        bottomNavigationView.setupWithNavController(navController)

    }
}