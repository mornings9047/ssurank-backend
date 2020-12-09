package com.yourssu.ssurank.api.admin.service.function

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseRepository
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorRepository
import com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank.CreateCourseDto
import com.yourssu.ssurank.api.repository.model.entity.ssurank.Course
import com.yourssu.ssurank.api.repository.model.entity.ssurank.Semester
import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import java.io.FileInputStream

class ReadCourseFunction(
        private val courseRepository: CourseRepository,
        private val professorRepository: ProfessorRepository
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
        val courses = arrayListOf<CreateCourseDto>()

        for (rowIndex in 1 until rows - 1) {
            val row: HSSFRow = sheet.getRow(rowIndex)
            val code = row.getCell(1).numericCellValue.toLong().toString()
            val year = row.getCell(2).numericCellValue.toInt()
            val semester = row.getCell(3).numericCellValue.toInt()
            val title = row.getCell(4).stringCellValue.toString()
            val lectureGrade = row.getCell(5).numericCellValue.toInt()
            val professor = row.getCell(6).stringCellValue.toString()
            val college = row.getCell(7).stringCellValue.toString()
            val department = row.getCell(8).stringCellValue.toString()
            val position = row.getCell(9).stringCellValue.toString()
            val rating = row.getCell(10).numericCellValue.toFloat()

            val createCourseDto = CreateCourseDto(code = code, year = year, semester = parseSemester(semester),
                    title = title, lectureGrade = lectureGrade, professor = professor, college = college,
                    department = department, position = position, rating = rating, classification = null, major = null, target = null)
            courses.add(createCourseDto)
        }

        for (course in courses)
            saveCourse(course)
    }

    private fun parseSemester(semester: Int): Semester {
        return if (semester % 2 == 1) Semester.FIRST
        else Semester.SECOND
    }

    private fun saveCourse(createCourseDto: CreateCourseDto) {
        val course = courseRepository.save(Course(title = createCourseDto.title, year = createCourseDto.year, classification = createCourseDto.classification,
                code = createCourseDto.code, semester = createCourseDto.semester, target = createCourseDto.target,
                rating = createCourseDto.rating, major = createCourseDto.major, lectureGrade = createCourseDto.lectureGrade, professor = null))

        if (professorRepository.existsByNameAndCollegeAndDepartmentAndPosition(createCourseDto.professor, createCourseDto.college, createCourseDto.department, createCourseDto.position)) {
            course.professor = professorRepository.findByNameAndCollegeAndDepartmentAndPosition(createCourseDto.professor, createCourseDto.college, createCourseDto.department, createCourseDto.position)
            courseRepository.save(course)
        }
    }
}