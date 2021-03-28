package com.api.nawf.domain.models;

import com.api.nawf.domain.entities.CountryEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryAPIModel {
	private String countryCode;
	private String countryCode3;
	private String countryName;
	private String countryEmoji;
	
	public CountryEntity toCountry() {
		return CountryEntity.builder().code(countryCode).name(countryName).build();
	}
}