package com.yourssu.ssurank.api.service
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.lecture.CourseRepository
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.professor.ProfessorRepository
import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.File
import java.io.FileInputStream

@Service
class CourseService @Autowired constructor(val courseRepository: CourseRepository, val professorRepository: ProfessorRepository){
    fun getFileList() : List<String>{
        val path = "Lecture_Excel/"
        val dir = File(path)
        val fileList = dir.listFiles()
        val fileNames = arrayListOf<String>()

        for(file in fileList)
            if(file.isFile) fileNames.add(path.plus(file.name))

        return fileNames
    }

    fun readExcel(){
        for(path in getFileList())
            readSheet(path)
    }

    fun readSheet(path: String){
        val filePath = FileInputStream(path)
        val sheetCount = HSSFWorkbook(filePath).numberOfSheets
        for(i in 0 until sheetCount)
            readColumn(path,i)
    }

    fun readColumn(path: String, sheetAt: Int){
        val filePath = FileInputStream(path)
        var columnIndex = 0
        val sheet = HSSFWorkbook(filePath).getSheetAt(sheetAt)
        val rows = sheet.physicalNumberOfRows
        val row: HSSFRow = sheet.getRow(0)
        var courseNames = arrayListOf<String>()
        val majors: ArrayList<String>
        var professors = arrayListOf<String>()
    }
}