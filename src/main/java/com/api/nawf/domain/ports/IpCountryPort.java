package com.api.nawf.domain.ports;

import java.util.Optional;

import com.api.nawf.domain.entities.IpCountryEntity;

public interface IpCountryPort {
	/**
	 * Almacena la información de la consulta relacionada a la IP.
	 * 
	 * @param ipCountry País a guardar
	 * @return IP guardada
	 */
	public IpCountryEntity save(IpCountryEntity ipCountry);

	/**
	 * Busca el registro del IP dada en la base de datos.
	 * 
	 * @param ip
	 * @return Información del país de la IP
	 */
	public Optional<IpCountryEntity> findOneByIp(String ip);
}
