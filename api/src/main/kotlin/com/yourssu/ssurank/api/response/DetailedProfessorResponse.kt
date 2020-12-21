package com.yourssu.ssurank.api.response

import com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank.DetailedProfessorDto

data class DetailedProfessorResponse(
        val detailedProfessor: DetailedProfessorDto,
        override val message: String? = null
) : Response(message)