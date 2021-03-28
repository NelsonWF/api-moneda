package com.api.nawf.domain.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.nawf.domain.entities.CurrencyEntity;
import com.api.nawf.domain.ports.CurrencyPort;

@Service
public class CurrencyService {
	@Autowired
	private CurrencyPort currencyPort;

	/**
	 * Guarda la información de la moneda
	 * 
	 * @param currency
	 * @return Moneda creada
	 */
	public CurrencyEntity save(CurrencyEntity currency) {
		return this.currencyPort.save(currency);
	}

	/**
	 * Busca una moneda por el código
	 * 
	 * @param code código de moneda
	 * @return Moneda
	 */
	public Optional<CurrencyEntity> findOneByCode(String code) {
		return this.currencyPort.findOneByCode(code);
	}

	/**
	 * Busca o crea una moneda.
	 * 
	 * @param currency moneda a buscar o crear
	 * @return Moneda.
	 */
	public CurrencyEntity findOrSave(CurrencyEntity currency) {
		Optional<CurrencyEntity> currencyOptional = this.currencyPort.findOneByCode(currency.getCode());
		if (currencyOptional.isPresent()) {
			return currencyOptional.get();
		}
		return this.currencyPort.save(currency);
	}
}
