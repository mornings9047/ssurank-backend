package com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank

import com.yourssu.ssurank.api.repository.model.projection.ssurank.SearchCourseTransporter

class SearchCourseDto(searchCourseTransporter: SearchCourseTransporter, professorName: String){
    val title = searchCourseTransporter.title
    val year = searchCourseTransporter.year
    val semester = searchCourseTransporter.semester
    val ranking = searchCourseTransporter.ranking
    var professor = professorName
}