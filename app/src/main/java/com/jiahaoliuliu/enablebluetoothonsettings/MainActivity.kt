package com.jiahaoliuliu.enablebluetoothonsettings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.Compiler.disable
import java.lang.Compiler.enable
import android.bluetooth.BluetoothAdapter
import androidx.databinding.DataBindingUtil
import com.jiahaoliuliu.enablebluetoothonsettings.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        updateView()
    }

    private fun setBluetooth(enable: Boolean): Boolean {
        val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        val isEnabled = isBluetoothEnabled()
        if (enable && !isEnabled) {
            return bluetoothAdapter.enable()
        } else if (!enable && isEnabled) {
            return bluetoothAdapter.disable()
        }
        // No need to change bluetooth state
        return true
    }

    private fun isBluetoothEnabled() = BluetoothAdapter.getDefaultAdapter().isEnabled

    private fun updateView() {
        mainBinding.isBluetoothEnabled = isBluetoothEnabled()
    }
}
