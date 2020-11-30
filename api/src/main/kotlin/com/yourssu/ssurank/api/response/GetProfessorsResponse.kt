package com.yourssu.ssurank.api.response

import com.yourssu.ssurank.api.repository.model.entity.ssurank.SimplifiedProfessor

data class GetProfessorsResponse(
        val professors: Collection<SimplifiedProfessor>,
        override val message: String? = null
) : Response(message)