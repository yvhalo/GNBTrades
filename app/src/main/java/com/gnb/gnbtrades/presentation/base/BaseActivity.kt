package com.gnb.gnbtrades.presentation.base

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat.animate
import androidx.navigation.NavController
import com.gnb.gnbtrades.presentation.products.view.ProductsFragmentDirections
import kotlinx.android.synthetic.main.activity_main.*

open class BaseActivity : AppCompatActivity() {
    var navController : NavController? = null

    companion object {
        const val COMMON_DURATION = 1000L
    }
    private var firstTime = true

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
            }
            animate.alpha(0f)
            .setDuration(COMMON_DURATION)
            .start()
    }
}
