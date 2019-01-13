package mx.irving.grintooth.data.db

interface BaseDataSource<T> {
    fun create(entity: T)
    fun createOrUpdate(entity: T)
    fun createOrUpdate(entities: List<T>)
    fun getFirst(): T?
    fun getAll(): List<T>?
    fun deleteAll()
}
