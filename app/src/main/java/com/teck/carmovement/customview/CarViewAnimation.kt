package com.teck.carmovement.customview

import android.view.View
import android.view.ViewPropertyAnimator
import android.view.animation.Interpolator

class CarViewAnimation(
    private val maxYOfScreen: Float,
    private val maxXOfScreen: Float,
    private val view: View,
    private val interpolator: Interpolator
) {
    fun onStartAnimation(duration: Long, rotation: Float, isMoved: Boolean) {
        if (isMoved) {
            animate(rotation, duration, true, -1f)
                .withEndAction {
                    animate(rotation, duration, false, -1f)
                        .start()
                }
                .start()
        } else {
            animate(rotation, duration, true, 1f)
                .withEndAction {
                    animate(rotation, duration, false, 1f)
                        .start()
                }
                .start()
        }
    }

    private fun animate(
        rotation: Float,
        duration: Long,
        xTranslation: Boolean,
        up: Float
    ): ViewPropertyAnimator =
        view
            .animate().apply {
                val movement: Float
                if (xTranslation) {
                    movement = up * (maxXOfScreen / 2 - view.width * 0.5f)
                    translationX(movement)
                } else {
                    movement = if (up == 1f) {
                        -maxYOfScreen + view.measuredHeight * 1.75f
                    } else {
                        maxYOfScreen / 2 - view.measuredHeight * 1.75f
                    }
                    translationY(movement)
                }
            }
            .rotationBy(rotation - 90f)
            .setInterpolator(interpolator).apply {
                if (xTranslation) {
                    setDuration(duration)
                } else {
                    setDuration(3 * duration)
                }
            }
}