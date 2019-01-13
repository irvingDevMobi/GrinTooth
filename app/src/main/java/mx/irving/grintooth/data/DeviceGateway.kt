package mx.irving.grintooth.data

import io.reactivex.Observable
import mx.irving.grintooth.data.mappers.DeviceResponseMapper
import mx.irving.grintooth.data.network.GrinApi

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
