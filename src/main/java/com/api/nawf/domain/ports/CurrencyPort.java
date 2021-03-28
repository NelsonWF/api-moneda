package com.api.nawf.domain.ports;

import java.util.Optional;

import com.api.nawf.domain.entities.CurrencyEntity;

public interface CurrencyPort {
	/**
	 * Guarda la información de la moneda
	 * 
	 * @param currency
	 * @return Moneda creada
	 */
	public CurrencyEntity save(CurrencyEntity currency);

	/**
	 * Busca una moneda por el código
	 * 
	 * @param code código de moneda
	 * @return Moneda
	 */
	public Optional<CurrencyEntity> findOneByCode(String code);
}
