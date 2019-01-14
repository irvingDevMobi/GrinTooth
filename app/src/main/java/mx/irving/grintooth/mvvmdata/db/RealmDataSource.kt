package mx.irving.grintooth.mvvmdata.db

import mx.irving.grintooth.mvvmdata.mappers.BaseDataMapper
import io.realm.Realm
import io.realm.RealmObject

open class RealmDataSource<Data : RealmObject, Entity, Params>(
        protected val clazz: Class<Data>,
        protected val mapper: BaseDataMapper<Data, Entity, Params>,
        private val params: Params? = null) : BaseDataSource<Entity> {

    override fun create(entity: Entity) {
        val data = mapper.transformEntity(entity, params)
        data?.let { dataR ->
            Realm.getDefaultInstance().use { realm ->
                realm.executeTransaction { it.copyToRealm(dataR) }
            }
        }
    }

    override fun createOrUpdate(entity: Entity) {
        val data = mapper.transformEntity(entity, params)
        data?.let { dataR ->
            Realm.getDefaultInstance().use { realm ->
                realm.executeTransaction { it.copyToRealmOrUpdate(dataR) }
            }
        }
    }

    override fun createOrUpdate(entities: List<Entity>) {
        val dataList = mapper.transformEntities(entities, params)
        dataList.let { dataRList ->
            Realm.getDefaultInstance().use { realm ->
                realm.executeTransaction {
                    for (dataR in dataRList) {
                        it.copyToRealmOrUpdate(dataR)
                    }
                }
            }
        }
    }

    override fun getFirst(): Entity? {
        Realm.getDefaultInstance().use {
            val data = it.where(clazz).findFirst()
            return mapper.transformData(data)
        }
    }

    override fun getAll(): List<Entity>? {
        Realm.getDefaultInstance().use {
            val dataCollection = it.where(clazz).findAll()
            return mapper.transformData(dataCollection)
        }
    }

    override fun deleteAll() {
        Realm.getDefaultInstance().use { realm ->
            realm.executeTransaction { it.delete(clazz) }
        }
    }
}
