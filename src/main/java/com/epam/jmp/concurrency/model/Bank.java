package com.epam.jmp.concurrency.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Siarhei_Komlik on 10/27/14.
 */
public class Bank {

    private String name;
    private List<Person> clients;

    public Bank() {
        this.clients = new ArrayList<Person>();
    }

    public Bank(String name) {
        this.name = name;
        this.clients = new ArrayList<Person>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Person> getClients() {
        return clients;
    }

    public void setClients(List<Person> clients) {
        this.clients = clients;
    }
}
