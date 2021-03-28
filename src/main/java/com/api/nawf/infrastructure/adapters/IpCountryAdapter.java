package com.api.nawf.infrastructure.adapters;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.api.nawf.domain.entities.IpCountryEntity;
import com.api.nawf.domain.ports.IpCountryPort;
import com.api.nawf.infrastructure.adapters.repositories.IpCountryJpaRepository;

@Repository
public class IpCountryAdapter implements IpCountryPort {
	@Autowired
	private IpCountryJpaRepository repository;

	@Override
	public IpCountryEntity save(IpCountryEntity ipCountry) {
		return this.repository.save(ipCountry);
	}

	@Override
	public Optional<IpCountryEntity> findOneByIp(String ip) {
		return this.repository.findById(ip);
	}

}
