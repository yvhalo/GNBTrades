package com.gnb.gnbtrades.presentation

import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.gnb.gnbtrades.R
import com.gnb.gnbtrades.presentation.base.BaseActivity
import com.gnb.gnbtrades.presentation.products.view.ProductsFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * MainActivity
 */
@AndroidEntryPoint
class MainActivity() : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_GNBTrades)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupNavigationController()
    }

    /**
     * Initializes child fragments navigation controller
     */
    private fun setupNavigationController() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }
}