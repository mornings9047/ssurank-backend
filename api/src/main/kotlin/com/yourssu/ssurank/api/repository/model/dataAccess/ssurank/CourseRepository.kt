package com.yourssu.ssurank.api.repository.model.dataAccess.ssurank

import com.yourssu.ssurank.api.repository.model.entity.ssurank.entity.Course
import com.yourssu.ssurank.api.repository.model.entity.ssurank.entity.Professor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CourseRepository : JpaRepository<Course, Int>{
    fun existsByCode(Code: String): Boolean
    fun findByCodeContains(Code: String): List<Course>
    fun findByTitleContains(Title: String): List<Course>
    fun findByProfessor(Professor: Professor): List<Course>
}