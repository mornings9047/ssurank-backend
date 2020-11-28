package com.yourssu.ssurank.api.admin.service
import com.yourssu.ssurank.api.admin.service.function.FileListFunction
import com.yourssu.ssurank.api.admin.service.function.ReadCourseFunction
import com.yourssu.ssurank.api.admin.service.function.ReadProfessorFunction
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.course.CourseRepository
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.professor.ProfessorRepository
import com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank.CourseCreateDto
import com.yourssu.ssurank.api.repository.model.entity.ssurank.entity.Course
import com.yourssu.ssurank.api.repository.model.entity.ssurank.entity.Semester
import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.FileInputStream


@Service
class CourseService @Autowired constructor(val courseRepository: CourseRepository, val professorRepository: ProfessorRepository) {

    private val readCourseFunction = ReadCourseFunction(courseRepository, professorRepository)
    private val readProfessorFunction = ReadProfessorFunction(professorRepository, courseRepository)

    fun readCourse(){
        readCourseFunction.readExcel()
        readProfessorFunction.connCourses()
    }
}

