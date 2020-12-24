package com.yourssu.ssurank.api.repository.model.dataAccess.ssurank

import com.yourssu.ssurank.api.repository.model.entity.ssurank.Report
import org.springframework.stereotype.Repository

@Repository
interface ReportRepository : ExtendedRepository<Int, Report>