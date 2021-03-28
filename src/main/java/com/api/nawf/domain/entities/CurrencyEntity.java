package com.api.nawf.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CURRENCIES")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyEntity {
	@Id
	@Column(name = "CODE", length = 5)
	private String code;

	@Column(name = "NAME", length = 50)
	private String name;

	@Column(name = "SYMBOL", length = 5)
	private String symbol;
}
