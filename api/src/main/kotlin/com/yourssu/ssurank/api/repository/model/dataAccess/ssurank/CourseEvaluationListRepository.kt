package com.yourssu.ssurank.api.repository.model.dataAccess.ssurank


import com.yourssu.ssurank.api.repository.model.entity.ssurank.CourseEvaluationList
import org.springframework.stereotype.Repository

@Repository
interface CourseEvaluationListRepository : ExtendedRepository<Int, CourseEvaluationList>