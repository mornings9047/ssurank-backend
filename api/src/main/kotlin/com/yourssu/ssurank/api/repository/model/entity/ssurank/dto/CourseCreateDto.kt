package com.yourssu.ssurank.api.repository.model.entity.ssurank.dto

import com.yourssu.ssurank.api.repository.model.entity.ssurank.entity.Grade
import com.yourssu.ssurank.api.repository.model.entity.ssurank.entity.Semester

data class CourseCreateDto(
        val title: String,
        val year:Int,
        val classification: String,
        val code: String,
        //val grade: String,
        val semester: Semester,
        val target: String,
        val rating: Float
)
