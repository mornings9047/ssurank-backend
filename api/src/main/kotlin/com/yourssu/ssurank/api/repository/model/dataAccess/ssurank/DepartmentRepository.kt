package com.yourssu.ssurank.api.repository.model.dataAccess.ssurank

import com.yourssu.ssurank.api.repository.model.entity.ssurank.Department
import com.yourssu.ssurank.api.repository.model.projection.ssurank.DepartmentTransporter
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*
import javax.transaction.Transactional

@Repository
interface DepartmentRepository : ExtendedRepository<Int, Department> {
    @Modifying
    @Transactional
    @Query(
        "update departments set shortened_name = :shortenedName " +
                "where college = :college and original_name = :originalName",
        nativeQuery = true
    )
    fun updateShortenedNames(college: String, originalName: String, shortenedName: String)

    fun findByCollegeAndOriginalName(college: String, originalName: String): Optional<Department>

    @Query(
        "select original_name as originalName, shortened_name as shortenedName from departments where college = :college",
        nativeQuery = true
    )
    fun findDepartmentsByCollege(college: String): List<DepartmentTransporter>
}