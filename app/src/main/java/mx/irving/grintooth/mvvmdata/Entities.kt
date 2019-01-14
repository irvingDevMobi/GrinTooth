package mx.irving.grintooth.mvvmdata

class OptionalRx<Model>(val value: Model?)

data class Device(
        val id: String,
        val name: String,
        val address: String,
        val strength: String? = null
)