package mx.irving.grintooth.mvvmdata

import io.reactivex.Observable
import mx.irving.grintooth.mvvmdata.mappers.DeviceResponseMapper
import mx.irving.grintooth.mvvmdata.network.DeviceRequest
import mx.irving.grintooth.mvvmdata.network.GrinApi

class DeviceGateway(
        private val api: GrinApi,
        private val deviceResponseMapper: DeviceResponseMapper
) {
    fun getDevices(): Observable<OptionalRx<List<Device>>?> {
        return api.getDevices()
                .map {
                    OptionalRx(deviceResponseMapper.transform(it))
                }
    }

    fun saveDevice(device: Device): Observable<Boolean> {
        return api.addDevice(DeviceRequest(device.name,
                                           device.address,
                                           device.strength ?: "0"))
                .map {
                    return@map it.device != null
                }
    }
}
