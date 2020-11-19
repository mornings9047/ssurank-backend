package com.yourssu.ssurank.api.repository.model.entity.ssurank.dto

import com.yourssu.ssurank.api.repository.model.entity.ssurank.entity.*

class CourseCreateDto(
    val classification: Classification,
    val title: String,
    val professor: CourseProfessor,
    val code: String,
    val semester: Semester,
    //val credit:
    val grade: Grade,
    //val rating: Float
)