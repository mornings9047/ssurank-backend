package com.yourssu.ssurank.api.admin.service

import com.yourssu.ssurank.api.admin.service.function.ReadDepartmentFunction
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.DepartmentDataAccessor
import org.springframework.stereotype.Service

@Service
class AdminDepartmentService(
        departmentDataAccessor: DepartmentDataAccessor
) {
    private val readDepartmentFunction = ReadDepartmentFunction(departmentDataAccessor)
    fun readDepartment() {
        readDepartmentFunction.readTxt()
    }
}