package com.yourssu.ssurank.api.repository.model.entity.ssurank

import com.yourssu.ssurank.api.repository.model.entity.ssurank.entity.Professor

data class SimplifiedProfessor(
        val id: Int?,
        val name: String,
        val department: String
) {
    constructor(transporter: Professor) : this(
            transporter.id,
            transporter.name,
            transporter.department
    )
}