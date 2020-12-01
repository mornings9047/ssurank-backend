package com.yourssu.ssurank.api.admin.service

import com.yourssu.ssurank.api.admin.service.function.ReadProfessorFunction
import com.yourssu.ssurank.api.admin.service.function.UpdateProfessorRatingsFunction
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseDataAccessor
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseRepository
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorDataAccessor
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class ProfessorService(
        professorRepository: ProfessorRepository,
        courseRepository: CourseRepository,
        professorDataAccessor: ProfessorDataAccessor,
        courseDataAccessor: CourseDataAccessor
) {
    private val readProfessorFunction = ReadProfessorFunction(professorRepository, courseRepository)
    private val updateProfessorRatingsFunction = UpdateProfessorRatingsFunction(professorDataAccessor, courseDataAccessor)

    fun readProfessor() {
        readProfessorFunction.readExcel()
    }

    fun updateProfessorRatings(): Mono<Unit> {
        return updateProfessorRatingsFunction.updateProfessorRatings()
    }
}