package com.yourssu.ssurank.api.repository.model.dataAccess.ssurank

import com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank.GetHistoryCourseDto
import com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank.SearchCourseDto
import com.yourssu.ssurank.api.repository.model.entity.ssurank.Course
import com.yourssu.ssurank.api.repository.model.projection.ssurank.DetailedCourseTransporter
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CourseRepository : ExtendedRepository<Int, Course> {
    fun findCourseById(id: Int): Course

    @Query("select avg(c.rating) from Course c inner join CourseProfessor cp on c.id = cp.course.id inner join Professor p on p.id = cp.professor.id where p.id=:id")
    fun calculateProfessorRatings(id: Int): Float

    @Query("select (select count(*) from professors where rating >= :rating having count(*) >= 5) * 100 / count(*) AS PERCENT from professors having count(*) >= 5", nativeQuery = true)
    fun getProfessorPercentRank(rating: Float): Float

    @Query("select (select count(*) from courses where rating >= :rating and ranking <> 'U0') * 100 / count(*) AS PERCENT from courses where ranking <> 'U0'", nativeQuery = true)
    fun getCoursePercentRank(rating: Float): Float

    @Query(nativeQuery = true)
    fun searchCourseByTitle(title: String, page: Pageable): List<SearchCourseDto>

    @Query(nativeQuery = true)
    fun findDetailedCourseById(id: Int): DetailedCourseTransporter

    @Query(nativeQuery = true)
    fun getCourseHistoryByCodeAndName(code: String, name: String): List<GetHistoryCourseDto>

    @Query("select count(*) from " +
            "(select * from " +
            "(select name, code, c.id as courseId, original_name as department, title, year, semester, c.ranking from courses c " +
            "inner join course_professor cp on c.id = cp.course_id " +
            "inner join professors p on p.id = cp.professor_id " +
            "inner join departments d on p.department_id = d.id " +
            "where title COLLATE UTF8_GENERAL_CI like %:title% " +
            "group by name, year, semester " +
            "order by year desc, semester desc, c.rating desc, title asc, name asc) as result " +
            "group by name, code) as count", nativeQuery = true)
    fun countCourseByTitle(title: String): Int
}