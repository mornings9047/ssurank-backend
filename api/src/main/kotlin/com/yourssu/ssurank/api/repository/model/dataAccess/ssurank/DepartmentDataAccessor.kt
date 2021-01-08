package com.yourssu.ssurank.api.repository.model.dataAccess.ssurank

import com.yourssu.ssurank.api.repository.model.entity.ssurank.Department
import com.yourssu.ssurank.api.repository.model.projection.ssurank.DepartmentTransporter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class DepartmentDataAccessor(
    @Autowired override var repository: DepartmentRepository
) : DataAccessorAdapterRepository<Int, Department, DepartmentRepository>() {

    fun save(department: Department): Department {
        return repository.save(department)
    }

    fun findByCollegeAndOriginalName(college: String, originalName: String): Optional<Department> {
        return repository.findByCollegeAndOriginalName(college, originalName)
    }

    fun updateShortenedNames(department: Department) {
        return repository.updateShortenedNames(department.college, department.originalName, department.shortenedName!!)
    }

    fun getDepartmentsByCollege(college: String): List<DepartmentTransporter> {
        return repository.findDepartmentsByCollege(college)
    }
}