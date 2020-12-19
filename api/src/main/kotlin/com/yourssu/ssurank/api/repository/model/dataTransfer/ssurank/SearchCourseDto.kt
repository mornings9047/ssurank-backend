package com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank

import com.yourssu.ssurank.api.repository.model.entity.ssurank.Ranking
import com.yourssu.ssurank.api.repository.model.entity.ssurank.Semester

class SearchCourseDto(
        var name : String,
        var code : String,
        var title : String,
        var year : Int,
        var semester : String,
        var ranking : String
){
}