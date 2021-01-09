package com.yourssu.ssurank.api.admin.service.function

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.DepartmentDataAccessor
import com.yourssu.ssurank.api.repository.model.entity.ssurank.Department
import java.io.File

class UpdateDepartmentFunction(
    private val departmentDataAccessor: DepartmentDataAccessor
) {
    private val path = "Department/departments.txt"

    fun readTxt() {
        val inputStream = File(path).inputStream()
        val buffer = inputStream.bufferedReader(Charsets.UTF_8)
        val lines = buffer.readLines()
        val departments = arrayListOf<Department>()
        var college = ""

        for (l in lines) {
            if (l == "\n")
                continue
            if (!l.startsWith("'", true)) {
                college = l
                continue
            }
            val names = l.replace("'", "").split(',')
            val originalName = names[0]
            val shortenedName = names[1]
            val department = Department(college = college, originalName = originalName, shortenedName = shortenedName)
            departments.add(department)
        }
        updateShortenedNames(departments)
    }

    private fun updateShortenedNames(departments: List<Department>) {
        for (department in departments)
            departmentDataAccessor.updateShortenedNames(department)
    }
}