package com.mins.corona.api.account;

import com.mins.corona.app.entity.Account;
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

    @PostMapping(value = "/account/get", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> get(@RequestBody Account param) {

        Account accountInfo = redisService.get(param.getName(), Account.class);
        if(accountInfo == null) {
            accountInfo = accountRepository.findByName(param.getName());
            if(accountInfo != null) {
                redisService.put(accountInfo.getName(), accountInfo);
            }
        }

        return ResponseEntity.ok(accountInfo);
    }

    @PostMapping(value = "/account/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> add(@RequestBody Account param) {
        accountRepository.save(param);
        return ResponseEntity.ok("OK");
    }
}
