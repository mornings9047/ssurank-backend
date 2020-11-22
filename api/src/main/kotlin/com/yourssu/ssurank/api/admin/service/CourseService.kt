package com.yourssu.ssurank.api.admin.service
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.courseprofessor.CourseProfessorRepository
import org.apache.poi.ss.usermodel.CellType
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.lecture.CourseRepository
import com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank.CourseCreateDto
import com.yourssu.ssurank.api.repository.model.entity.ssurank.entity.Course
import com.yourssu.ssurank.api.repository.model.entity.ssurank.entity.CourseProfessor
import com.yourssu.ssurank.api.repository.model.entity.ssurank.entity.Semester
import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.File
import java.io.FileInputStream


@Service
class CourseService @Autowired constructor(val courseRepository: CourseRepository, val courseProfessorRepository: CourseProfessorRepository){

    fun getFileList() : List<String>{
        val path = "TimeTable_Excel/"
        val dir = File(path)
        val fileList = dir.listFiles()
        val fileNames = arrayListOf<String>()

        for(file in fileList)
            if(file.isFile) fileNames.add(path.plus(file.name))

        return fileNames
    }

    fun courseSave(courseCreateDto: CourseCreateDto){
        var professor = listOf<CourseProfessor>()

        if(!courseRepository.existsByCode(courseCreateDto.code)) {

            val course = courseRepository.save(Course(
                    title = courseCreateDto.title, year = courseCreateDto.year, classification = courseCreateDto.classification,
                    code = courseCreateDto.code, semester = courseCreateDto.semester, target = courseCreateDto.target,
                    rating = courseCreateDto.rating, major = courseCreateDto.major, professor = professor))
        }
    }

    fun readExcel(){
        for(path in getFileList()){
            if(path.contains("Score"))
                readScore(path)
            else readSheet(path)
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
        var classifications = arrayListOf<String>()
        var courseNames = arrayListOf<String>()
        var majors = arrayListOf<String>()
        var professors = arrayListOf<String>()
        var courseCodes = arrayListOf<String>()
        var targets = arrayListOf<String>()

        while (true) {
            val cell = row.getCell(columnIndex++)
            if(cell.stringCellValue == "이수구분(주전공)") classifications = readRow("classifications", path, sheetAt)
            else if (cell.stringCellValue == "과목번호") courseCodes = readRow("courseCodes", path, sheetAt)
            else if (cell.stringCellValue == "과목명") courseNames = readRow("courseNames", path, sheetAt)
            else if (cell.stringCellValue == "개설학과") majors = readRow("majors", path, sheetAt)
            else if (cell.stringCellValue == "수강대상"){
                targets = readRow("targets", path, sheetAt)
                break
            }
        }

        for (i in 0 until rows - 1) {
            if (!courseRepository.existsByCode(courseCodes[i])) {
                courseSave(CourseCreateDto(courseNames[i], 2020, classifications[i], courseCodes[i],
                        Semester.FIRST, targets[i], 0.0F, majors[i]))
            }
        }
    }

    fun readRow(fieldName: String, path: String, sheetAt: Int): ArrayList<String> {
        val result = arrayListOf<String>()
        val filePath = FileInputStream(path)
        val sheet = HSSFWorkbook(filePath).getSheetAt(sheetAt)

        when (fieldName) {
            "classifications" -> {
                for (i in 1 until sheet.physicalNumberOfRows){
                    result.add(sheet.getRow(i).getCell(1).stringCellValue)
                }
            }

            "courseCodes" -> {
                for (i in 1 until sheet.physicalNumberOfRows) {
                    when (sheet.getRow(i).getCell(5).cellType) {
                        CellType.STRING -> result.add(sheet.getRow(i).getCell(5).stringCellValue.slice(0..7))
                        CellType.NUMERIC -> result.add(sheet.getRow(i).getCell(5).numericCellValue.toLong().toString().slice(0..7))
                        else -> result.add("")
                    }
                }
            }
            "courseNames" -> {
                for (i in 1 until sheet.physicalNumberOfRows)
                    result.add(sheet.getRow(i).getCell(6).stringCellValue)
            }
            "professors" -> {
                for (i in 1 until sheet.physicalNumberOfRows)
                    if(sheet.getRow(i).getCell(8).stringCellValue == "")
                        result.add("")
                    else result.add(parseName(sheet.getRow(i).getCell(8).stringCellValue))
            }
            "majors" -> {
                for (i in 1 until sheet.physicalNumberOfRows)
                    result.add(sheet.getRow(i).getCell(9).stringCellValue)
            }
            "targets" ->{
                for (i in 1 until sheet.physicalNumberOfRows)
                    result.add(sheet.getRow(i).getCell(14).stringCellValue)
            }
        }
        return result
    }

    fun parseName(name: String): String {
        val temp = name.split("\n")
        var result = ""
        for (i in temp) {
            if (!result.contains(i)) result = result.plus(i).plus(" ")
        }
        return result
    }

    fun readScore(path: String){
        val filePath = FileInputStream(path)
        var columnIndex = 5
        val sheet = HSSFWorkbook(filePath).getSheetAt(0)
        val rows = sheet.physicalNumberOfRows
        val row: HSSFRow = sheet.getRow(0)

    }

}