package com.yourssu.ssurank.api.request

import com.yourssu.ssurank.api.repository.model.entity.ssurank.ReportCategory

class ReportRequest(
        val id: Int,
        val reportCategory: ReportCategory,
        val content: String
)