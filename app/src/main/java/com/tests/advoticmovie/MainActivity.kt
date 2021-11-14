package com.tests.advoticmovie

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.tests.advoticmovie.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupNavigation()
    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.navHostFragment).navigateUp()
    }

    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.botNavigationViewMain.itemIconTintList = null
        NavigationUI.setupWithNavController(binding.botNavigationViewMain, navController)

        navHostFragment.findNavController()
            .addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.homeFragment,
                    R.id.homeFragment,
                    R.id.homeFragment,
                    R.id.homeFragment ->
                        binding.botNavigationViewMain.visibility = View.VISIBLE
                    else -> binding.botNavigationViewMain.visibility = View.GONE
                }

            }
    }


    interface getAuthIntent {
        var getData: String?
    }

}