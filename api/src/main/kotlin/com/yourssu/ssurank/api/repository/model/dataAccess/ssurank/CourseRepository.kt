package com.yourssu.ssurank.api.repository.model.dataAccess.ssurank

import com.yourssu.ssurank.api.repository.model.entity.ssurank.Course
import com.yourssu.ssurank.api.repository.model.entity.ssurank.Professor
import com.yourssu.ssurank.api.repository.model.projection.ssurank.CourseTransporter
import com.yourssu.ssurank.api.repository.model.projection.ssurank.SearchCourseTransporter
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.awt.print.Pageable

@Repository
interface CourseRepository : ExtendedRepository<Int, Course> {
    fun findByProfessor(Professor: Professor): List<Course>

    @Query("select avg(c.rating) from Course c where c.professor.id = :id")
    fun calculateProfessorRatings(@Param("id") id: Int): Float

    @Query("select (select count(*) from professors where rating >= :rating) * 100 / count(*) AS PERCENT from professors", nativeQuery = true)
    fun getPercentRank(rating: Float): Float

    @Query("select a from Course a where a.title = :title order by year desc, semester desc")
    fun searchCourseByTtile(@Param("title") title: String): List<SearchCourseTransporter>
}