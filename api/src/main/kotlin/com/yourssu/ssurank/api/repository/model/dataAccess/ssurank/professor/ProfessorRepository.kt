package com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.professor

import com.yourssu.ssurank.api.repository.model.entity.ssurank.entity.Professor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProfessorRepository : JpaRepository<Professor, Int>{
    fun existsByNameAndCollegeAndDepartmentAndPosition(Name: String, Colleage: String, Department: String, Position: String): Boolean
    fun findByNameAndCollegeAndDepartmentAndPosition(Name: String, Colleage: String, Department: String, Position: String): Professor
    fun findByNameContains(Name: String) : List<Professor>
}