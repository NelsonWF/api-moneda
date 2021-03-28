package com.api.nawf.domain.ports;

import com.api.nawf.domain.entities.BlacklistEntity;

public interface BlacklistPort {
	/**
	 * Guarda una IP Baneada
	 * @param blackList IP baneada a guardar
	 * @return IP Baneada
	 */
	public BlacklistEntity save(BlacklistEntity blackList);
	
	/**
	 * Elimina una IP Baneada
	 * @param ip IP a eliminar
	 */
	public void delete(String ip);
	
	/**
	 * Verifica si una IP se encuentra baneada.
	 * @param ip
	 * @return true = baneada, false = no baneada
	 */
	public boolean isIpBanned(String ip);
}
