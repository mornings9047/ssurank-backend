package com.yourssu.ssurank.api.repository.model.entity.ssurank.entity

enum class Grade(bitfield: Int) {
    FIRST(1),
    SECOND(2),
    THIRD(4),
    FOURTH(8),
    FIFTH(16)
}