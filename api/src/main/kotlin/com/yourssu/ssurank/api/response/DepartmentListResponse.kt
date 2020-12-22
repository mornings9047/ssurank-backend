package com.yourssu.ssurank.api.response

import com.yourssu.ssurank.api.repository.model.projection.ssurank.DepartmentTransporter

data class DepartmentListResponse(
        val list: Map<String, List<DepartmentTransporter>>,
        override val message: String? = null
) : Response(message)