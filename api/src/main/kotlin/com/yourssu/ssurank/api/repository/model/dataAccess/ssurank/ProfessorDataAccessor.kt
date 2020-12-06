package com.yourssu.ssurank.api.repository.model.dataAccess.ssurank

import com.yourssu.ssurank.api.repository.model.entity.ssurank.Professor
import com.yourssu.ssurank.api.repository.model.projection.ssurank.ProfessorTransporter
import com.yourssu.ssurank.api.repository.model.projection.ssurank.SearchProfessorTransporter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
open class ProfessorDataAccessor(
        @Autowired override var repository: ProfessorRepository
) : DataAccessorAdapterRepository<Int, Professor, ProfessorRepository>() {

    fun save(professor: Professor): Mono<Professor> {
        return monoFromCallableWithScheduler { repository.save(professor) }
    }

    fun findAll(): Flux<Professor> {
        return monoFromCallableWithScheduler { repository.findAll() }
                .flatMapMany { Flux.fromIterable(it) }
    }

    fun getProfessorsByDept(department: String, page: Pageable): Flux<SearchProfessorTransporter> {
        return monoFromCallableWithScheduler { repository.getProfessorsByDepartmentOrderByRatingDesc(department, page) }
                .flatMapMany { Flux.fromIterable(it) }
    }

    fun getTop10Honors(): Flux<ProfessorTransporter> {
        return monoFromCallableWithScheduler { repository.getProfessorsHavingCoursesOverTen().subList(0, 10) }
                .flatMapMany { Flux.fromIterable(it) }
    }

    fun findAllByProfessorName(name: String, page: Pageable): Flux<SearchProfessorTransporter> {
        return monoFromCallableWithScheduler { repository.findAllByNameContainsOrderByRatingDesc(name, page) }
                .flatMapMany { Flux.fromIterable(it) }
    }
}