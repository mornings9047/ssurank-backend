package com.yourssu.ssurank.api.repository.model.dataAccess.ssurank

import com.yourssu.ssurank.api.repository.model.entity.ssurank.Professor
import com.yourssu.ssurank.api.repository.model.projection.ssurank.*
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ProfessorRepository : ExtendedRepository<Int, Professor> {
    fun existsByNameAndCollegeAndDepartmentAndPosition(Name: String, College: String, Department: String, Position: String): Boolean

    fun findByNameAndCollegeAndDepartmentAndPosition(Name: String, College: String, Department: String, Position: String): Professor

    @Query("select count(*) from courses c inner join course_professor cp on c.id = cp.professor_id where cp.professor_id = :id", nativeQuery = true)
    fun countCourse(id: Int): Int

    @Query("select p.id as id, name, department, position, ranking, count(cp.course_id) as courseCnt from professors p inner join course_professor cp on p.id = cp.professor_id  where department = :department group by p.id order by ranking asc, rating desc", nativeQuery = true)
    fun getProfessorsByDepartment(department: String, page: Pageable): List<SearchProfessorTransporter>

    @Query("select p.id as id, name, department, position, ranking from professors p inner join course_professor cp on p.id = cp.professor_id group by name, college, department, position having count(cp.course_id) >= 10 order by rating desc limit 10", nativeQuery = true)
    fun getProfessorsHavingCoursesOverTen(): List<ProfessorTransporter>

    @Query("select distinct p.id as id, name, department, position, ranking, count(cp.course_id) as courseCnt from professors p inner join course_professor cp on p.id = cp.professor_id where name like %:name% group by id order by rating desc", nativeQuery = true)
    fun findAllByName(name: String, page: Pageable): List<SearchProfessorTransporter>

    @Query("select p.id as id, name, department, position, ranking, count(cp.course_id) as courseCnt from professors p inner join course_professor cp on p.id = cp.professor_id  where p.id = :id", nativeQuery = true)
    fun findDetailedProfessorById(id: Int): DetailedProfessorTransporter

    @Query("select ceiling(\n" +
            "(select count(*) from (\n" +
            "select p.id, count(cp.course_id) as course from professors p\n" +
            "inner join course_professor cp on p.id = cp.professor_id\n" +
            "where p.id = :id group by p.id) as A, (\n" +
            "select cp.professor_id, count(cp.course_id) as courses from course_professor cp group by cp.professor_id\n" +
            ") as B\n" +
            "where A.course < B.courses)\n" +
            "/ count(*) * 100) as percent\n" +
            " from course_professor", nativeQuery = true)
    fun getTopPercent(id: Int): Int

    @Query("select distinct year, semester from courses c " +
            "inner join course_professor cp on c.id = cp.course_id " +
            "inner join professors p on p.id = cp.professor_id " +
            "where p.id = :id", nativeQuery = true)
    fun getSessions(id: Int): List<SessionCourseTransporter>


    @Query("select distinct c.ranking, name, department, title, year, semester from courses c " +
            "inner join course_professor cp on c.id = cp.course_id " +
            "inner join professors p on p.id = cp.professor_id " +
            "where p.id = :id " +
            "group by title " +
            "order by c.rating desc", nativeQuery = true)
    fun getCoursesById(id: Int, page: Pageable): List<DetailedProfessorCoursesTransporter>

    fun findProfessorById(id: Int): Professor
}