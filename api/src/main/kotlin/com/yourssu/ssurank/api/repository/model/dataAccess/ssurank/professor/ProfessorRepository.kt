package com.yourssu.ssurank.api.repository.model.dataAccess.ssurank.professor

import com.yourssu.ssurank.api.repository.model.entity.ssurank.Professor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProfessorRepository : JpaRepository<Professor, Int>{
}