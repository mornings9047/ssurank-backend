package com.yourssu.ssurank.api.admin.service.function

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseRepository
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorRepository
import com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank.CreateCourseDto
import com.yourssu.ssurank.api.repository.model.entity.ssurank.entity.Course
import com.yourssu.ssurank.api.repository.model.entity.ssurank.entity.Semester
import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import java.io.FileInputStream

class ReadCourseFunction(val courseRepository: CourseRepository, val professorRepository: ProfessorRepository){

    private val getFileListFunction = GetFileListFunction()

    fun readExcel() {
        for (path in getFileListFunction.getFileList()) readSheet(path)
    }

    fun readSheet(path: String) {
        val filePath = FileInputStream(path)
        val sheetCount = HSSFWorkbook(filePath).numberOfSheets

        for (i in 0 until sheetCount) readRecord(path, i)
    }

    fun readRecord(path: String, sheetAt: Int) {
        val filePath = FileInputStream(path)
        var columnIndex = 0
        var rowIndex = 1
        val sheet = HSSFWorkbook(filePath).getSheetAt(sheetAt)
        val rows = sheet.physicalNumberOfRows
        var courses = arrayListOf<CreateCourseDto>()

        while (rowIndex < rows - 1) {
            var row: HSSFRow = sheet.getRow(rowIndex++)
            val code = row.getCell(columnIndex + 1).numericCellValue.toLong().toString()
            val year = row.getCell(columnIndex + 2).numericCellValue.toInt()
            val semester = row.getCell(columnIndex + 3).numericCellValue.toInt()
            val title = row.getCell(columnIndex + 4).stringCellValue.toString()
            val lectureGrade = row.getCell(columnIndex + 5).numericCellValue.toInt()
            val professor = row.getCell(columnIndex + 6).stringCellValue.toString()
            val college = row.getCell(columnIndex + 7).stringCellValue.toString()
            val department = row.getCell(columnIndex + 8).stringCellValue.toString()
            val position = row.getCell(columnIndex + 9).stringCellValue.toString()
            val rating = row.getCell(columnIndex + 10).numericCellValue.toFloat()

            val createCourseDto = CreateCourseDto(code = code, year = year, semester = parseSemester(semester),
                    title = title, lectureGrade = lectureGrade, professor = professor, college = college,
                    department = department, position = position, rating = rating, classification = null, major = null, target = null)

            courses.add(createCourseDto)
        }

        for(course in courses) saveCourse(course)
    }

    fun parseSemester(semester: Int) : Semester {
        if(semester % 2 == 1) return Semester.FIRST
        else return Semester.SECOND
    }

    private fun saveCourse(createCourseDto: CreateCourseDto) {
        val course = courseRepository.save(Course(title = createCourseDto.title, year = createCourseDto.year, classification = createCourseDto.classification,
                code = createCourseDto.code, semester = createCourseDto.semester, target = createCourseDto.target,
                rating = createCourseDto.rating, major = createCourseDto.major, lectureGrade = createCourseDto.lectureGrade,professor = null))

        if(professorRepository.existsByNameAndCollegeAndDepartmentAndPosition(createCourseDto.professor, createCourseDto.college, createCourseDto.department, createCourseDto.position)){
            course.professor = professorRepository.findByNameAndCollegeAndDepartmentAndPosition(createCourseDto.professor, createCourseDto.college, createCourseDto.department, createCourseDto.position)
            courseRepository.save(course)
        }

    }
}