package com.yourssu.ssurank.api.repository.model.dataAccess.ssurank

import com.yourssu.ssurank.api.repository.model.entity.common.SuperEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean
import java.io.Serializable

@NoRepositoryBean
interface ExtendedRepository<Key : Serializable, Entity : SuperEntity<Key>> : JpaRepository<Entity, Key> {

}