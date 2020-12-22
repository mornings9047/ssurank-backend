package com.yourssu.ssurank.api.repository.model.dataAccess.ssurank

import com.yourssu.ssurank.api.repository.model.entity.ssurank.CourseProfessor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
class CourseProfessorDataAccessor(
        @Autowired override var repository: CourseProfessorRepository
) : DataAccessorAdapterRepository<Int, CourseProfessor, CourseProfessorRepository>() {

    fun save(courseProfessor: CourseProfessor): Mono<CourseProfessor> {
        return monoFromCallableWithScheduler { repository.save(courseProfessor) }
    }
}