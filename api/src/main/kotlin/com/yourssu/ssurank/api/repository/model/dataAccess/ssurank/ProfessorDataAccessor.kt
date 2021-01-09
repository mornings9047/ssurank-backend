package com.yourssu.ssurank.api.repository.model.dataAccess.ssurank

import com.yourssu.ssurank.api.repository.model.entity.ssurank.Professor
import com.yourssu.ssurank.api.repository.model.projection.ssurank.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
class ProfessorDataAccessor(
        @Autowired override var repository: ProfessorRepository
) : DataAccessorAdapterRepository<Int, Professor, ProfessorRepository>() {

    fun save(professor: Professor): Professor {
        return repository.save(professor)
    }

    fun findAll(): List<Professor> {
        return repository.findAll()
    }

    fun countCourse(professor: Professor): Int {
        return repository.countCourse(professor.id!!)
    }

    fun getProfessorsByDept(department: String, page: Pageable): Flux<SearchProfessorTransporter> {
        return monoFromCallableWithScheduler { repository.getProfessorsByDepartment(department, page) }
                .flatMapMany { Flux.fromIterable(it) }
    }

    fun getTop10Honors(): Flux<ProfessorTransporter> {
        return monoFromCallableWithScheduler { repository.getTop10Honors() }
                .flatMapMany { Flux.fromIterable(it) }
    }

    fun findAllByProfessorName(name: String, page: Pageable): Flux<SearchProfessorTransporter> {
        return monoFromCallableWithScheduler { repository.findAllByName(name, page) }
                .flatMapMany { Flux.fromIterable(it) }
    }

    fun getDetailedProfessor(id: Int): DetailedProfessorTransporter {
        return repository.findDetailedProfessorById(id)
    }

    fun getTopPercent(id: Int): Int {
        return repository.getTopPercent(id)
    }

    fun getSessions(id: Int): List<SessionCourseTransporter> {
        return repository.getSessions(id)
    }

    fun getCoursesById(id: Int, page: Pageable): List<DetailedProfessorCoursesTransporter> {
        return repository.getCoursesById(id, page)
    }

    fun findByProfessorId(id: Int): Professor{
        return repository.findProfessorById(id)
    }

    fun countAllProfessorsByName(name: String): Int{
        return repository.countAllProfessorsByName(name)
    }

    fun countDepartment(department: String): Int{
        return repository.countDepartment(department)
    }

    fun countProfessorCourses(id: Int): Int{
        return repository.countProfessorCourses(id)
    }
}