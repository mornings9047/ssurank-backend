package com.yourssu.ssurank.api.repository.model.entity.ssurank

import com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank.GetHistoryCourseDto
import com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank.SearchCourseDto
import com.yourssu.ssurank.api.repository.model.entity.common.SuperEntity
import com.yourssu.ssurank.api.repository.model.projection.ssurank.DetailedCourseTransporter
import org.hibernate.annotations.ColumnDefault
import javax.persistence.*

@SqlResultSetMappings(SqlResultSetMapping(name = "SearchCourseDto",
        classes = [
            ConstructorResult(targetClass = SearchCourseDto::class,
                    columns = [
                        ColumnResult(name = "courseId", type = Int::class),
                        ColumnResult(name = "name", type = String::class),
                        ColumnResult(name = "department", type = String::class),
                        ColumnResult(name = "title", type = String::class),
                        ColumnResult(name = "year", type = Int::class),
                        ColumnResult(name = "semester", type = String::class),
                        ColumnResult(name = "ranking", type = String::class)
                    ]
            )
        ]
),
        SqlResultSetMapping(name = "DetailedCourseTransporter",
                classes = [
                    ConstructorResult(targetClass = DetailedCourseTransporter::class,
                            columns = [
                                ColumnResult(name = "professorId", type = Int::class),
                                ColumnResult(name = "code", type = String::class),
                                ColumnResult(name = "name", type = String::class),
                                ColumnResult(name = "department", type = String::class),
                                ColumnResult(name = "title", type = String::class),
                                ColumnResult(name = "ranking", type = String::class)
                            ]
                    )
                ]
        ),
        SqlResultSetMapping(name = "GetHistoryCourseDto",
        classes = [
            ConstructorResult(targetClass = GetHistoryCourseDto::class,
                    columns = [
                        ColumnResult(name = "year", type = Int::class),
                        ColumnResult(name = "semester", type = String::class),
                        ColumnResult(name = "ranking", type = String::class),
                    ]
            )
        ]
)
)

@NamedNativeQueries(
NamedNativeQuery(
        name = "Course.searchCourseByTitle",
        query = "select * from (select name, c.id as courseId, department, title, year, semester, c.ranking from courses c inner join course_professor cp on c.id = cp.course_id inner join professors p on p.id = cp.professor_id where title COLLATE UTF8_GENERAL_CI like CONCAT('%',:title,'%') group by name, year, semester order by year desc, semester desc, c.rating desc, title asc, name asc) as result group by name",
        resultSetMapping = "SearchCourseDto"
), NamedNativeQuery(
        name = "Course.findDetailedCourseById",
        query = "select p.id as professorId, code, name, department, title, c.ranking from courses c inner join course_professor cp on c.id = cp.course_id inner join professors p on p.id = cp.professor_id where c.id = :id",
        resultSetMapping = "DetailedCourseTransporter"
), NamedNativeQuery(
        name = "Course.getCourseHistoryByCodeAndName",
        query = "select year, semester, c.ranking from courses c inner join course_professor cp on c.id = cp.course_id inner join professors p on p.id = cp.professor_id where code = :code and p.name like :name group by year, semester",
        resultSetMapping = "GetHistoryCourseDto"
)
)


@Entity
@Table(name = "courses")
data class Course(
        @field:Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        override val id: Int? = null,

        @Column(nullable = false)
        val title: String,

        @Column(nullable = true)
        val major: String?,

        @Column(nullable = false)
        val year: Int,

        @Column(nullable = false)
        @Enumerated(EnumType.STRING)
        val semester: Semester,

        @Column(nullable = false)
        @ColumnDefault("0")
        val lectureGrade: Int?,

        @Column(nullable = false, length = 15)
        @ColumnDefault("0")
        val code: String?,

        @Column(nullable = true)
        val classification: String?,

        @Column(nullable = true)
        val target: String?,

        @Column(nullable = true)
        val rating: Float = 0.0F,

        @Column(length = 2, nullable = true)
        @Enumerated(EnumType.STRING)
        var ranking: Ranking = Ranking.D0

) : SuperEntity<Int>