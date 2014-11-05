package com.epam.jmp.unit.model;

import org.apache.log4j.Logger;

import java.math.BigDecimal;

/**
 * Created by Siarhei_Komlik on 10/27/14.
 */
public class Account {
    public static final Logger LOGGER = Logger.getLogger(Account.class);

    private String name;
    private Amount amount;

    public Account(String name) {
        this.name = name;
    }

    public Account() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public BigDecimal getBalance() {
        if (null != amount) {
            return amount.getMoney();
        }
        throw new RuntimeException("Amount of this account has not money");
    }

    public void debit(BigDecimal money) {
        if (null != amount) {
            final BigDecimal currentBalance = amount.getMoney();
            LOGGER.info(Thread.currentThread().getName() + " DEBIT: Account#" + name + ". Balance before debit: " + currentBalance);
            amount.setMoney(currentBalance.subtract(money));
            LOGGER.info(Thread.currentThread().getName() + " DEBIT: Account#" + name + ". Balance after debit:" + amount.getMoney());
        } else {
            throw new RuntimeException("Amount of this account has not money");
        }
    }

    public void credit(BigDecimal money) {
        if (null != amount) {
            final BigDecimal currentBalance = amount.getMoney();
            LOGGER.info(Thread.currentThread().getName() + " CREDIT: Account#" + name + ". Balance before credit: " + currentBalance);
            amount.setMoney(currentBalance.add(money));
            LOGGER.info(Thread.currentThread().getName() + " CREDIT: Account#" + name + ". Balance after credit:" + amount.getMoney());
        } else {
            throw new RuntimeException("Amount of this account has not money");
        }
    }
}
