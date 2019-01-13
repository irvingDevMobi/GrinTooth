package mx.irving.grintooth.data.mappers

import io.realm.RealmList

abstract class BaseDataMapper<DataSource, Entity, Params> {
    abstract fun transformData(dataSource: DataSource?, params: Params? = null): Entity?

    abstract fun transformEntity(entity: Entity, params: Params? = null): DataSource?

    fun transformData(collection: Collection<DataSource>?, params: Params? = null): List<Entity>? {
        if (collection == null) {
            return null
        }
        val list = ArrayList<Entity>()
        for (data in collection) {
            val entity = transformData(data, params)
            if (entity != null) {
                list.add(entity)
            }
        }
        return list
    }

    fun transformEntities(collection: Collection<Entity>,
                          params: Params? = null): RealmList<DataSource> {
        val list = RealmList<DataSource>()
        for (entity in collection) {
            val data = transformEntity(entity, params)
            if (data != null) {
                list.add(data)
            }
        }
        return list
    }
}
