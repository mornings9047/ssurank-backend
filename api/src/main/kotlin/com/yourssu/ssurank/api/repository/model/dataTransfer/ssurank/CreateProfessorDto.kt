package com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank

data class CreateProfessorDto(
        val name: String,
        val college: String,
        val department: String,
        val position: String,
        val score: Float
)