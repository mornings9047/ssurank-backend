package com.yourssu.ssurank.api.admin.controller

import com.yourssu.ssurank.api.admin.service.AdminReportService
import com.yourssu.ssurank.api.config.baseUrl
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("$baseUrl/admin/report")
class AdminReportController(
        val adminReportService: AdminReportService
) {
        @GetMapping("/delete/{evaluateId}/{reportType}")
        @ResponseStatus(HttpStatus.OK)
        fun deleteEvaluation(
                @PathVariable evaluateId: Int,
                @PathVariable reportType: String
        ){
                return adminReportService.deleteEvaluation(evaluateId, reportType)
        }
}

