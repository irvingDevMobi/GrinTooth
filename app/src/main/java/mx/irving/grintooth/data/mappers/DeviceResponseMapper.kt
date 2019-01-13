package mx.irving.grintooth.data.mappers

import mx.irving.grintooth.data.Device
import mx.irving.grintooth.data.network.DeviceResponse

class DeviceResponseMapper : BaseMapper<DeviceResponse, Device, Any>() {
    override fun transform(input: DeviceResponse?, params: Any?): Device? {
        return if (input?.id == null || input.name == null || input.address == null) null
        else Device(input.id,
                    input.name,
                    input.address,
                    input.strength)
    }
}
