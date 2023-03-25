package com.randomlychosenbytes.androidgamebase.entities

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.randomlychosenbytes.androidgamebase.PLAYER_SPEED

class Player(
    private var x: Float,
    private var y: Float,
    private val radius: Float,
    private val SCREEN_HEIGHT: Int
) {
    private val paint = Paint().apply { color = Color.RED }

    fun moveUp(deltaTime: Float) {
        y -= deltaTime * PLAYER_SPEED

        if (y < 0) {
            y = SCREEN_HEIGHT.toFloat()
        }
    }

    fun render(canvas: Canvas) {
        canvas.drawCircle(x, y, radius, paint)
    }
}
