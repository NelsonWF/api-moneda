package com.api.nawf.infrastructure.helpers;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class CalendarHelper {
	/**
	 * Convierte un timestamp dado en long en un Date
	 * 
	 * @param timestamp
	 * @return Date
	 */
	public Date toDate(long timestamp) {
		return new Timestamp(timestamp * 1000L);
	}

	/**
	 * Devuelve la hora actual del sistema.
	 * 
	 * @return fecha actual
	 */
	public Date now() {
		return new Date();
	}
}
