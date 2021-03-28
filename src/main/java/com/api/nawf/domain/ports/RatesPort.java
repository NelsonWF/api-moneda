package com.api.nawf.domain.ports;

import java.util.List;

import com.api.nawf.domain.entities.CountryEntity;
import com.api.nawf.domain.entities.RateEntity;

public interface RatesPort {
	/**
	 * Busca las tarifas actuales del país dado.
	 * 
	 * @param country país
	 * @return Tarifas.
	 */
	public List<RateEntity> findCurrentRatesByCountry(CountryEntity country);

	/**
	 * Guardar tarifas
	 * 
	 * @param rates tarifas
	 * @return tarifas almacenadas
	 */
	public List<RateEntity> saveAll(List<RateEntity> rates);
}
