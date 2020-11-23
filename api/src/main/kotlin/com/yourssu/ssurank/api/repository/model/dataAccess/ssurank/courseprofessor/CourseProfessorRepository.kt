package com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.courseprofessor

import com.yourssu.ssurank.api.repository.model.entity.ssurank.entity.Course
import com.yourssu.ssurank.api.repository.model.entity.ssurank.entity.CourseProfessor
import com.yourssu.ssurank.api.repository.model.entity.ssurank.entity.Professor
import org.springframework.data.jpa.repository.JpaRepository

interface CourseProfessorRepository : JpaRepository<CourseProfessor, Int>{
    fun findByCourse(course: Course) : List<CourseProfessor>
    fun findByProfessor(professor: Professor) : List<CourseProfessor>
}