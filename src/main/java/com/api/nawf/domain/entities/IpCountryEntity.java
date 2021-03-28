package com.api.nawf.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Almacena las IP que han sido consultadas con su respectivo país.
 * 
 * @author nwandurragaf
 *
 */
@Entity
@Table(name = "IP_COUNTRY")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IpCountryEntity {
	@Id
	@Column(name = "IP", length = 15)
	private String ip;

	@ManyToOne
	@JoinColumn(name = "COUNTRY_CODE")
	private CountryEntity country;
}
