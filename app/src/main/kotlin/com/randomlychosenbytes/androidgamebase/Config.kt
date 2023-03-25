package com.randomlychosenbytes.androidgamebase

const val TARGET_FPS = 60

// we consider 1080px as height of the screen. All constant values in this
// file are related to that number. In order to scale properly for any screen height
// we calculate a scale factor 1080px / (actual)screenHeight and multiply all values with it.
const val BASE_HEIGHT = 1080
const val PLAYER_SPEED = 1000
const val PLAYER_RADIUS = 100
