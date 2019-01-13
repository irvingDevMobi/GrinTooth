package mx.irving.grintooth.data.mappers

abstract class BaseMapper<Input, Output, Params> {

    abstract fun transform(input: Input?, params: Params? = null): Output?

    fun transform(collection: Collection<Input>?, params: Params? = null): List<Output>? {
        if (collection == null) {
            return null
        }
        val list = ArrayList<Output>()
        for (input in collection) {
            val entity = transform(input, params)
            if (entity != null) {
                list.add(entity)
            }
        }
        return list
    }
}
