package mx.irving.grintooth.mvvmdata.mappers

import mx.irving.grintooth.mvvmdata.Device
import mx.irving.grintooth.mvvmdata.network.DeviceResponse
import mx.irving.grintooth.utils.EMPTY

class DeviceResponseMapper : BaseMapper<DeviceResponse, Device, Any>() {
    override fun transform(input: DeviceResponse?, params: Any?): Device? {
        return if (input?.id == null || input.name == null || input.address == null) null
        else Device(input.id,
                    input.name,
                    input.address,
                    input.strength,
                    input.createdAt ?: EMPTY)
    }
}
