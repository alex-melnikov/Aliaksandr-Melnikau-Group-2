package com.epam.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.epam.bean.Account;
import com.epam.bean.ExchangeRate;
import com.epam.dao.interfaces.IAccountDaoLocal;
import com.epam.dao.interfaces.IAccountDaoRemote;

/**
 * Account dao implementation bean
 */
@Stateless
public class AccountDaoImpl implements IAccountDaoLocal, IAccountDaoRemote
{
    @PersistenceContext
    private EntityManager em;

    @Override
    public void createAccount(Account account)
    {
        if (account != null)
        {
            em.persist(account);
        }
    }

    @Override
    public Account getAccountById(int id)
    {
        Query query = em.createQuery("from Account where id = :id");
        query.setParameter("id", id);
        return (Account) query.getResultList().get(0);
    }

    @Override
    public Account exchange(Account account, String currency)
    {
        if (account != null && currency != null)
        {
            List<ExchangeRate> rates = getExchangeRates();
            for (ExchangeRate rate : rates)
            {
                if (account.getCurrency().equals(rate.getFromCurrency()) && currency.equals(rate.getToCurrency()))
                {
                    double newAmount = account.getBalance() * rate.getRate();
                    account.setBalance(newAmount);
                    account.setCurrency(currency);
                    break;
                }

            }
        }
        em.merge(account);
        return account;
    }

    private List<ExchangeRate> getExchangeRates()
    {
        Query query = em.createQuery("from ExchangeRate");
        return query.getResultList();
    }

}
