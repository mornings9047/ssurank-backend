package com.yourssu.ssurank.api.repository.model.dataAccess.ssurank

import com.yourssu.ssurank.api.repository.model.entity.ssurank.Professor
import org.springframework.stereotype.Repository

@Repository
interface ProfessorRepository : ExtendedRepository<Int, Professor> {

    fun findAllByProfessorName(professorName: String): List<Professor>

}