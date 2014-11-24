package com.epam.dao.interfaces;

import javax.ejb.Remote;

import com.epam.bean.Account;

/**
 * Account dao remote interface
 */
@Remote
public interface IAccountDaoRemote
{
    /**
     * Create account
     * @param account
     */
    void createAccount(Account account);

    /**
     * get account by id
     * @param id
     * @return
     */
    Account getAccountById(int id);

    /**
     * exchange money
     * @param currency
     * @param account
     * @return new balance
     */
    Account exchange(Account account, String currency);

}
