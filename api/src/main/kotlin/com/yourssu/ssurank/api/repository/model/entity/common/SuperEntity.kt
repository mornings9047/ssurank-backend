package com.yourssu.ssurank.api.repository.model.entity.common

import java.io.Serializable

interface SuperEntity<Key : Serializable> {
    val id: Key?
}