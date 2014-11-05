package com.epam.jmp.unit.dao;

import com.epam.jmp.unit.model.Account;

import java.util.List;

/**
 * Created by Siarhei_Komlik on 11/4/14.
 */
public interface AccountDao {

    List<Account> getAccounts();
    List<Account> readSource();
    void writeSource(List<Account> accounts);
}
