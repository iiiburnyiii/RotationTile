package com.example.rotationtile

import android.content.Context
import android.provider.Settings

class RotationInteractor(
    private val context: Context
) {

    fun stopAccelerometerRotation() {
        Settings.System.putInt(
            context.contentResolver,
            Settings.System.ACCELEROMETER_ROTATION,
            0
        )
    }

    fun changeSurface(surface: Int) {
        Settings.System.putInt(
            context.contentResolver,
            Settings.System.USER_ROTATION,
            surface
        )
    }

    fun currentSurface(): Int =
        Settings.System.getInt(
            context.contentResolver,
            Settings.System.USER_ROTATION
        )

}