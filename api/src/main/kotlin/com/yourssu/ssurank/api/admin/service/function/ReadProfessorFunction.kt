package com.yourssu.ssurank.api.admin.service.function

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseRepository
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorRepository
import com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank.CreateProfessorDto
import com.yourssu.ssurank.api.repository.model.entity.ssurank.Professor
import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import java.io.FileInputStream

class ReadProfessorFunction(
        private val professorRepository: ProfessorRepository,
        private val courseRepository: CourseRepository
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
        val professors = arrayListOf<CreateProfessorDto>()

        for (rowIndex in 1 until rows - 1) {
            val row: HSSFRow = sheet.getRow(rowIndex)
            val name = row.getCell(6).stringCellValue.toString()
            val college = row.getCell(7).stringCellValue.toString()
            val department = row.getCell(8).stringCellValue.toString()
            val position = row.getCell(9).stringCellValue.toString()
            val createProfessorDto = CreateProfessorDto(name = name, college = college, department = department, position = position, score = 0.0F)
            professors.add(createProfessorDto)
        }
        for (professor in professors)
            saveProfessor(professor)
    }

    private fun saveProfessor(professorCreateDto: CreateProfessorDto) {
        if (!professorRepository.existsByNameAndCollegeAndDepartmentAndPosition(professorCreateDto.name, professorCreateDto.college, professorCreateDto.department, professorCreateDto.position)) {
            professorRepository.save(Professor(name = professorCreateDto.name, college = professorCreateDto.college, department = professorCreateDto.department, position = professorCreateDto.position, rating = professorCreateDto.score))
        }
    }
}