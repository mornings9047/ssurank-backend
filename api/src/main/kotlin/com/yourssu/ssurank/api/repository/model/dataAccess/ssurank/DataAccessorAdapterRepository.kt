package com.yourssu.ssurank.api.repository.model.dataAccess.ssurank

import com.yourssu.ssurank.api.repository.model.entity.common.SuperEntity
import java.io.Serializable

abstract class DataAccessorAdapterRepository<Key : Serializable, Entity : SuperEntity<Key>, Repository : ExtendedRepository<Key, Entity>> : DataAccessor<Key, Entity>() {
    protected open lateinit var repository: Repository
}