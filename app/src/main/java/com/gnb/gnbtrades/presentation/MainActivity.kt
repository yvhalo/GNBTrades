package com.gnb.gnbtrades.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gnb.gnbtrades.R
import com.gnb.gnbtrades.presentation.view.ProductsFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * MainActivity
 */
@AndroidEntryPoint
class MainActivity() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // TODO Better splash
        setTheme(R.style.Theme_GNBTrades)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // TODO use jetpack navigation
        supportFragmentManager.beginTransaction().add(R.id.container, ProductsFragment()).commit()

    }
}