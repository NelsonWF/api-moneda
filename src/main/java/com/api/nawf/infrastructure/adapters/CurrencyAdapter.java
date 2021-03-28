package com.api.nawf.infrastructure.adapters;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.api.nawf.domain.entities.CurrencyEntity;
import com.api.nawf.domain.ports.CurrencyPort;
import com.api.nawf.infrastructure.adapters.repositories.CurrencyJpaRepository;

@Repository
public class CurrencyAdapter implements CurrencyPort {

	@Autowired
	private CurrencyJpaRepository repository;
	
	@Override
	public CurrencyEntity save(CurrencyEntity currency) {
		return this.repository.save(currency);
	}

	@Override
	public Optional<CurrencyEntity> findOneByCode(String code) {
		return this.repository.findById(code);
	}

}
