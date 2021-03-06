package com.yourssu.ssurank.api.admin.service

import com.yourssu.ssurank.api.admin.service.function.ReadProfessorFunction
import com.yourssu.ssurank.api.admin.service.function.UpdateProfessorRatingsAndRankingsFunction
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseDataAccessor
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.DepartmentDataAccessor
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorDataAccessor
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorRepository
import org.springframework.stereotype.Service

@Service
class AdminProfessorService(
        departmentDataAccessor: DepartmentDataAccessor,
        professorRepository: ProfessorRepository,
        professorDataAccessor: ProfessorDataAccessor,
        courseDataAccessor: CourseDataAccessor
) {
    private val readProfessorFunction = ReadProfessorFunction(departmentDataAccessor, professorRepository)
    private val updateProfessorRatingsAndGradesFunction = UpdateProfessorRatingsAndRankingsFunction(professorDataAccessor, courseDataAccessor)

    fun readProfessor() {
        readProfessorFunction.readExcel()
    }

    fun updateProfessorRatingsAndGrades() {
        return updateProfessorRatingsAndGradesFunction.updateProfessorRatingsAndGrades()
    }
}