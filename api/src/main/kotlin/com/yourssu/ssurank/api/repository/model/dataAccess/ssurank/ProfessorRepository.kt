package com.yourssu.ssurank.api.repository.model.dataAccess.ssurank

import com.yourssu.ssurank.api.repository.model.entity.ssurank.Department
import com.yourssu.ssurank.api.repository.model.entity.ssurank.Professor
import com.yourssu.ssurank.api.repository.model.projection.ssurank.*
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ProfessorRepository : ExtendedRepository<Int, Professor> {
    fun existsByNameAndCollegeAndDepartmentAndPosition(name: String, college: String, department: Department, position: String): Boolean

    fun findByNameAndCollegeAndDepartmentAndPosition(name: String, college: String, department: Department, position: String): Professor

    @Query("select count(*) from courses c inner join course_professor cp on c.id = cp.professor_id where cp.professor_id = :id", nativeQuery = true)
    fun countCourse(id: Int): Int

    @Query("select p.id as id, name, department, position, ranking, count(cp.course_id) as courseCnt from professors p inner join course_professor cp on p.id = cp.professor_id  where department = :department group by p.id order by ranking asc, rating desc", nativeQuery = true)
    fun getProfessorsByDepartment(department: String, page: Pageable): List<SearchProfessorTransporter>

    @Query("select p.id as id, name, department, position, ranking from professors p " +
            "inner join course_professor cp on p.id = cp.professor_id " +
            "where ranking <> 'U0' " +
            "group by name, college, department, position " +
            "having count(cp.course_id) >= 15 " +
            "order by rating desc limit 10", nativeQuery = true)
    fun getTop10Honors(): List<ProfessorTransporter>

    @Query("select distinct p.id as id, name, department, position, ranking, count(cp.course_id) as courseCnt " +
            "from professors p " +
            "inner join course_professor cp on p.id = cp.professor_id " +
            "where name COLLATE UTF8_GENERAL_CI like %:name% " +
            "group by id " +
            "order by rating desc", nativeQuery = true)
    fun findAllByName(name: String, page: Pageable): List<SearchProfessorTransporter>

    @Query("select p.id as id, name, department, position, ranking, count(cp.course_id) as courseCnt " +
            "from professors p " +
            "inner join course_professor cp on p.id = cp.professor_id " +
            "where p.id = :id", nativeQuery = true)
    fun findDetailedProfessorById(id: Int): DetailedProfessorTransporter

    @Query("select ceiling( " +
            "((select count(*) from professors) " +
            "- (select count(*) from (select count(course_id) from course_professor " +
            "group by professor_id having count(course_id) < (select count(*) from course_professor where professor_id = :id)) as a)) " +
            "/ (select count(*) from professors) * 100) as percent", nativeQuery = true)
    fun getTopPercent(id: Int): Int

    @Query("select distinct year, semester from courses c " +
            "inner join course_professor cp on c.id = cp.course_id " +
            "inner join professors p on p.id = cp.professor_id " +
            "where p.id = :id", nativeQuery = true)
    fun getSessions(id: Int): List<SessionCourseTransporter>


    @Query("select courseId, ranking, name, department, title, year, semester from (" +
            "select c.rating as rating, c.id as courseId, c.ranking, name, department, title, year, semester " +
            "from courses c " +
            "inner join course_professor cp on c.id = cp.course_id " +
            "inner join professors p on p.id = cp.professor_id " +
            "where p.id = :id " +
            "group by title, code, year, semester " +
            "order by year desc, semester desc) as courses " +
            "group by title " +
            "order by rating desc", nativeQuery = true)
    fun getCoursesById(id: Int, page: Pageable): List<DetailedProfessorCoursesTransporter>

    fun findProfessorById(id: Int): Professor

    @Query("select count(*) from professors where name COLLATE UTF8_GENERAL_CI like %:name%", nativeQuery = true)
    fun countProfessorAllByName(name: String): Int

    @Query("select count(*) from professors where department = :department", nativeQuery = true)
    fun countDepartment(department: String): Int

    @Query("select count(*) from(select courseId, ranking, name, department, title, year, semester from (" +
            "select c.rating as rating, c.id as courseId, c.ranking, name, department, title, year, semester " +
            "from courses c " +
            "inner join course_professor cp on c.id = cp.course_id " +
            "inner join professors p on p.id = cp.professor_id " +
            "where p.id = :id " +
            "group by title, code, year, semester " +
            "order by year desc, semester desc) as courses " +
            "group by title " +
            "order by rating desc) as result", nativeQuery = true)
    fun countProfessorCourses(id: Int): Int
}