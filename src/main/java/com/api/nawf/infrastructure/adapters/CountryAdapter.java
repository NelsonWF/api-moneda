package com.api.nawf.infrastructure.adapters;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.api.nawf.domain.entities.CountryEntity;
import com.api.nawf.domain.ports.CountryPort;
import com.api.nawf.infrastructure.adapters.repositories.CountryJpaRepository;

@Repository
public class CountryAdapter implements CountryPort {
	
	@Autowired
	private CountryJpaRepository repository;

	@Override
	public Optional<CountryEntity> findOneByIsoCode(String isoCode) {
		return this.repository.findById(isoCode);
	}

	@Override
	public CountryEntity save(CountryEntity country) {
		return this.repository.save(country);
	}

}
