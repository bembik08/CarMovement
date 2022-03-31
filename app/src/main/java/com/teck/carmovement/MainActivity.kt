package com.teck.carmovement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.animation.AnticipateOvershootInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.teck.carmovement.customview.CarViewAnimation
import com.teck.carmovement.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(LayoutInflater.from(baseContext))
    }
    private var isMoved: Boolean = true
    private val anim: CarViewAnimation by lazy {
        CarViewAnimation(
            maxYOfScreen = binding.root.height.toFloat(),
            maxXOfScreen = binding.root.width.toFloat(),
            view = binding.taxiImage,
            interpolator = AnticipateOvershootInterpolator()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        binding.taxiImage.setOnClickListener {
            onStartAnimation()
        }
    }

    private fun onStartAnimation() {
        isMoved = !isMoved
        anim.onStartAnimation(800, 0f, isMoved)
    }
}