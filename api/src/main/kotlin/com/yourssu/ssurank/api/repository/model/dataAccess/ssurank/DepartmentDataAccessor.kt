package com.yourssu.ssurank.api.repository.model.dataAccess.ssurank

import com.yourssu.ssurank.api.repository.model.entity.ssurank.Department
import com.yourssu.ssurank.api.repository.model.projection.ssurank.DepartmentTransporter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class DepartmentDataAccessor(
        @Autowired override var repository: DepartmentRepository
) : DataAccessorAdapterRepository<Int, Department, DepartmentRepository>() {

    fun getDepartmentsByCollege(college: String): List<DepartmentTransporter> {
        return repository.findDepartmentsByCollege(college)
    }
}