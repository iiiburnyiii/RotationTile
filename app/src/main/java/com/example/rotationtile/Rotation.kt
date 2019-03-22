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
        override val next: Rotation
            get() = LANDSCAPE
    },
    LANDSCAPE(
        surface = Surface.ROTATION_90,
        iconRes = R.drawable.ic_tile_landscape_24dp
    ) {
        override val next: Rotation
            get() = PORTRAIT
    };

    abstract val next: Rotation

    companion object {

        fun getRotationFromSurface(surface: Int): Rotation =
            when (surface) {
                Surface.ROTATION_0 -> PORTRAIT
                Surface.ROTATION_90 -> LANDSCAPE
                else -> PORTRAIT
            }

    }
}