package com.pizzadeliverybackend.controllers;

import com.pizzadeliverybackend.domain.Account;
import com.pizzadeliverybackend.model.Response;
import com.pizzadeliverybackend.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts/")
@RequiredArgsConstructor
@CrossOrigin
public class AccountController {
    private final AccountService accountService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createAccount(@RequestBody Account account) {
        accountService.createAccount(account);
    }

    @PostMapping("login")
    @ResponseStatus(HttpStatus.CREATED)
    public Response processLogin(@RequestBody Account account) {
        return accountService.processLogin(account);
    }

    @PostMapping("logout")
    @ResponseStatus(HttpStatus.CREATED)
    public Response processLogout(@RequestBody Account account) {
        return accountService.processLogout(account);
    }

    @GetMapping("{username}/checkLogin")
    @ResponseStatus(HttpStatus.OK)
    public Response checkLogin(@PathVariable String username) {
        return accountService.checkLogin(username);
    }
}
