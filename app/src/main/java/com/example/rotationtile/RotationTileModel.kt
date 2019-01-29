package com.example.rotationtile

import android.content.Context
import android.graphics.drawable.Icon
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import kotlin.concurrent.thread

class RotationTileModel(
    private val context: Context
) {

    private val interactor = RotationInteractor(context)
    private val tileIcon = MutableLiveData<Icon>()

    fun changeRotation() = thread {
        interactor.disableAccelerometerRotation()
        val nextRotation = currentRotation().nextRotation()

        interactor.changeRotation(nextRotation.surface)

        tileIcon.postValue(iconFromRes(nextRotation.iconRes))
    }

    fun getTileIconLiveData(): LiveData<Icon> = map(tileIcon) { it }

    private fun currentRotation() =
        Rotation.getRotation(interactor.currentRotation())

    private fun iconFromRes(iconRes: Int) =
        Icon.createWithResource(context, iconRes)

}