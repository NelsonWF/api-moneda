package com.api.nawf.infrastructure.adapters.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.api.nawf.domain.entities.CountryEntity;
import com.api.nawf.domain.entities.RateEntity;

public interface RateJpaRepository extends CrudRepository<RateEntity, String> {
	/**
	 * Consulta las tarifas actuales por país.
	 * 
	 * @param country
	 * @param date
	 * @return
	 */
	@Query("SELECT r FROM RateEntity r WHERE r.country = :country AND r.date = :date")
	public List<RateEntity> findCurrentRatesByCountry(@Param("country") CountryEntity country,
			@Param("date") Date date);
}
