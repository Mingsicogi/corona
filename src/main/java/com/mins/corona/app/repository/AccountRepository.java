package com.mins.corona.app.repository;

import com.mins.corona.app.entity.Account;
import com.mins.corona.common.annotation.RedisCache;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @RedisCache
    Account findByName(String name);
}
