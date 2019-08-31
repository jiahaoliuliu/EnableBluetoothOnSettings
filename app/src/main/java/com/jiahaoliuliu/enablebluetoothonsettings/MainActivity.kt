package com.jiahaoliuliu.enablebluetoothonsettings

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import androidx.databinding.DataBindingUtil
import com.jiahaoliuliu.enablebluetoothonsettings.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    companion object {
        private const val REQUEST_CODE_BLUETOOTH_ENABLE = 1000
    }

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainBinding.bluetoothButton.setOnClickListener{
            requestForBluetooth(isBluetoothEnabled())
        }
    }

    override fun onResume() {
        super.onResume()
        updateView()
    }

    private fun requestForBluetooth(isBluetoothEnabled: Boolean) {
        // Request to disable bluetooth
        if (isBluetoothEnabled) {
            return
        }

        // Request to enable bluetooth
        val requestToEnableBluetoothIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
        startActivityForResult(requestToEnableBluetoothIntent, REQUEST_CODE_BLUETOOTH_ENABLE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_BLUETOOTH_ENABLE) {
            if (resultCode == Activity.RESULT_OK) {
                // The bluetooth has been enabled correctly
            }
            updateView()
        }
    }

    private fun isBluetoothEnabled() = BluetoothAdapter.getDefaultAdapter().isEnabled

    private fun updateView() {
        mainBinding.isBluetoothEnabled = isBluetoothEnabled()
    }
}
