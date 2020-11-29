package com.yourssu.ssurank.api.repository.model.entity.ssurank.entity

enum class Classification(val className: String) {
    MAJOR_BASIC("전공기초"),
    MAJOR_ELECTIVE("전공선택"),
    MAJOR_REQUIRED("전공필수"),
    GENERAL_ELECTIVE("교양선택"),
    GENERAL_REQUIRED("교양필수"),
    TEACHING_CAREER("교직")
}