package com.api.nawf.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import com.api.nawf.domain.entities.BlacklistEntity;
import com.api.nawf.domain.ports.BlacklistPort;

@Service
public class BlackListService {
	@Autowired
	private BlacklistPort blackListPort;

	/**
	 * Guarda una IP Baneada
	 * 
	 * @param blackList IP baneada a guardar
	 * @return IP Baneada
	 */
	public BlacklistEntity save(BlacklistEntity blackList) {
		return this.blackListPort.save(blackList);
	}

	/**
	 * Elimina una IP Baneada
	 * 
	 * @param ip IP a eliminar
	 */
	public void delete(String ip) {
		this.blackListPort.delete(ip);
	}

	/**
	 * Verifica si una IP se encuentra baneada.
	 * 
	 * @param ip
	 * @return true = baneada, false = no baneada
	 */
	public boolean isIpBanned(String ip) {
		return this.blackListPort.isIpBanned(ip);
	}

	/**
	 * Consulta una Ip Baneada
	 * 
	 * @param ip
	 * @return Ip Baneada
	 */
	public Optional<BlacklistEntity> findOne(String ip) {
		return this.blackListPort.findOne(ip);
	}
}
