package com.yourssu.ssurank.api.repository.model.dataAccess.ssurank

import com.yourssu.ssurank.api.repository.model.entity.ssurank.Course
import com.yourssu.ssurank.api.repository.model.entity.ssurank.Professor
import com.yourssu.ssurank.api.repository.model.projection.ssurank.SearchCourseTransporter
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository


@Repository
interface CourseRepository : ExtendedRepository<Int, Course> {
    fun findByProfessor(Professor: Professor): List<Course>

    @Query("select avg(c.rating) from Course c where c.professor.id = :id")
    fun calculateProfessorRatings(@Param("id") id: Int): Float

    @Query("select (select count(*) from professors where rating >= :rating and ranking <> 17) * 100 / count(*) AS PERCENT from professors where ranking <> 17", nativeQuery = true)
    fun getProfessorPercentRank(rating: Float): Float

    @Query("select (select count(*) from courses where rating >= :rating and ranking <> 17) * 100 / count(*) AS PERCENT from courses where ranking <> 17", nativeQuery = true)
    fun getCoursePercentRank(rating: Float): Float

    fun findAllByTitleContainsOrderByYearDescSemesterDescRatingDescTitleDesc(title: String, page: Pageable): List<SearchCourseTransporter>
}