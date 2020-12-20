package com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank

import com.yourssu.ssurank.api.repository.model.entity.ssurank.Ranking
import com.yourssu.ssurank.api.repository.model.entity.ssurank.Semester

class SearchCourseDto(
        val id: Int,
        val name : String,
        val code : String,
        val title : String,
        val year : Int,
        val semester : String,
        val ranking : String
)