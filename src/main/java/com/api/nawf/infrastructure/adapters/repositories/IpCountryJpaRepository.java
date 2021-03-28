package com.api.nawf.infrastructure.adapters.repositories;

import org.springframework.data.repository.CrudRepository;

import com.api.nawf.domain.entities.IpCountryEntity;

public interface IpCountryJpaRepository extends CrudRepository<IpCountryEntity, String>{

}
