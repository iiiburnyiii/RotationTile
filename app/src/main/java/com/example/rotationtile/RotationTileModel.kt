package com.example.rotationtile

import android.content.Context
import android.graphics.drawable.Icon

class RotationTileModel(
    private val context: Context
) {

    private val interactor = RotationInteractor(context)
    private val currentRotation: Rotation
        get() = Rotation.getRotationFromSurface(interactor.currentSurface())

    val icon: Icon
        get() = Icon.createWithResource(context, currentRotation.iconRes)

    fun changeRotation() {
        val nextRotation = currentRotation.next

        interactor.stopAccelerometerRotation()
        interactor.changeSurface(nextRotation.surface)
    }

}