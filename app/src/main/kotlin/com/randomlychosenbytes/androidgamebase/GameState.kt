package com.randomlychosenbytes.androidgamebase

import android.graphics.Canvas
import android.view.MotionEvent
import com.randomlychosenbytes.androidgamebase.entities.Player

class GameState(screenWidth: Int, screenHeight: Int) {

    private var player: Player
    private var scaleFactor: Float = BASE_HEIGHT / screenHeight.toFloat()
    private var touched = false

    init {
        player =
            Player(
                screenWidth / 2f,
                screenHeight / 2f,
                PLAYER_RADIUS * scaleFactor,
                screenHeight
            )
    }

    fun handleInput(event: MotionEvent) {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> touched = true
            MotionEvent.ACTION_UP -> touched = false
        }
    }

    fun update(deltaTime: Float) {
        if (touched) {
            player.moveUp(deltaTime)
        }
    }

    fun draw(canvas: Canvas) {
        player.render(canvas)
    }
}