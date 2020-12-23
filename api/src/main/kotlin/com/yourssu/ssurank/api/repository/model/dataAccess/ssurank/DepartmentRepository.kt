package com.yourssu.ssurank.api.repository.model.dataAccess.ssurank

import com.yourssu.ssurank.api.repository.model.entity.ssurank.Department
import com.yourssu.ssurank.api.repository.model.projection.ssurank.DepartmentTransporter
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface DepartmentRepository : ExtendedRepository<Int, Department> {
    @Query("select original_name as originalName, shortened_name as shortenedName from departments where college = :college", nativeQuery = true)
    fun findDepartmentsByCollege(college: String): List<DepartmentTransporter>
}