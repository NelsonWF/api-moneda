package com.api.nawf.infrastructure.adapters.repositories;

import org.springframework.data.repository.CrudRepository;

import com.api.nawf.domain.entities.CountryEntity;

public interface CountryJpaRepository extends CrudRepository<CountryEntity, String> {

}
