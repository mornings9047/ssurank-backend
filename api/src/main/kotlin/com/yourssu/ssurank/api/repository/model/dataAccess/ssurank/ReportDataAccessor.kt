package com.yourssu.ssurank.api.repository.model.dataAccess.ssurank

import com.yourssu.ssurank.api.repository.model.entity.ssurank.Report
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class ReportDataAccessor(
        @Autowired override var repository: ReportRepository
) : DataAccessorAdapterRepository<Int, Report, ReportRepository>() {
    fun save(report: Report) {
        repository.save(report)
    }
}