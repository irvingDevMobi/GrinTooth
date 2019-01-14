package mx.irving.grintooth.mvvmdata

import java.lang.ref.WeakReference

interface IBluetoothManager {

    var contextWeak: WeakReference<*>?

    fun isBluetoothSupported(): Boolean
    fun isBluetoothEnable(): Boolean
    fun hasLocationPermission(): Boolean
    fun startDeviceDiscovery()
}
