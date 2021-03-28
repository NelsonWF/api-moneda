package com.api.nawf.infrastructure.adapters.repositories;

import org.springframework.data.repository.CrudRepository;

import com.api.nawf.domain.entities.CurrencyEntity;

public interface CurrencyJpaRepository extends CrudRepository<CurrencyEntity, String> {

}
