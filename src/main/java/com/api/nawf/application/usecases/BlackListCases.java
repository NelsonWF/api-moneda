package com.api.nawf.application.usecases;

import java.util.Optional;

import javax.transaction.Transactional;

import com.api.nawf.domain.entities.BlacklistEntity;
import com.api.nawf.domain.services.BlackListService;
import com.api.nawf.infrastructure.helpers.CalendarHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BlackListCases {
    @Autowired
    private BlackListService blackListService;

    @Autowired
    private CalendarHelper calendarHelper;

    /**
     * Agrega a una IP a la lista negra
     * 
     * @param ip
     * @return
     */
    public BlacklistEntity createBlackList(String ip) {
        Optional<BlacklistEntity> blackListOptional = this.blackListService.findOne(ip);
        if (blackListOptional.isPresent()) {
            return blackListOptional.get();
        } else {
            BlacklistEntity blackList = BlacklistEntity.builder().ip(ip).date(this.calendarHelper.now()).build();
            return this.blackListService.save(blackList);
        }
    }

    /**
     * Elimina una IP de la lista negra
     * 
     * @param ip
     */
    public void deleteBlackList(String ip) {
        this.blackListService.delete(ip);
    }
}
