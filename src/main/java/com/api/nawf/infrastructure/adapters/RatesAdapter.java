package com.api.nawf.infrastructure.adapters;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.api.nawf.domain.entities.CountryEntity;
import com.api.nawf.domain.entities.RateEntity;
import com.api.nawf.domain.ports.RatesPort;
import com.api.nawf.infrastructure.adapters.repositories.RateJpaRepository;

@Repository
public class RatesAdapter implements RatesPort {

	@Autowired
	private RateJpaRepository repository;

	@Override
	public List<RateEntity> findCurrentRatesByCountry(CountryEntity country) {
		return this.repository.findCurrentRatesByCountry(country, new Date());
	}

	@Override
	public List<RateEntity> saveAll(List<RateEntity> rates) {
		List<RateEntity> result = new ArrayList<>();
		Iterable<RateEntity> ratesSave = this.repository.saveAll(rates);
		ratesSave.iterator().forEachRemaining(result::add);
		return result;
	}

}
