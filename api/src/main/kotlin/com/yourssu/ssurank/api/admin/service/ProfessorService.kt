package com.yourssu.ssurank.api.admin.service

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.courseprofessor.CourseProfessorRepository
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.course.CourseRepository
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.professor.ProfessorRepository
import com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank.CourseCreateDto
import com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank.CourseProfessorCreateDto
import com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank.ProfessorCreateDto
import com.yourssu.ssurank.api.repository.model.entity.ssurank.entity.Course
import com.yourssu.ssurank.api.repository.model.entity.ssurank.entity.CourseProfessor
import com.yourssu.ssurank.api.repository.model.entity.ssurank.entity.Professor
import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.springframework.stereotype.Service
import java.io.File
import java.io.FileInputStream

@Service
class ProfessorService(val professorRepository: ProfessorRepository, val courseProfessorRepository: CourseProfessorRepository, val courseRepository: CourseRepository) {

    fun getFileList() : List<String>{
        val path = "Evaluate_Excel/"
        val dir = File(path)
        val fileList = dir.listFiles()
        val fileNames = arrayListOf<String>()

        for(file in fileList)
            if(file.isFile) fileNames.add(path.plus(file.name))

        return fileNames
    }

    fun professorSave(professorCreateDto: ProfessorCreateDto) {
        val course = listOf<CourseProfessor>()
        if (!professorRepository.existsByNameAndCollegeAndDepartmentAndPosition(professorCreateDto.name, professorCreateDto.college, professorCreateDto.department, professorCreateDto.position)) {
            professorRepository.save(Professor(name = professorCreateDto.name, college = professorCreateDto.college, department = professorCreateDto.department, position = professorCreateDto.position, rating = professorCreateDto.score, courses = course))
        }
    }

    fun readExcel(){
        for(path in getFileList()){
            readSheet(path)
        }

    }

    fun readSheet(path: String){
        val filePath = FileInputStream(path)
        val sheetCount = HSSFWorkbook(filePath).numberOfSheets
        for(i in 0 until sheetCount)
            readRecord(path,i)
    }
    fun readRecord(path: String, sheetAt: Int) {
        val filePath = FileInputStream(path)
        var columnIndex = 0
        var rowIndex = 1
        val sheet = HSSFWorkbook(filePath).getSheetAt(sheetAt)
        val rows = sheet.physicalNumberOfRows

        var professors = arrayListOf<ProfessorCreateDto>()

        while (rowIndex < rows - 1) {
            var row: HSSFRow = sheet.getRow(rowIndex++)
            val code = row.getCell(columnIndex + 1).numericCellValue.toLong().toString()
            val name = row.getCell(columnIndex + 6).stringCellValue.toString()
            val college = row.getCell(columnIndex + 7).stringCellValue.toString()
            val department = row.getCell(columnIndex + 8).stringCellValue.toString()
            val position = row.getCell(columnIndex + 9).stringCellValue.toString()

            var professorCreateDto = ProfessorCreateDto(name = name, college = college, department = department, position = position, score = 0.0F)

            professors.add(professorCreateDto)
        }

        for(professor in professors)
            professorSave(professor)
    }
}