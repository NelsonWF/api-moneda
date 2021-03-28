package com.api.nawf.domain.entities;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Almacena las tarifas de las monedas consultadas por país y fecha.
 * 
 * @author nwandurragaf
 *
 */
@Entity
@Table(name = "RATES", uniqueConstraints = { @UniqueConstraint(columnNames = { "DATE", "COUNTRY_CODE",
		"CURRENCY" }, name = "uk_date_country_currency_rate") })
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RateEntity {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "UUID", unique = true)
	@JsonIgnore
	private String uuid;

	@Basic
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date date;

	@Column(name = "CURRENCY", length = 5)
	private String currency;

	@Column(name = "PRICE", precision = 12, scale = 6)
	private Double price;

	@ManyToOne
	@JoinColumn(name = "COUNTRY_CODE")
	@JsonIgnore
	private CountryEntity country;

}
