package com.yourssu.ssurank.api.repository.model.dataTransfer.ssurank

import com.yourssu.ssurank.api.repository.model.projection.ssurank.SearchProfessorTransporter

class SearchProfessorDto(searchProfessorTransporter: SearchProfessorTransporter) {
    val name = searchProfessorTransporter.name
    val department = searchProfessorTransporter.department
    val position = searchProfessorTransporter.position
    val ranking = searchProfessorTransporter.ranking
//  val courses = searchProfessorTransporter.courses!!.size
}