package com.randomlychosenbytes.androidgamebase

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView

class GameSurfaceView(context: Context, @Suppress("unused") attributeSet: AttributeSet) :
    SurfaceView(context),
    SurfaceHolder.Callback {

    private var gameState: GameState? = null
    private lateinit var gameLoop: GameLoop

    init {
        holder.addCallback(this)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        super.onTouchEvent(event)
        gameState?.handleInput(event)
        return true
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        gameState?.draw(canvas)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        // the game needs screen width and height. So, here is the earliest option for
        // initialization.
        if (gameState == null) {
            gameState = GameState(width, height)
        }

        gameLoop = GameLoop(holder, TARGET_FPS) { canvas, deltaTime ->
            draw(canvas)
            gameState?.update(deltaTime)
        }

        gameLoop.running = true
        gameLoop.start()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        var retry = true
        while (retry) {
            try {
                gameLoop.running = false
                gameLoop.join()
                retry = false
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }
}