package com.example.rotationtile

import android.content.Context
import android.provider.Settings

class RotationInteractor(
    private val context: Context
) {

    fun disableAccelerometerRotation() {
        Settings.System.putInt(
            context.contentResolver,
            Settings.System.ACCELEROMETER_ROTATION,
            0
        )
    }

    fun changeRotation(surface: Int) {
        Settings.System.putInt(
            context.contentResolver,
            Settings.System.USER_ROTATION,
            surface
        )
    }

    fun currentRotation(): Int =
        Settings.System.getInt(
            context.contentResolver,
            Settings.System.USER_ROTATION
        )

}