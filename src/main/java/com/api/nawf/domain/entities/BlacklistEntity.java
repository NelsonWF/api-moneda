package com.api.nawf.domain.entities;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * Almacena la información de las IP baneadas
 * 
 * @author nwandurragaf
 *
 */
@Entity
@Table(name = "BLACKLIST")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlacklistEntity {
	@Id
	@Column(name = "IP", length = 15)
	private String ip;

	@Basic
	@Temporal(TemporalType.DATE)
	@CreationTimestamp
	@Column(name = "BAN_DATE")
	private Date date;
}
