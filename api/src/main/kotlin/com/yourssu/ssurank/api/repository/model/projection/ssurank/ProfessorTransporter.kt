package com.yourssu.ssurank.api.repository.model.projection.ssurank

import com.yourssu.ssurank.api.repository.model.entity.ssurank.Ranking

interface ProfessorTransporter {
    val name: String
    val department: String
    val position: String
    val ranking: Ranking
}