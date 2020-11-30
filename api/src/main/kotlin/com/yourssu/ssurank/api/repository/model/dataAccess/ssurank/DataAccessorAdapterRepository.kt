package com.yourssu.ssurank.api.repository.model.dataAccess.ssurank

import com.yourssu.ssurank.api.repository.model.entity.common.SuperEntity
import reactor.core.publisher.Flux
import java.io.Serializable
import kotlin.reflect.KClass
/*
abstract class DataAccessorAdapterRepository<Key : Serializable, Entity : SuperEntity<Key>, Repository : ExtendedRepository<Key, Entity>> : DataAccessor<Key, Entity>() {
    protected open lateinit var repository: Repository

    override fun <T : Any> findAllBy(type: KClass<T>): Flux<T> {
        return monoFromCallableWithScheduler { repository.findAllBy(type.java) }
                .flatMapMany { Flux.fromIterable(it) }
    }
}
*/