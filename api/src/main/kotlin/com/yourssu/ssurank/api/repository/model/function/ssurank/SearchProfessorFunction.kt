package com.yourssu.ssurank.api.repository.model.function.ssurank

import com.yourssu.ssurank.api.repository.model.entity.ssurank.SimplifiedProfessor
import reactor.core.publisher.Mono
/*
class SearchProfessorFunction(

       private val professorDataAccessor: ProfessorDataAccessor
) {
   fun searchProfessor(professorName: String): Mono<List<SimplifiedProfessor>> {
       return professorDataAccessor.findAllByProfessorName(professorName)
               .map { SimplifiedProfessor(it) }
               .collectList()
   }
}
*/