package com.example.rotationtile

import android.graphics.drawable.Icon
import android.provider.Settings
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer

class RotationTileService : TileService(), LifecycleOwner {

    private lateinit var lifecycleRegistry: LifecycleRegistry
    private var model: RotationTileModel? = null

    override fun onCreate() {
        lifecycleRegistry = LifecycleRegistry(this)
        lifecycleRegistry.markState(Lifecycle.State.CREATED)

        applicationContext?.let {
            model = RotationTileModel(it)
        }
    }

    override fun onStartListening() {
        lifecycleRegistry.markState(Lifecycle.State.STARTED)

        if (!Settings.System.canWrite(this)) {
            qsTile?.state = Tile.STATE_UNAVAILABLE
        }

        observeTileIconChanges()
    }

    override fun onClick() {
        model?.changeRotation()
    }

    override fun getLifecycle(): Lifecycle =
        lifecycleRegistry

    private fun observeTileIconChanges() {
        model?.getTileIconLiveData()?.observe(this, Observer {
            changeTileIcon(it)
        })
    }

    private fun changeTileIcon(newIcon: Icon) {
        qsTile?.apply {
            icon = newIcon
            updateTile()
        }
    }

    override fun onStopListening() {
        lifecycleRegistry.markState(Lifecycle.State.RESUMED)
    }

    override fun onTileRemoved() {
        lifecycleRegistry.markState(Lifecycle.State.DESTROYED)
    }

}