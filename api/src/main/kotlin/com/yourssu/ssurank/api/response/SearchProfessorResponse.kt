package com.yourssu.ssurank.api.response

import com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank.SearchProfessorDto

data class SearchProfessorResponse(
        val professors: Collection<SearchProfessorDto>,
        override val message: String? = null
) : Response(message)