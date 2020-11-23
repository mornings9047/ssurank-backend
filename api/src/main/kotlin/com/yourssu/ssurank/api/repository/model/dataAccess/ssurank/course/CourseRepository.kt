package com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.course

import com.yourssu.ssurank.api.repository.model.entity.ssurank.entity.Course
import com.yourssu.ssurank.api.repository.model.entity.ssurank.entity.CourseProfessor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CourseRepository : JpaRepository<Course, Int>{
    fun existsByCode(Code: String): Boolean
    fun findByCodeContains(Code: String): List<Course>
    fun findByTitleContains(Title: String): List<Course>
}