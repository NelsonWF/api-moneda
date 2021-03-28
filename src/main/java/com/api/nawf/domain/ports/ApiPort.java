package com.api.nawf.domain.ports;

import java.util.List;
import java.util.Optional;

import com.api.nawf.domain.models.CountryAPIModel;
import com.api.nawf.domain.models.CurrencyAPIModel;
import com.api.nawf.domain.models.RateAPIModel;
import com.api.nawf.infrastructure.exceptions.ApiException;

public interface ApiPort {
	/**
	 * Consulta la información del país por la IP al API
	 * <link>https://ip2country.info/</link>
	 * 
	 * @param ip
	 * @return Información del país
	 * @throws ApiException
	 */
	public Optional<CountryAPIModel> findCountryByIp(String ip) throws ApiException;

	/**
	 * Consulta la moneda del país por el código ISO a la API
	 * <link>http://restcountries.eu/</link>
	 * 
	 * @param isoCode código ISO asignado al país.
	 * @return Información de la moneda del país consultado.
	 * @throws ApiException
	 */
	public Optional<CurrencyAPIModel> findCurrencyByIsoCode(String isoCode) throws ApiException;

	/**
	 * Consulta la tarifa actual en (USD, EUR) de la moneda del país por el código
	 * ISO de este, a la API <link>https://fixer.io/</link>
	 * 
	 * @param currencyCode código de moneda asignado al país.
	 * @return Tarifas de la moneda.
	 * @throws ApiException
	 */
	public List<RateAPIModel> findRatesByCurrencyCode(String currencyCode) throws ApiException;
}
