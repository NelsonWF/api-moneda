package com.api.nawf.domain.entities;

import java.util.Date;
import java.util.Optional;

public class BlackListEntityMother {
    /**
     * Retorna una entidad de blackListEntity
     * 
     * @return
     */
    public static BlacklistEntity getIpBanned() {
        return new BlacklistEntity("192.168.1.1", new Date());
    }

    public static Optional<BlacklistEntity> getIpBannedOptional() {
        return Optional.of(getIpBanned());
    }
}
