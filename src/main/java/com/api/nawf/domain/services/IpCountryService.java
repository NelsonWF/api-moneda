package com.api.nawf.domain.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.nawf.domain.entities.IpCountryEntity;
import com.api.nawf.domain.ports.IpCountryPort;

@Service
public class IpCountryService {
	@Autowired
	private IpCountryPort ipCountryPort;

	/**
	 * Almacena la información de la consulta relacionada a la IP.
	 * 
	 * @param ipCountry país a guardar
	 * @return IP guardada
	 */
	public IpCountryEntity save(IpCountryEntity ipCountry) {
		return this.ipCountryPort.save(ipCountry);
	}

	/**
	 * Busca el registro del IP dada en la base de datos.
	 * 
	 * @param ip
	 * @return información del país de la IP
	 */
	public Optional<IpCountryEntity> findOneByIp(String ip) {
		return this.ipCountryPort.findOneByIp(ip);
	}
}
