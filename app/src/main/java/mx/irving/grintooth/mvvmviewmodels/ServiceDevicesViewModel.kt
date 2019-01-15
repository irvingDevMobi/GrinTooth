package mx.irving.grintooth.mvvmviewmodels

import android.arch.lifecycle.ViewModel
import io.reactivex.Observable
import mx.irving.grintooth.mvvmdata.Device
import mx.irving.grintooth.mvvmdata.DeviceGateway
import mx.irving.grintooth.mvvmdata.OptionalRx
import mx.irving.grintooth.mvvmdata.mappers.DeviceResponseMapper
import mx.irving.grintooth.mvvmdata.network.GrinApi

class ServiceDevicesViewModel() : ViewModel() {
    private val deviceGateway by lazy { DeviceGateway(GrinApi.instance, DeviceResponseMapper()) }

    fun getDevices(): Observable<OptionalRx<List<Device>>?> {
        return deviceGateway.getDevices()
    }
}
