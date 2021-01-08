package com.yourssu.ssurank.api.admin.service.function

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.DepartmentDataAccessor
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorRepository
import com.yourssu.ssurank.api.repository.model.entity.ssurank.Department
import com.yourssu.ssurank.api.repository.model.entity.ssurank.Professor
import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import java.io.FileInputStream

class ReadProfessorFunction(
    private val departmentDataAccessor: DepartmentDataAccessor,
    private val professorRepository: ProfessorRepository,
) {
    private val getFileListFunction = GetFileListFunction()

    fun readExcel() {
        for (path in getFileListFunction.getFileList())
            readSheet(path)
    }

    private fun readSheet(path: String) {
        val filePath = FileInputStream(path)
        val sheetCount = HSSFWorkbook(filePath).numberOfSheets
        for (sheetAt in 0 until sheetCount)
            readRecord(path, sheetAt)
    }

    private fun readRecord(path: String, sheetAt: Int) {
        val filePath = FileInputStream(path)
        val sheet = HSSFWorkbook(filePath).getSheetAt(sheetAt)
        val rows = sheet.physicalNumberOfRows
        val professors = arrayListOf<Professor>()

        for (rowIndex in 1 until rows - 1) {
            val row: HSSFRow = sheet.getRow(rowIndex)
            val name = row.getCell(6).stringCellValue.toString()
            val college = row.getCell(7).stringCellValue.toString()
            var departmentName = row.getCell(8).stringCellValue.toString()
            if (departmentName == "베어드학부대학")
                departmentName = "베어드교양대학"
            var department = Department(college = college, originalName = departmentName, shortenedName = null)
            department = saveDepartment(department)

            val position = row.getCell(9).stringCellValue.toString()
            val professor =
                Professor(name = name, college = college, department = department, position = position)
            professors.add(professor)
        }
        for (professor in professors)
            saveProfessor(professor)
    }

    private fun saveDepartment(department: Department): Department {
        val dept =
            departmentDataAccessor.findByCollegeAndOriginalName(department.college, department.originalName)
        if (dept.isPresent)
            return dept.get()
        return departmentDataAccessor.save(department)
    }

    private fun saveProfessor(professor: Professor) {
        if (!professorRepository.existsByNameAndCollegeAndDepartmentAndPosition(
                professor.name,
                professor.college,
                professor.department,
                professor.position
            )
        ) {
            professorRepository.save(professor)
        }
    }
}