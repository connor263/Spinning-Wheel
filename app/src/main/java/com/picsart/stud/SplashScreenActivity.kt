package com.picsart.stud

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.animation.doOnEnd

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val parent = findViewById<FrameLayout>(R.id.fl_parent)
        val appName = findViewById<TextView>(R.id.tv_name)

        val textAlpha = ObjectAnimator.ofFloat(
            appName, View.ALPHA, 0F, 1F
        ).apply {
            duration = 2000L
        }

        val scaleX = ObjectAnimator.ofFloat(
            parent, View.SCALE_X, 0.98F, 1F
        ).apply {
            duration = 1000L
        }
        val scaleY = ObjectAnimator.ofFloat(
            parent, View.SCALE_Y, 0.98F, 1F
        ).apply {
            duration = 1000L
        }
        val rotateWheel = ObjectAnimator.ofFloat(
            parent, View.ROTATION, 0F, 2160F
        ).apply {
            duration = 1000L
        }

        AnimatorSet().apply {
            play(scaleX).with(scaleY).with(rotateWheel).with(textAlpha)
        }.apply {
            doOnEnd {
                navigateToMainActivity()
            }
            start()
        }
    }

    private fun navigateToMainActivity() {
        Intent(this, MainActivity::class.java).run {
            startActivity(this)
            finish()
        }
    }
}