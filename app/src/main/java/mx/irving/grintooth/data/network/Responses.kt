package mx.irving.grintooth.data.network

import com.google.gson.annotations.SerializedName

data class DeviceResponse(
        @SerializedName("_id") val id: String?,
        val name: String?,
        val address: String?,
        val strength: String?,
        @SerializedName("created_at") val createdAt: String?
)

data class AddDeviceResponse(
        //TODO: check correct body when service ready
        val device: DeviceResponse
)
