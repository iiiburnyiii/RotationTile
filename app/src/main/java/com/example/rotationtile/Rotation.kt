package com.example.rotationtile

import android.view.Surface

enum class Rotation(
    val surface: Int,
    val iconRes: Int
) {
    PORTRAIT(
        surface = Surface.ROTATION_0,
        iconRes = R.drawable.ic_tile_portrait_24dp
    ) {
        override fun nextRotation() = LANDSCAPE
    },
    LANDSCAPE(
        surface = Surface.ROTATION_90,
        iconRes = R.drawable.ic_tile_landscape_24dp
    ) {
        override fun nextRotation() = PORTRAIT
    };

    abstract fun nextRotation(): Rotation

    companion object {

        fun getRotation(surface: Int): Rotation =
            when (surface) {
                Surface.ROTATION_0 -> PORTRAIT
                Surface.ROTATION_90 -> LANDSCAPE
                else -> PORTRAIT
            }

    }
}

