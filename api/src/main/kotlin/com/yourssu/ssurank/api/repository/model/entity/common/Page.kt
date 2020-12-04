package com.yourssu.ssurank.api.repository.model.entity.common

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort

class Page(page: Int, size: Int) : PageRequest(page, size, Sort.by("ranking").descending())