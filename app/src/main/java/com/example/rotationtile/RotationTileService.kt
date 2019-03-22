package com.example.rotationtile

import android.service.quicksettings.TileService

class RotationTileService : TileService() {

    /**
     * Or create model instance in onCreate and not reset link in onStop.
     * Faster surface change, but, as i understand, link is permanently stored in the service.
    */

    private var model: RotationTileModel? = null

    override fun onStartListening() {
        model = RotationTileModel(this)
        changeTileIcon()
    }

    override fun onClick() {
        model?.changeRotation()
        changeTileIcon()
    }

    override fun onStopListening() {
        model = null
    }

    private fun changeTileIcon() {
        if (model != null && qsTile != null)
            qsTile.apply {
                icon = model!!.icon
                updateTile()
            }
    }

}