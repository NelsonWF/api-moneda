package com.api.nawf.infrastructure.adapters;

import java.util.ArrayList;
import java.util.List;

import com.api.nawf.domain.entities.CountryEntity;
import com.api.nawf.domain.entities.RateEntity;
import com.api.nawf.domain.ports.RatesPort;
import com.api.nawf.infrastructure.adapters.repositories.RateJpaRepository;
import com.api.nawf.infrastructure.helpers.CalendarHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RatesAdapter implements RatesPort {

	@Autowired
	private RateJpaRepository repository;

	@Autowired
	private CalendarHelper calendarHelper;

	@Override
	public List<RateEntity> findCurrentRatesByCountry(CountryEntity country) {
		return this.repository.findCurrentRatesByCountry(country, this.calendarHelper.now());
	}

	@Override
	public List<RateEntity> saveAll(List<RateEntity> rates) {
		List<RateEntity> result = new ArrayList<>();
		Iterable<RateEntity> ratesSave = this.repository.saveAll(rates);
		ratesSave.iterator().forEachRemaining(result::add);
		return result;
	}

}
