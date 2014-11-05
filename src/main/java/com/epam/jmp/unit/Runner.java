package com.epam.jmp.unit;

import com.epam.jmp.unit.model.Account;
import com.epam.jmp.unit.model.Bank;
import com.epam.jmp.unit.model.Person;
import com.epam.jmp.unit.service.AccountService;
import com.epam.jmp.unit.service.AccountServiceImpl;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * Created by Siarhei_Komlik on 10/27/14.
 */
public class Runner {

    public static final Logger LOGGER = Logger.getLogger(Runner.class);

    private static List<Bank> banks = new ArrayList<Bank>();
    private static Bank currentBank;
    private static Scanner scanner = new Scanner(System.in);
    private static AccountService accountService = new AccountServiceImpl();
    private static List<Account> accounts;

    public static void main(String[] args) {
        System.out.println("==== JMP. Module 6. Classic Model and Concurrency. ====");

        init();

        MainMenu menuItem = MainMenu.WAITING;
        MainMenu.showMenu();

        while (menuItem != MainMenu.EXIT) {
            while (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("Please enter a number!");
            }

            menuItem = MainMenu.findMenuByCode(scanner.nextInt());
            // move carets
            scanner.nextLine();
            MainMenu.showMenu();

            switch (menuItem) {
                case WAITING:
                    break;
                case SHOW_ALL_ACCOUNTS:
                    showAllAccounts();
                    break;
                case RUN_MONEY_TRANSFER:
                    accountService.readSource();
                    runMoney();
                    break;
                case EXIT:
                    break;
            }
            MainMenu.showMenu();
        }
        scanner.close();
    }

    private static void init() {
        Bank bank = new Bank("GoldBank");
        banks.add(bank);
        currentBank = bank;
        currentBank.getClients().add(new Person("Ivan Ivanov"));
        accountService.readSource();
        currentBank.getClients().get(0).setAccounts(accountService.getAccounts());
        accounts = accountService.getAccounts();
    }

    private static void runMoney() {
        //final List<Account> accountList = currentBank.getClients().get(0).getAccounts();
        for(int i = 0; i < accounts.size(); i++) {
            new TransferThread(accounts, accountService).start();
        }
        final Thread daemonThread = new TransferThread(accounts, accountService);
        daemonThread.setDaemon(true);
        daemonThread.start();
    }

    private static void showAllAccounts() {
        for (Account account : currentBank.getClients().get(0).getAccounts()) {
            LOGGER.info(account.getName() + ": " + account.getAmount().getMoney() + " " + account.getAmount().getCurrency());
        }
    }

    private enum MainMenu {
        WAITING(0, "Menu:"),
        SHOW_ALL_ACCOUNTS(1, "1. Show all accounts"),
        RUN_MONEY_TRANSFER(2, "2. Run transfer money"),
        EXIT(3, "3. Exit");

        private final Integer code;
        private final String desc;

        MainMenu(Integer i, String desc) {
            this.code = i;
            this.desc = desc;
        }

        static MainMenu findMenuByCode(Integer i) {
            for (MainMenu value : values()) {
                if (i == 0) {
                    break;
                }
                if (i == value.code) {
                    return value;
                }
            }
            System.out.println("Number is incorrect. Please retry");
            return WAITING;
        }

        public String getDesc() {
            return desc;
        }

        public static void showMenu() {
            for (MainMenu item : MainMenu.values()) {
                System.out.println(item.getDesc());
            }
        }
    }
}