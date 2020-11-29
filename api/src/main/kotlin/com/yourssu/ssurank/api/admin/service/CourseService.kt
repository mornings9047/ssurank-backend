package com.yourssu.ssurank.api.admin.service
import com.yourssu.ssurank.api.admin.service.function.ReadCourseFunction
import com.yourssu.ssurank.api.admin.service.function.ReadProfessorFunction
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.CourseRepository
import com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.ProfessorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class CourseService @Autowired constructor(val courseRepository: CourseRepository, val professorRepository: ProfessorRepository) {

    private val readCourseFunction = ReadCourseFunction(courseRepository, professorRepository)
    private val readProfessorFunction = ReadProfessorFunction(professorRepository, courseRepository)

    fun readCourse(){
        readCourseFunction.readExcel()
        readProfessorFunction.connCourses()
    }
}

