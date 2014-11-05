package com.epam.jmp.unit.service;

import com.epam.jmp.unit.model.Account;

import java.util.List;

/**
 * Created by Siarhei_Komlik on 11/3/14.
 */
public interface AccountService {

    List<Account> getAccounts();
    void readSource();
    void writeSource(List<Account> accounts);

}
