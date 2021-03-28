package com.api.nawf.domain.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Almacena la información de los paises consultados.
 * 
 * @author nwandurragaf
 *
 */
@Entity
@Table(name = "COUNTRIES")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryEntity {

	@Id
	@Column(name = "CODE", length = 3)
	private String code;

	@Column(name = "NAME", length = 100)
	private String name;

	@ManyToOne
	@JoinColumn(name = "CURRENCY")
	private CurrencyEntity currency;

	@OneToMany(mappedBy = "country")
	private List<RateEntity> rates;

}
