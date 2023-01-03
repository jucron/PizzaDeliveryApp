package com.pizzadeliverybackend.services;

import com.pizzadeliverybackend.domain.Account;
import com.pizzadeliverybackend.model.Response;
import com.pizzadeliverybackend.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final ProcessService processService;
    @Override
    public void createAccount(Account account) {
        log.info("createAccount executed");
        accountRepository.save(account);
    }

    @Override
    public Response processLogin(Account account) {
        log.info("processLogin executed");
        //todo: check login existence and return error if not found
        //todo: return username
        Optional<Account> existentAccountOptional = accountRepository.findByUsername(account.getUsername());
        if (existentAccountOptional.isEmpty() || !Objects.equals(existentAccountOptional.get().getPassword(), account.getPassword())) {
            return null;} //return null if there is no match
        Account existentAccount = existentAccountOptional.get();
        existentAccount.setLoginStatus("logged");
        accountRepository.save(existentAccount);

        return processService.getTaskDefKey(account.getUsername());
    }

    @Override
    public Response processLogout(Account account) {
        Account existentAccount = accountRepository.findByUsername(account.getUsername()).get();
        existentAccount.setLoginStatus("not_logged");
        accountRepository.save(existentAccount);
        return new Response()
                .withMessage(account.getUsername());
    }

    @Override
    public Response checkLogin(String username) {
        Account existingAccount = accountRepository.findByUsername(username).orElse(null);
        log.info("CheckLogin: "+ existingAccount==null ? "Username not found in DB" : "username found with loginStatus: "+ existingAccount.getLoginStatus());
        return (existingAccount==null ? null : new Response().withMessage(existingAccount.getLoginStatus()));
    }
}

