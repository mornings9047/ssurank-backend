package com.yourssu.ssurank.api.admin.controller

import com.yourssu.ssurank.api.admin.service.AdminReportService
import com.yourssu.ssurank.api.config.baseUrl
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("$baseUrl/admin")
class AdminReportController(
    val adminReportService: AdminReportService
) {
    @DeleteMapping("/delete/{reportType}/{evaluateId}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteEvaluation(
        @PathVariable reportType: String,
        @PathVariable evaluateId: Int
    ) {
        return adminReportService.deleteEvaluation(reportType, evaluateId)
    }
}

