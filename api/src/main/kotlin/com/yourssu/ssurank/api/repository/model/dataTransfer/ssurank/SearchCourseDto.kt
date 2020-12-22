package com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank

class SearchCourseDto(
        val id: Int,
        val name : String,
        val code : String,
        val title : String,
        val year : Int,
        val semester : String,
        val ranking : String
)