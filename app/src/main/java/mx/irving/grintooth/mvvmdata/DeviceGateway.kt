package mx.irving.grintooth.mvvmdata

import io.reactivex.Observable
import mx.irving.grintooth.mvvmdata.mappers.DeviceResponseMapper
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
}
