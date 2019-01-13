package mx.irving.grintooth.domain

import android.arch.lifecycle.ViewModel
import io.reactivex.Observable
import mx.irving.grintooth.data.Device
import mx.irving.grintooth.data.DeviceGateway
import mx.irving.grintooth.data.OptionalRx
import mx.irving.grintooth.data.mappers.DeviceResponseMapper
import mx.irving.grintooth.data.network.GrinApi

class SavedDevicesViewModel() : ViewModel() {
    private val deviceGateway by lazy { DeviceGateway(GrinApi.instance, DeviceResponseMapper()) }

    fun getDevices(): Observable<OptionalRx<List<Device>>?> {
        return deviceGateway.getDevices()
    }
}
