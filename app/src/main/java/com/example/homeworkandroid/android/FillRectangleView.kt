package com.example.homeworkandroid.android

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import java.util.Random

class FillRectangleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private var fillPercent = 0
    private var fillColor = getRandomColor()

    private val paint = Paint().apply {
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    init {
        setOnClickListener {
            fillPercent += 10
            if (fillPercent > 100) {
                fillPercent = 0
            }
            fillColor = getRandomColor()
            invalidate()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.color = Color.GRAY
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)

        val filledWidth = width * fillPercent / 100f
        paint.color = fillColor
        canvas.drawRect(0f, 0f, filledWidth, height.toFloat(), paint)
    }

    private fun getRandomColor(): Int {
        val rnd = Random()
        return Color.rgb(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }
}
