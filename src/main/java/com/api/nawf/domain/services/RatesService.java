package com.api.nawf.domain.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.nawf.domain.entities.CountryEntity;
import com.api.nawf.domain.entities.RateEntity;
import com.api.nawf.domain.models.RateAPIModel;
import com.api.nawf.domain.ports.RatesPort;
import com.api.nawf.infrastructure.exceptions.ApiException;

@Service
public class RatesService {

	@Autowired
	private RatesPort ratesPort;

	@Autowired
	private ApiService apiService;

	/**
	 * Busca las tarifas actuales del país dado.
	 * 
	 * @param country país
	 * @return Tarifas.
	 * @throws ApiException
	 */
	public List<RateEntity> findCurrentRatesByCountry(CountryEntity country) throws ApiException {
		List<RateEntity> rates = this.ratesPort.findCurrentRatesByCountry(country);
		if (rates.isEmpty()) {
			rates = this.findAndCreateCurrentRatesByCountry(country);
		}
		return rates;
	}

	/**
	 * Guardar tarifas
	 * 
	 * @param rates tarifas
	 * @return tarifas almacenadas
	 */
	public List<RateEntity> saveAll(List<RateEntity> rates) {
		return this.ratesPort.saveAll(rates);
	}

	/**
	 * Consulta y crea las tarifas actuales para país dado.
	 * 
	 * @param country país
	 * @return Lista de tarifas
	 * @throws ApiException
	 */
	private List<RateEntity> findAndCreateCurrentRatesByCountry(CountryEntity country) throws ApiException {
		List<RateAPIModel> ratesApi = this.apiService.findRatesByCurrencyCode(country.getCurrency().getCode());
		List<RateEntity> rates = new ArrayList<>();
		for (RateAPIModel rate : ratesApi) {
			RateEntity rateEntity = rate.toRateEntity();
			rateEntity.setCountry(country);
			rates.add(rateEntity);
		}
		return this.ratesPort.saveAll(rates);
	}
}
