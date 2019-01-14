package mx.irving.grintooth.mvvmdata.platform

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat
import mx.irving.grintooth.mvvmdata.IBluetoothManager
import java.lang.ref.WeakReference

class MyBluetoothManager : IBluetoothManager {
    override var contextWeak: WeakReference<*>? = null

    private val bluetooth by lazy { BluetoothAdapter.getDefaultAdapter() }

    override fun isBluetoothSupported(): Boolean = bluetooth != null

    override fun isBluetoothEnable(): Boolean = bluetooth?.isEnabled ?: false

    override fun hasLocationPermission(): Boolean {
        contextWeak?.get()?.let {
            it as Context
            return ContextCompat.checkSelfPermission(it,
                                                     Manifest.permission.ACCESS_COARSE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED
        }
        return false
    }

    override fun startDeviceDiscovery() {
        if (bluetooth.isDiscovering) bluetooth.cancelDiscovery()
        bluetooth.startDiscovery()
    }
}
