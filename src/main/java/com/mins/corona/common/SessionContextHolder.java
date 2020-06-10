package com.mins.corona.common;

import com.mins.corona.app.entity.Account;

public class SessionContextHolder {

    private static final ThreadLocal<Account> threadLocalAccount = new ThreadLocal<>();

    public static Account getSessionContextHolder() {
        return threadLocalAccount.get();
    }

    public static void setSeesionContextHolder(Account account) {
        threadLocalAccount.set(account);
    }
}
