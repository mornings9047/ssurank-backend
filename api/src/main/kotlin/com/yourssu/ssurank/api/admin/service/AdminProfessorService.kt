package com.yourssu.ssurank.api.admin.service

import com.yourssu.ssurank.api.admin.service.function.ReadProfessorFunction
import com.yourssu.ssurank.api.admin.service.function.UpdateProfessorRatingsAndRankingsFunction
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseDataAccessor
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseRepository
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorDataAccessor
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class AdminProfessorService(
        professorRepository: ProfessorRepository,
        courseRepository: CourseRepository,
        professorDataAccessor: ProfessorDataAccessor,
        courseDataAccessor: CourseDataAccessor
) {
    private val readProfessorFunction = ReadProfessorFunction(professorRepository, courseRepository)
    private val updateProfessorRatingsAndGradesFunction = UpdateProfessorRatingsAndRankingsFunction(professorDataAccessor, courseDataAccessor)

    fun readProfessor() {
        readProfessorFunction.readExcel()
    }

    /*
    fun updateProfessorRatingsAndGrades(): Mono<Unit> {
        return updateProfessorRatingsAndGradesFunction.updateProfessorRatingsAndGrades()
    }
    랭킹 업데이트 메소드 수정 필요
     */
}