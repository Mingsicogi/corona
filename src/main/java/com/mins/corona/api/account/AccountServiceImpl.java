package com.mins.corona.api.account;

import com.mins.corona.app.entity.Account;
import com.mins.corona.app.repository.AccountRepository;
import com.mins.corona.common.annotation.RedisCache;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    @RedisCache
    public Account getOneByName(String name) {
        return accountRepository.findByName(name);
    }
}
