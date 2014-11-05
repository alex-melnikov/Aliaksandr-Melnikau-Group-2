package com.epam.jmp.unit.service;

import com.epam.jmp.unit.model.Account;
import com.epam.jmp.unit.model.Amount;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Siarhei_Komlik on 11/5/14.
 */
@RunWith(JUnit4.class)
public class AccountServiceImplTest extends TestCase {

    private AccountService accountService;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.accountService = new AccountServiceImpl();
    }

    @Test
    public void testGetAccounts() throws Exception {
        accountService.readSource();
        List<Account> accountList = accountService.getAccounts();
        assertNotSame(0, accountList.size());
    }

    @Test
    public void testWriteSource() throws Exception {
        accountService.readSource();
        List<Account> accountList = accountService.getAccounts();
        assertNotSame(0, accountList.size());
        Account newAccount = new Account("TestAccount");
        Amount amount = new Amount(new BigDecimal(1000), "USD");
        newAccount.setAmount(amount);
        accountList.add(newAccount);
        accountService.writeSource(accountList);
        accountService.readSource();
        List<Account> latestAccountList = accountService.getAccounts();
        assertEquals("Size of list must be the same",
                accountList.size(), latestAccountList.size());
    }
}
