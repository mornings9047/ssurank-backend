package com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank

import com.yourssu.ssurank.api.repository.model.projection.ssurank.DetailedCourseTransporter

class DetailedCourseDto(
        detailedCourseTransporter: DetailedCourseTransporter,
        historyCourses : List<GetHistoryCourseDto>
){
    val professorId = detailedCourseTransporter.professorId
    val code = detailedCourseTransporter.code
    val name = detailedCourseTransporter.name
    val department = detailedCourseTransporter.department
    val title = detailedCourseTransporter.title
    val ranking = detailedCourseTransporter.ranking
    val historyCourses = historyCourses
}
