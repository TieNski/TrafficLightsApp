package com.kbc.interview.test.app.ui.view

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.kbc.interview.test.app.R
import com.kbc.interview.test.app.databinding.WidgetTrafficLightsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class WidgetTrafficLight : LinearLayout, CoroutineScope {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var binding : WidgetTrafficLightsBinding = WidgetTrafficLightsBinding.inflate(LayoutInflater.from(context), this, true)

    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + job

    fun startTrafficLights() {
        if (::job.isInitialized && job.isActive) {
            return
        }

        job = Job()
        launch {
            while (true) {
                CoroutineScope(Dispatchers.Main).launch {
                    turnOnRed()
                }
                delay(4000)
                CoroutineScope(Dispatchers.Main).launch {
                    turnOffRed()
                    turnOnGreen()
                }
                delay(4000)
                CoroutineScope(Dispatchers.Main).launch {
                    turnOffGreen()
                    turnOnOrange()
                }
                delay(1000)
                CoroutineScope(Dispatchers.Main).launch {
                    turnOffOrange()
                }
            }
        }
    }

    fun turnOffAllLights() {
        CoroutineScope(Dispatchers.Main).launch {
            stopJob()
            turnOffGreen()
            turnOffRed()
            turnOffOrange()
        }
    }

    private fun stopJob() {
        if (::job.isInitialized && job.isActive) {
            job.cancel()
        }
    }

    private fun animateColorChange(trafficLight: ImageView, startColor: Int, endColor: Int) {
        val valueAnimator = ValueAnimator.ofObject(
            ArgbEvaluator(),
            ContextCompat.getColor(context, startColor),
            ContextCompat.getColor(context, endColor))

        val background = trafficLight.drawable as GradientDrawable
        valueAnimator.addUpdateListener { animator -> background.setColor((animator.animatedValue as Int)) }
        valueAnimator.duration = 500
        valueAnimator.start()
    }

    private fun turnOffGreen() {
        animateColorChange(binding.green, R.color.green, R.color.dark_green)
    }

    private fun turnOnGreen() {
        animateColorChange(binding.green, R.color.dark_green, R.color.green)
    }

    private fun turnOffRed() {
        animateColorChange(binding.red, R.color.red, R.color.dark_red)
    }

    private fun turnOnRed() {
        animateColorChange(binding.red, R.color.dark_red, R.color.red)
    }

    private fun turnOffOrange() {
        animateColorChange(binding.orange, R.color.orange, R.color.dark_orange)
    }

    private fun turnOnOrange() {
        animateColorChange(binding.orange, R.color.dark_orange, R.color.orange)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        stopJob()
    }
}