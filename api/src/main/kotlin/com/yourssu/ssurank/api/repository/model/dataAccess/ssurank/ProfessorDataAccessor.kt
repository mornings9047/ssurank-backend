package com.yourssu.ssurank.api.repository.model.dataAccess.ssurank

import com.yourssu.ssurank.api.repository.model.entity.ssurank.entity.Professor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
/*
@Repository
open class ProfessorDataAccessor(
        @Autowired override var repository: ProfessorRepository
) : DataAccessorAdapterRepository<Int, Professor, ProfessorRepository>() {

    fun findAllByProfessorName(Name: String): Flux<Professor> {
        return monoFromCallableWithScheduler { repository.findAllByName(Name) }
                .flatMapMany { Flux.fromIterable(it) }
    }
}
 */