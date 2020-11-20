package com.yourssu.ssurank.api.service

import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.courseprofessor.CourseProfessorRepository
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.lecture.CourseRepository
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.professor.ProfessorRepository
import com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank.CourseCreateDto
import com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank.CourseProfessorCreateDto
import com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank.ProfessorCreateDto
import com.yourssu.ssurank.api.repository.model.entity.ssurank.entity.Course
import com.yourssu.ssurank.api.repository.model.entity.ssurank.entity.CourseProfessor
import com.yourssu.ssurank.api.repository.model.entity.ssurank.entity.Professor
import com.yourssu.ssurank.api.repository.model.entity.ssurank.entity.Semester
import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.CellType
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
        if (!professorRepository.existsByNameAndCollegeAndDepartmentAndPosition(professorCreateDto.name, professorCreateDto.college, professorCreateDto.department, professorCreateDto.position)) {
            professorRepository.save(Professor(name = professorCreateDto.name, college = professorCreateDto.college, department = professorCreateDto.department, position = professorCreateDto.position, rating = professorCreateDto.score, courses = null))
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
            readColumn(path,i)
    }

    fun readColumn(path: String, sheetAt: Int) {
        val filePath = FileInputStream(path)
        var columnIndex = 0
        val sheet = HSSFWorkbook(filePath).getSheetAt(sheetAt)
        val rows = sheet.physicalNumberOfRows
        val row: HSSFRow = sheet.getRow(0)
        var colleges  = arrayListOf<String>()
        var percentages = arrayListOf<String>()
        var scores = arrayListOf<String>()
        var professors = arrayListOf<String>()
        var courseCodes = arrayListOf<String>()
        var departments = arrayListOf<String>()
        var positions = arrayListOf<String>()

        while (true) {
            val cell = row.getCell(columnIndex++)
            if(cell.stringCellValue == "과목코드") courseCodes = readRow("courseCodes", path, sheetAt)
            else if (cell.stringCellValue == "교수명") professors = readRow("professors", path, sheetAt)
            else if (cell.stringCellValue == "단과대학") colleges = readRow("colleges", path, sheetAt)
            else if (cell.stringCellValue == "학과") departments = readRow("departments", path, sheetAt)
            else if (cell.stringCellValue == "직위") positions = readRow("positions", path, sheetAt)
            else if (cell.stringCellValue == "평점") scores = readRow("scores", path, sheetAt)
            else if (cell.stringCellValue == "백분율"){
                percentages = readRow("percentages", path, sheetAt)
                break
            }
        }

        for (i in 0 until rows - 1) {
            if (!professorRepository.existsByNameAndCollegeAndDepartmentAndPosition(professors[i],colleges[i],departments[i],positions[i])) {

                professorSave(ProfessorCreateDto(name = professors[i], college = colleges[i], department = departments[i],
                        position = positions[i], score = scores[i].toFloat()))

                if(courseRepository.existsByCode(courseCodes[i])) {
                    val course: Course? = courseRepository.findByCodeContains(courseCodes[i])
                    connectTable(course, professorRepository.findByNameAndCollegeAndDepartmentAndPosition(professors[i], colleges[i], departments[i], positions[i]))
                }
            }
        }
    }

    fun readRow(fieldName: String, path: String, sheetAt: Int): ArrayList<String> {
        val result = arrayListOf<String>()
        val filePath = FileInputStream(path)
        val sheet = HSSFWorkbook(filePath).getSheetAt(sheetAt)

        when (fieldName) {
            "courseCodes" -> {
                for (i in 1 until sheet.physicalNumberOfRows){
                    result.add(sheet.getRow(i).getCell(1).numericCellValue.toLong().toString())
                }
            }
            "professors" -> {
                for (i in 1 until sheet.physicalNumberOfRows) {
                    result.add(sheet.getRow(i).getCell(6).stringCellValue)
                }
            }
            "colleges" -> {
                for (i in 1 until sheet.physicalNumberOfRows){
                    result.add(sheet.getRow(i).getCell(7).stringCellValue)
                }
            }
            "departments" -> {
                for (i in 1 until sheet.physicalNumberOfRows)
                    result.add(sheet.getRow(i).getCell(8).stringCellValue)
            }
            "positions" -> {
                for (i in 1 until sheet.physicalNumberOfRows)
                    result.add(sheet.getRow(i).getCell(9).stringCellValue)
            }
            "scores" ->{
                for (i in 1 until sheet.physicalNumberOfRows)
                    result.add(sheet.getRow(i).getCell(10).numericCellValue.toFloat().toString())
            }
            "percentages" ->{
                for (i in 1 until sheet.physicalNumberOfRows)
                    result.add(sheet.getRow(i).getCell(11).numericCellValue.toLong().toString())
            }
        }
        return result
    }

    fun connectTable(course: Course?, professor: Professor){
        val courseProfessorCreateDto = CourseProfessorCreateDto(course, professor)
        courseProfessorRepository.save(CourseProfessor(course = courseProfessorCreateDto.course, professor = courseProfessorCreateDto.professor))
    }
}