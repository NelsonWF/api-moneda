package com.api.nawf.domain.entities;

import java.util.Optional;

public class CurrencyEntityMother {
    public static CurrencyEntity getCurrency() {
        return new CurrencyEntity("COP", "Colombia", "$");
    }

    public static Optional<CurrencyEntity> getCurrencyOptional() {
        return Optional.of(getCurrency());
    }
}
