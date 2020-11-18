package com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.lecture

import com.yourssu.ssurank.api.repository.model.entity.ssurank.Course
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CourseRepository : JpaRepository<Course, Int>{
    fun existsByCode(Code: String): Boolean
}