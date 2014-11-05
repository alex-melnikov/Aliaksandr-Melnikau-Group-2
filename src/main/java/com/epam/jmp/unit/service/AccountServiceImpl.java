package com.epam.jmp.unit.service;

import com.epam.jmp.unit.dao.AccountDao;
import com.epam.jmp.unit.dao.AccountExcelFileDaoImpl;
import com.epam.jmp.unit.model.Account;

import java.util.List;

/**
 * Created by Siarhei_Komlik on 11/4/14.
 */
public class AccountServiceImpl implements AccountService {

    public static final String FILE_PATH = "accounts.xls";
    private AccountDao accountDao = new AccountExcelFileDaoImpl(FILE_PATH);;

    @Override
    public List<Account> getAccounts() {
        return accountDao.getAccounts();
    }

    @Override
    public void readSource() {
        accountDao.readSource();
    }

    @Override
    public void writeSource(List<Account> accounts) {
        accountDao.writeSource(accounts);
    }
}
