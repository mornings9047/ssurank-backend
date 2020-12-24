package com.yourssu.ssurank.api.repository.model.dataAccess.ssurank

import com.yourssu.ssurank.api.repository.model.entity.ssurank.CourseProfessor
import org.springframework.stereotype.Repository

@Repository
interface CourseProfessorRepository : ExtendedRepository<Int, CourseProfessor>