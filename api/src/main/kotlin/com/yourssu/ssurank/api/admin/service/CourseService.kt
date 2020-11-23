package com.yourssu.ssurank.api.admin.service
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.courseprofessor.CourseProfessorRepository
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.course.CourseRepository
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.professor.ProfessorRepository
import com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank.CourseCreateDto
import com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank.CourseProfessorCreateDto
import com.yourssu.ssurank.api.repository.model.entity.ssurank.entity.Course
import com.yourssu.ssurank.api.repository.model.entity.ssurank.entity.CourseProfessor
import com.yourssu.ssurank.api.repository.model.entity.ssurank.entity.Professor
import com.yourssu.ssurank.api.repository.model.entity.ssurank.entity.Semester
import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.HSSFSheet
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.File
import java.io.FileInputStream


@Service
class CourseService @Autowired constructor(val courseRepository: CourseRepository, val courseProfessorRepository: CourseProfessorRepository, val professorRepository: ProfessorRepository) {

    fun getFileList(): List<String> {
        val path = "Evaluate_Excel/"
        val dir = File(path)
        val fileList = dir.listFiles()
        val fileNames = arrayListOf<String>()

        for (file in fileList)
            if (file.isFile) fileNames.add(path.plus(file.name))

        return fileNames
    }

    fun readExcel() {
        for (path in getFileList()) {
            readSheet(path)
        }
    }

    fun readSheet(path: String) {
        val filePath = FileInputStream(path)
        val sheetCount = HSSFWorkbook(filePath).numberOfSheets
        for (i in 0 until sheetCount)
            readRecord(path, i)
    }

    fun readRecord(path: String, sheetAt: Int) {
        val filePath = FileInputStream(path)
        var columnIndex = 0
        var rowIndex = 1
        val sheet = HSSFWorkbook(filePath).getSheetAt(sheetAt)
        val rows = sheet.physicalNumberOfRows

        var courses = arrayListOf<CourseCreateDto>()

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

            var courseCreateDto = CourseCreateDto(code = code, year = year, semester = transSemester(semester),
                    title = title, lectureGrade = lectureGrade, professor = professor, college = college,
                    department = department, position = position, rating = rating, classification = " ", major = " ", target = " ")

            courses.add(courseCreateDto)
        }

        for(course in courses)
            courseSave(course)
    }

    fun transSemester(semester: Int) : Semester{
        if(semester % 2 == 1)
            return Semester.FIRST

        else return Semester.SECOND
    }

    fun courseSave(courseCreateDto: CourseCreateDto){
        val course = courseRepository.save(Course(title = courseCreateDto.title, year = courseCreateDto.year, classification = courseCreateDto.classification,
                                     code = courseCreateDto.code, semester = courseCreateDto.semester, target = courseCreateDto.target,
                                     rating = courseCreateDto.rating, major = courseCreateDto.major, professor = null,
                                     lectureGrade = courseCreateDto.lectureGrade))

        if(professorRepository.existsByNameAndCollegeAndDepartmentAndPosition(courseCreateDto.professor, courseCreateDto.college, courseCreateDto.department, courseCreateDto.position)){
            val professor = professorRepository.findByNameAndCollegeAndDepartmentAndPosition(courseCreateDto.professor, courseCreateDto.college, courseCreateDto.department, courseCreateDto.position)
            val courseProfessorCreateDto = CourseProfessorCreateDto(course, professor)
            courseProfessorRepository.save(CourseProfessor(course = courseProfessorCreateDto.course, professor = courseProfessorCreateDto.professor))
        }
    }
}

