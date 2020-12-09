package com.yourssu.ssurank.api.repository.model.entity.common

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort

class Page(page: Int, size: Int, sortBy: String) : PageRequest(page, size, Sort.by(sortBy).descending())