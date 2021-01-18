package com.gnb.gnbtrades.presentation.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat.animate
import androidx.navigation.NavController
import com.gnb.gnbtrades.presentation.products.view.ProductsFragmentDirections
import kotlinx.android.synthetic.main.activity_main.*

open class BaseActivity : AppCompatActivity() {
    var navController : NavController? = null

    private var firstTime = true

    companion object {
        const val COMMON_DURATION = 1000L
    }

    override fun onResume() {
        super.onResume()
        firstTime = true
    }

    fun showLoading() {
        animate(loading)
                .alpha(1f)
                .setDuration(COMMON_DURATION)
                .start()
    }

    fun hideLoading() {
        var animate = animate(loading)
            if (firstTime) {
                animate.startDelay = 1000L
                firstTime = false
            }
            animate.alpha(0f)
            .setDuration(COMMON_DURATION)
            .start()
    }
}
