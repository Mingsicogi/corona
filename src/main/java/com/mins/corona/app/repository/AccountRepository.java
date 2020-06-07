package com.mins.corona.app.repository;

import com.mins.corona.app.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByName(String name);
}
