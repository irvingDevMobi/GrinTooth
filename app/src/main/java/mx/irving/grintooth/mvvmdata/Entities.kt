package mx.irving.grintooth.mvvmdata

import mx.irving.grintooth.utils.EMPTY

class OptionalRx<Model>(val value: Model?)

data class Device(
        val id: String,
        val name: String,
        val address: String,
        val strength: String? = null,
        val creationDate: String = EMPTY
)
