package com.yourssu.ssurank.api.response

import com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank.SearchCourseDto

data class SearchCourseResponse(
        val courses: Collection<SearchCourseDto>,
        override val message: String? = null
) : Response(message)