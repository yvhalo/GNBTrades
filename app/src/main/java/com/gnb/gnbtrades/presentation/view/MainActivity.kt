package com.gnb.gnbtrades.presentation.view

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.gnb.gnbtrades.R
import dagger.hilt.android.AndroidEntryPoint

/**
 * MainActivity
 */
@AndroidEntryPoint
class MainActivity() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // TODO use jetpack navigation
        supportFragmentManager.beginTransaction().add(R.id.container, ProductsFragment()).commit()

    }
}