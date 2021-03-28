package com.api.nawf.infrastructure.adapters.repositories;

import org.springframework.data.repository.CrudRepository;

import com.api.nawf.domain.entities.BlacklistEntity;

public interface BlackListJpaRepository extends CrudRepository<BlacklistEntity, String>{

}
