package com.yourssu.ssurank.api.repository.model.projection.ssurank

import com.yourssu.ssurank.api.repository.model.entity.ssurank.StudentType
import java.util.*

interface CourseEvaluationTransporter {
    val type: StudentType
    val createdAt: Date
    val content: String
    val thumbsUp: Int
    val thumbsDown: Int
}