package com.mins.corona.common.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RestController
public class Welcome {

    private static AtomicInteger counter = new AtomicInteger(1);

    @GetMapping("/welcome")
    public ResponseEntity<String> welcome() {
        log.info("HTTP Call URI : {}", "/welcome");
        return ResponseEntity.ok("HI! This is corona project. Call Count : " + counter.getAndIncrement());
    }

    @PostMapping(value = "/welcome", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResJson urlEncodeDataFormTest(WelcomeDTO param) {

        String pwd = "test";

        if(pwd.equals(param.getPwd())) {
            return new ResJson(true, new Data(UUID.randomUUID().toString()));
        } else {
            return new ResJson(false, null);
        }

    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    private static class ResJson {
        private boolean success;
        private Object data;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    private static class WelcomeDTO {
        private String pwd;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Data {
        private String token;
    }
}
