package com.mins.corona.common.filter;

import com.mins.corona.app.entity.Account;
import com.mins.corona.common.SessionContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Slf4j
@Component
public class GlobalDataSettingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        log.info(">>>>>> Start Filter~!");


        Account account = new Account();
        account.setName("Guest");
        account.setAddress("");
        account.setAge(0);

        SessionContextHolder.setSeesionContextHolder(account);

        chain.doFilter(request, response);
    }
}