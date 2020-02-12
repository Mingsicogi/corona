package com.mins.corona.study.controller;

import com.mins.corona.study.dto.AccountDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/account")
public class Account {

    @PostMapping("/add")
    public ResponseEntity<String> add(@Valid AccountDTO param) {
        log.info("### [Start] /account/add ###");
        log.info("### request parameter : {} ###", param);
        log.info("### save ###");
        log.info("### [End] /account/add ###");

        return ResponseEntity.ok("success");
    }
}
