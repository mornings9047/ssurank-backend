package com.yourssu.ssurank.api.repository.model.dataAccess.ssurank

import reactor.core.publisher.Mono
import reactor.core.scheduler.Scheduler
import reactor.core.scheduler.Schedulers

abstract class DataAccessor<Key, Entity> {
    protected open var scheduler: Scheduler = Schedulers.boundedElastic()

    protected fun <T> monoFromCallableWithScheduler(supplier: () -> T): Mono<T> {
        return Mono.fromCallable(supplier)
                .subscribeOn(scheduler)
    }
}