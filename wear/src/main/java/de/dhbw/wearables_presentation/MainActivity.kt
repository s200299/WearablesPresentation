package de.dhbw.wearables_presentation

import android.app.Activity
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import androidx.annotation.ColorLong
import androidx.annotation.RequiresApi
import androidx.core.view.InputDeviceCompat
import androidx.core.view.MotionEventCompat
import androidx.core.view.ViewConfigurationCompat
import de.dhbw.wearables_presentation.databinding.ActivityMainBinding
import kotlin.math.roundToInt
import kotlin.math.roundToLong

class MainActivity : Activity() {

    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var intBackgroundColor = 0
        binding.text.setTextColor(Color.parseColor(intToHexColor(intBackgroundColor)))
        binding.boxlayout.setBackgroundColor(makeColor(intBackgroundColor))

        binding.boxlayout.requestFocus()
        binding.boxlayout.setOnGenericMotionListener { v, ev ->
            if (ev.action == MotionEvent.ACTION_SCROLL && ev.isFromSource(InputDeviceCompat.SOURCE_ROTARY_ENCODER)){
                val delta = -ev.getAxisValue(MotionEventCompat.AXIS_SCROLL) *
                        ViewConfigurationCompat.getScaledVerticalScrollFactor(
                    ViewConfiguration.get(baseContext), baseContext)


                intBackgroundColor += delta.roundToInt()
                if(intBackgroundColor >= 360){
                    intBackgroundColor = 0
                } else if (intBackgroundColor<0){
                    intBackgroundColor = 360
                }

                val backgroundColor = makeColor(intBackgroundColor)

                //binding.text.setTextColor(makeColor(360-intBackgroundColor))
                binding.boxlayout.setBackgroundColor(backgroundColor)

                Log.d("wheel", "delta: ${delta.roundToInt()}")
                true
            } else{
                false
            }
        }


    }

    private fun intToHexColor(intColor: Int): String {
        return String.format("#%06X", 0xFFFFFF and intColor)
    }

    private fun makeColor(intColor: Int): Int{
        return Color.HSVToColor(floatArrayOf(
            intColor.toFloat(),
            1.0F, 1.0F
        ))
    }
}