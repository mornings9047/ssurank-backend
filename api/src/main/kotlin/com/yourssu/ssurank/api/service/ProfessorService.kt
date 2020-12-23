package com.yourssu.ssurank.api.service

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.DepartmentDataAccessor
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorDataAccessor
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorEvaluationDataAccessor
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorEvaluationListDataAccessor
import com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank.DetailedProfessorDto
import com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank.SearchProfessorDto
import com.yourssu.ssurank.api.repository.model.function.ssurank.*
import com.yourssu.ssurank.api.repository.model.projection.ssurank.DepartmentTransporter
import com.yourssu.ssurank.api.repository.model.projection.ssurank.DetailedProfessorCoursesTransporter
import com.yourssu.ssurank.api.repository.model.projection.ssurank.ProfessorEvaluationTransporter
import com.yourssu.ssurank.api.repository.model.projection.ssurank.ProfessorTransporter
import com.yourssu.ssurank.api.request.ProfessorEvaluationRequest
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class ProfessorService(
        professorDataAccessor: ProfessorDataAccessor,
        departmentDataAccessor: DepartmentDataAccessor,
        professorEvaluationDataAccessor: ProfessorEvaluationDataAccessor,
        professorEvaluationListDataAccessor: ProfessorEvaluationListDataAccessor
) {
    private val getDepartmentListFunction = GetDepartmentListFunction(departmentDataAccessor)
    private val searchProfessorFunction = SearchProfessorFunction(professorDataAccessor)
    private val getHonorsFunction = GetHonorProfessorsFunction(professorDataAccessor)
    private val getDetailedProfessorFunction = GetDetailedProfessorFunction(professorDataAccessor)
    private val getProfessorCoursesFunction = GetProfessorCoursesFunction(professorDataAccessor)
    private val insertProfessorEvaluationFunction = InsertProfessorEvaluationFunction(professorDataAccessor, professorEvaluationDataAccessor, professorEvaluationListDataAccessor)
    private val getProfessorEvaluationsFunction = GetProfessorEvaluationsFunction(professorEvaluationDataAccessor)

    fun getDepartmentList(): Map<String, List<DepartmentTransporter>> {
        return getDepartmentListFunction.getDepartmentList()
    }

    fun getProfessorsByDept(department: String, page: Int): Flux<SearchProfessorDto> {
        return searchProfessorFunction.getProfessorsByDept(department, page)
    }

    fun getHonorProfessors(): Flux<ProfessorTransporter> {
        return getHonorsFunction.getHonorProfessors()
    }

    fun searchProfessor(name: String, page: Int): Flux<SearchProfessorDto> {
        return searchProfessorFunction.searchProfessor(name, page)
    }

    fun getDetailedProfessor(id: Int): DetailedProfessorDto {
        return getDetailedProfessorFunction.getDetailedProfessor(id)
    }

    fun getProfessorCourses(id: Int, page: Int): List<DetailedProfessorCoursesTransporter> {
        return getProfessorCoursesFunction.getProfessorCourses(id, page)
    }

    fun evaluateProfessor(professorEvaluationRequest: ProfessorEvaluationRequest) {
        return insertProfessorEvaluationFunction.insertProfessorEvaluation(professorEvaluationRequest)
    }

    fun getRecentProfessorEvaluations(id: Int, page: Int): List<ProfessorEvaluationTransporter> {
        return getProfessorEvaluationsFunction.getRecentProfessorEvaluations(id, page)
    }

    fun getRecommendedProfessorEvaluations(id: Int, page: Int): List<ProfessorEvaluationTransporter> {
        return getProfessorEvaluationsFunction.getRecommendedProfessorEvaluations(id, page)
    }
}