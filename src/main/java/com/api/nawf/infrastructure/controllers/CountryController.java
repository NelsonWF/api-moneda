package com.api.nawf.infrastructure.controllers;

import com.api.nawf.application.usecases.SearchCountry;
import com.api.nawf.domain.entities.CountryEntity;
import com.api.nawf.infrastructure.exceptions.ApiException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/country")
public class CountryController {

	@Autowired
	private SearchCountry searchCountry;

	@GetMapping(path = "/{ip:^\\d{1,3}+\\Q.\\E\\d{1,3}+\\Q.\\E\\d{1,3}+\\Q.\\E\\d{1,3}$}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public CountryEntity getCountry(@PathVariable String ip) throws ApiException {
		return this.searchCountry.searchByIp(ip);
	}
}
