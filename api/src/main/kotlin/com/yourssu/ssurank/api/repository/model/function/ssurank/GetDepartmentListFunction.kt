package com.yourssu.ssurank.api.repository.model.function.ssurank

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.DepartmentDataAccessor
import com.yourssu.ssurank.api.repository.model.projection.ssurank.DepartmentTransporter

class GetDepartmentListFunction(
        private val departmentDataAccessor: DepartmentDataAccessor
) {
    private val colleges = arrayOf("IT대학", "공과대학", "인문대학", "자연과학대학", "법과대학", "사회과학대학", "경제통상대학", "경영대학", "베어드교양대학", "융특")

    fun getDepartmentList(): Map<String, List<DepartmentTransporter>> {
        val deptList: MutableMap<String, List<DepartmentTransporter>> = hashMapOf()
        for (college in colleges)
            deptList[college] = departmentDataAccessor.getDepartmentsByCollege(college)
        return deptList
    }
}