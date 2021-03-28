package com.api.nawf.domain.models;

import java.util.Date;

import com.api.nawf.domain.entities.RateEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RateAPIModel {
	private Date date;
	private Double price;
	private String currency;
	
	
	public RateEntity toRateEntity() {
		return RateEntity.builder().date(date).currency(currency).price(price).build();
	}
}
