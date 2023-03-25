package com.randomlychosenbytes.androidgamebase

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.SurfaceHolder
import kotlin.math.roundToInt

class GameLoop(
    private val surfaceHolder: SurfaceHolder,
    targetFps: Int,
    val update: (Canvas, Float) -> Unit
) : Thread() {

    var running = false
    private val targetTimePerFrameMs = (MILLIS_IN_SECOND / targetFps).roundToInt()

    override fun run() {
        var lastUpdateTime = System.currentTimeMillis()

        while (running) {
            val currentTimeMs = System.currentTimeMillis()
            val elapsedTimeMs = currentTimeMs - lastUpdateTime

            if (BuildConfig.DEBUG) {
                // update FPS every 60 frames
                if (frameCounter % TARGET_FPS == 0) {
                    fps = (MILLIS_IN_SECOND / elapsedTimeMs).roundToInt()
                }
            }

            if (elapsedTimeMs >= targetTimePerFrameMs) {
                lastUpdateTime = currentTimeMs

                val deltaTime = elapsedTimeMs / MILLIS_IN_SECOND

                surfaceHolder.lockCanvas()?.let { canvas ->
                    update(canvas, deltaTime)

                    if (BuildConfig.DEBUG) {
                        // update FPS every 60 frames
                        frameCounter++
                        canvas.drawText("FPS: $fps", 100f, 100f, paint)
                    }

                    surfaceHolder.unlockCanvasAndPost(canvas)
                }
            }
        }
    }

    private var frameCounter = 0
    private var fps = 0

    private val paint = Paint().apply {
        color = Color.GRAY
        textSize = 60f
    }

    companion object {
        const val MILLIS_IN_SECOND = 1000f
    }
}
