package com.epam.jmp.concurrency.model;

import java.math.BigDecimal;

/**
 * Created by Siarhei_Komlik on 10/27/14.
 */
public class Amount {

    private BigDecimal money;
    private String currencyCode;

    public Amount(BigDecimal amount, String currencyCode) {
        this.money = amount;
        this.currencyCode = currencyCode;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getCurrency() {
        return currencyCode;
    }

    public void setCurrency(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}
