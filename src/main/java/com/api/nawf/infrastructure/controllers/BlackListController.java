package com.api.nawf.infrastructure.controllers;

import com.api.nawf.application.usecases.BlackListCases;
import com.api.nawf.domain.entities.BlacklistEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/blacklist")
public class BlackListController {
    @Autowired
    private BlackListCases blackListCases;

    @PostMapping(path = "/{ip:^\\d{1,3}+\\Q.\\E\\d{1,3}+\\Q.\\E\\d{1,3}+\\Q.\\E\\d{1,3}$}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public BlacklistEntity bannedIP(@PathVariable String ip) {
        return this.blackListCases.createBlackList(ip);
    }

    @DeleteMapping(path = "/{ip:^\\d{1,3}+\\Q.\\E\\d{1,3}+\\Q.\\E\\d{1,3}+\\Q.\\E\\d{1,3}$}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteIP(@PathVariable String ip) {
        this.blackListCases.deleteBlackList(ip);
    }
}
