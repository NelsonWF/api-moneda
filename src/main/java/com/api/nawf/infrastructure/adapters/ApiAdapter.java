package com.api.nawf.infrastructure.adapters;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.api.nawf.domain.models.CountryAPIModel;
import com.api.nawf.domain.models.CurrenciesAPIModel;
import com.api.nawf.domain.models.CurrencyAPIModel;
import com.api.nawf.domain.models.RateAPIModel;
import com.api.nawf.domain.ports.ApiPort;
import com.api.nawf.infrastructure.exceptions.ApiException;
import com.api.nawf.infrastructure.helpers.CalendarHelper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class ApiAdapter implements ApiPort {

	@Value("${api.fixer-key}")
	private String fixerKey;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CalendarHelper calendarHelper;

	@Override
	public Optional<CountryAPIModel> findCountryByIp(String ip) throws ApiException {
		try {
			String url = String.format("https://api.ip2country.info/ip?%s", ip);
			ResponseEntity<CountryAPIModel> response = this.restTemplate.getForEntity(url, CountryAPIModel.class);
			if (response.hasBody()) {
				return Optional.of(response.getBody());
			}
		} catch (RestClientException e) {
			log.error("Error recuperando el pais.", e);
			throw new ApiException("No se pudo obtener la información del país");
		}
		return Optional.empty();
	}

	@Override
	public Optional<CurrencyAPIModel> findCurrencyByIsoCode(String isoCode) throws ApiException {
		try {
			String url = String.format("https://restcountries.eu/rest/v2/alpha/%s?fields=currencies", isoCode);
			ResponseEntity<CurrenciesAPIModel> response = this.restTemplate.getForEntity(url, CurrenciesAPIModel.class);
			if (response.hasBody()) {
				CurrenciesAPIModel currencies = response.getBody();
				if (currencies != null && currencies.getCurrencies().length > 0) {
					return Optional.of(currencies.getCurrencies()[0]);
				}
			}
		} catch (RestClientException e) {
			log.error("Error recuperando la información de la moneda.", e);
			throw new ApiException("No se pudo obtener la información de la moneda del país");
		}
		return Optional.empty();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RateAPIModel> findRatesByCurrencyCode(String currencyCode) throws ApiException {
		List<RateAPIModel> rates = new ArrayList<>();
		try {
			String url = String.format("http://data.fixer.io/api/latest?access_key=%s&base=%s&symbols=EUR,USD",
					this.fixerKey, currencyCode);
			Map<String, Object> response = this.restTemplate.getForObject(url, HashMap.class);
			if (response != null) {
				if (!response.isEmpty() && !response.containsKey("error")) {
					Date date = this.calendarHelper.toDate(Long.valueOf(response.get("timestamp").toString()));
					Map<String, Object> rate = (Map<String, Object>) response.get("rates");
					for (Map.Entry<String, Object> entry : rate.entrySet()) {
						String key = entry.getKey();
						rates.add(new RateAPIModel(date, Double.valueOf(rate.get(key).toString()), key));
					}
					return rates;
				} else {
					Map<String, Object> error = (Map<String, Object>) response.get("error");
					throw new ApiException("No se pudo recuperar las tarifas actuales: " + error.get("type"),
							error.get("code").toString());
				}
			} else {
				throw new ApiException("No se pudo obtener una respuesta valida");
			}
		} catch (RestClientException e) {
			log.error("Error recuperando la información de la moneda.", e);
			throw new ApiException("No se pudo obtener las tarifas actuales");
		}
	}

}
