package com.yourssu.ssurank.api.response

import com.yourssu.ssurank.api.repository.model.projection.ssurank.ProfessorTransporter

class HonorProfessorResponse(
        val professors: Collection<ProfessorTransporter>,
        override val message: String? = null
) : Response(message)