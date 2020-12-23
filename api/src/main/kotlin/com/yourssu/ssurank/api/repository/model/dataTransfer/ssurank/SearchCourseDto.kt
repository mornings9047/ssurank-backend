package com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank

class SearchCourseDto(
        val courseId: Int,
        val name : String,
        val department : String,
        val title : String,
        val year : Int,
        val semester : String,
        val ranking : Int
)