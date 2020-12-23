package com.yourssu.ssurank.api.request

class ProfessorEvaluationRequest(
        val professorId: Int,
        val email: String,
        val type: String,
        val content: String
)