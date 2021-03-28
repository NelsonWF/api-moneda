package com.api.nawf.domain.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.nawf.domain.entities.CountryEntity;
import com.api.nawf.domain.models.CountryAPIModel;
import com.api.nawf.domain.models.CurrencyAPIModel;
import com.api.nawf.domain.ports.ApiPort;
import com.api.nawf.domain.ports.CountryPort;
import com.api.nawf.infrastructure.exceptions.ApiException;

@Service
public class CountryService {

	@Autowired
	private CountryPort countryPort;

	@Autowired
	private ApiPort apiPort;

	@Autowired
	private CurrencyService currencyService;

	/**
	 * Consulta las tarifas de un país por el código iso de este.
	 * 
	 * @param isoCode código iso del país
	 * @return País
	 */
	public Optional<CountryEntity> findOne(String isoCode) {
		return this.countryPort.findOneByIsoCode(isoCode);
	}

	/**
	 * Consulta y crea la información del país de la ip dada.
	 * 
	 * @param ip
	 * @return País creado
	 * @throws ApiException
	 */
	public CountryEntity createByIp(String ip) throws ApiException {
		CountryAPIModel countryApi = this.apiPort.findCountryByIp(ip).orElseThrow();
		CurrencyAPIModel currencyApi = this.apiPort.findCurrencyByIsoCode(countryApi.getCountryCode()).orElseThrow();
		CountryEntity country = countryApi.toCountry();
		country.setCurrency(this.currencyService.findOrSave(currencyApi.toCurrency()));
		return this.countryPort.save(country);
	}
}
