package com.mins.corona.api.account;

import com.mins.corona.api.account.dto.RedisDTO;
import com.mins.corona.app.repository.AccountRepository;
import com.mins.corona.common.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final RedisService redisService;
    private final AccountRepository accountRepository;

    @PostMapping(value = "/redis/test", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> redisTest(@RequestBody RedisDTO param) {
        redisService.put(param.getKey(), param);
        return ResponseEntity.ok(redisService.get(param.getKey(), RedisDTO.class));
    }
}
