package com.api.nawf.domain.ports;

import java.util.Optional;

import com.api.nawf.domain.entities.CountryEntity;

public interface CountryPort {
	/**
	 * Consulta las tarifas de un país por el código iso de este.
	 * 
	 * @param isoCode código iso del país
	 * @return país
	 */
	public Optional<CountryEntity> findOneByIsoCode(String isoCode);

	/**
	 * Guarda un país en base de datos.
	 * 
	 * @param country país a guardar
	 * @return país guardado
	 */
	public CountryEntity save(CountryEntity country);
}
