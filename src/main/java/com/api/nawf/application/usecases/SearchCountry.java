package com.api.nawf.application.usecases;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.nawf.domain.entities.CountryEntity;
import com.api.nawf.domain.entities.IpCountryEntity;
import com.api.nawf.domain.entities.RateEntity;
import com.api.nawf.domain.services.BlackListService;
import com.api.nawf.domain.services.CountryService;
import com.api.nawf.domain.services.IpCountryService;
import com.api.nawf.domain.services.RatesService;
import com.api.nawf.infrastructure.exceptions.ApiException;

@Service
@Transactional
public class SearchCountry {

	@Autowired
	private IpCountryService ipCountryService;

	@Autowired
	private CountryService countryService;

	@Autowired
	private RatesService ratesService;

	@Autowired
	private BlackListService blackListService;

	/**
	 * Busca la información de un país y sus tarifas por la ip dada
	 * 
	 * @param ip
	 * @return Información de un país
	 * @throws ApiException
	 */
	public CountryEntity searchByIp(String ip) throws ApiException {
		if (ip != null && !this.blackListService.isIpBanned(ip)) {
			Optional<IpCountryEntity> ipOptional = this.ipCountryService.findOneByIp(ip);
			CountryEntity country = null;
			IpCountryEntity ipCountry = null;
			if (ipOptional.isPresent()) {
				ipCountry = ipOptional.get();
				country = ipCountry.getCountry();
			} else {
				country = this.countryService.createByIp(ip);
				ipCountry = IpCountryEntity.builder().ip(ip).country(country).build();
				this.ipCountryService.save(ipCountry);
			}
			List<RateEntity> rates = this.ratesService.findCurrentRatesByCountry(country);
			country.setRates(rates);
			return country;
		}
		throw new ApiException("Valor Ip Invalido");
	}

}
