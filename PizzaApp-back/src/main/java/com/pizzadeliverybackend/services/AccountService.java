package com.pizzadeliverybackend.services;

import com.pizzadeliverybackend.domain.Account;
import com.pizzadeliverybackend.model.Response;

public interface AccountService {
    void createAccount(Account account);

    Response processLogin(Account account);

    Response processLogout(Account account);

    Response checkLogin(String username);
}
