package com.epam.jmp.unit.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Siarhei_Komlik on 10/27/14.
 */
public class Person {

    private String name;
    private List<Account> accounts = new ArrayList<Account>();


    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
