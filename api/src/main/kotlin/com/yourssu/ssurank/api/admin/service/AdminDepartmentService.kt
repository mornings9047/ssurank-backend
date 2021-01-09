package com.yourssu.ssurank.api.admin.service

import com.yourssu.ssurank.api.admin.service.function.UpdateDepartmentFunction
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.DepartmentDataAccessor
import org.springframework.stereotype.Service

@Service
class AdminDepartmentService(
    departmentDataAccessor: DepartmentDataAccessor
) {
    private val updateDepartmentFunction = UpdateDepartmentFunction(departmentDataAccessor)

    fun updateDepartment() {
        updateDepartmentFunction.readTxt()
    }
}