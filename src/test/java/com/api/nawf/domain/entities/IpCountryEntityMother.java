package com.api.nawf.domain.entities;

import java.util.Optional;

public class IpCountryEntityMother {
    public static Optional<IpCountryEntity> getIpCountryOptional() {
        return Optional.of(getIpCountry());
    }

    public static IpCountryEntity getIpCountry() {
        return new IpCountryEntity("192.168.1.2", CountryEntityMother.getCountry());
    }

}
