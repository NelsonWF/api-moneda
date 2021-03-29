package com.api.nawf.infrastructure.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.api.nawf.domain.entities.BlacklistEntity;
import com.api.nawf.domain.ports.BlacklistPort;
import com.api.nawf.infrastructure.adapters.repositories.BlackListJpaRepository;

@Repository
public class BlackListAdapter implements BlacklistPort {

	@Autowired
	private BlackListJpaRepository repository;

	@Override
	public BlacklistEntity save(BlacklistEntity blackList) {
		return this.repository.save(blackList);
	}

	@Override
	public void delete(String ip) {
		this.repository.deleteById(ip);
	}

	@Override
	public boolean isIpBanned(String ip) {
		return this.repository.existsById(ip);
	}

	@Override
	public Optional<BlacklistEntity> findOne(String ip) {
		return this.repository.findById(ip);
	}
}
