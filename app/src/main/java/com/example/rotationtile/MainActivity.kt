package com.example.rotationtile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val rotationModel = RotationTileModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        checkWriteSettingsPermission()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnChange.setOnClickListener {
            rotationModel.changeRotation()
        }
    }

    private fun checkWriteSettingsPermission() {
        if (!Settings.System.canWrite(this)) {
            val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS).apply {
                data = Uri.parse("package:$packageName")
                flags = (Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            startActivityForResult(intent, 0)
        }
    }

}
