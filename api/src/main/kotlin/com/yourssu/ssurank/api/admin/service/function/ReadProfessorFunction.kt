package com.yourssu.ssurank.api.admin.service.function

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseRepository
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorRepository
import com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank.CreateProfessorDto
import com.yourssu.ssurank.api.repository.model.entity.ssurank.entity.Professor
import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import java.io.FileInputStream

class ReadProfessorFunction(val professorRepository: ProfessorRepository, val courseRepository: CourseRepository){

    private val getFileListFunction = GetFileListFunction()

    private fun saveProfessor(professorCreateDto: CreateProfessorDto) {
        if (!professorRepository.existsByNameAndCollegeAndDepartmentAndPosition(professorCreateDto.name, professorCreateDto.college, professorCreateDto.department, professorCreateDto.position)) {
            professorRepository.save(Professor(name = professorCreateDto.name, college = professorCreateDto.college, department = professorCreateDto.department, position = professorCreateDto.position, rating = professorCreateDto.score, courses = null))
        }
    }

    fun connectCourseAndProfessor(){
        val professors = professorRepository.findAll()

        for (professor in professors) {
            val courses = courseRepository.findByProfessor(professor)
            professor.courses = courses
            professorRepository.save(professor)
        }
    }

    fun readExcel(){
        for(path in getFileListFunction.getFileList()) readSheet(path)
    }

    fun readSheet(path: String){
        val filePath = FileInputStream(path)
        val sheetCount = HSSFWorkbook(filePath).numberOfSheets

        for(i in 0 until sheetCount) readRecord(filePath,i)
    }

    fun readRecord(filePath: FileInputStream, sheetAt: Int) {
        val columnIndex = 0
        var rowIndex = 1
        val sheet = HSSFWorkbook(filePath).getSheetAt(sheetAt)
        val rows = sheet.physicalNumberOfRows
        var professors = arrayListOf<CreateProfessorDto>()

        while (rowIndex < rows - 1) {
            var row: HSSFRow = sheet.getRow(rowIndex++)
            val name = row.getCell(columnIndex + 6).stringCellValue.toString()
            val college = row.getCell(columnIndex + 7).stringCellValue.toString()
            val department = row.getCell(columnIndex + 8).stringCellValue.toString()
            val position = row.getCell(columnIndex + 9).stringCellValue.toString()

            var createProfessorDto = CreateProfessorDto(name = name, college = college, department = department, position = position, score = 0.0F)
            professors.add(createProfessorDto)
        }

        for(professor in professors) saveProfessor(professor)
    }
}