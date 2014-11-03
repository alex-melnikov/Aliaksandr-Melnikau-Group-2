package com.epam.jmp.concurrency;

import com.epam.jmp.concurrency.model.Account;
import com.epam.jmp.concurrency.model.Amount;
import com.epam.jmp.concurrency.model.Bank;
import com.epam.jmp.concurrency.model.Person;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * Created by Siarhei_Komlik on 10/27/14.
 */
public class Runner {

    public static final Logger LOGGER = Logger.getLogger(Runner.class);

    private static List<Bank> banks = new ArrayList<Bank>();
    private static Bank currentBank;
    private static Scanner scanner = new Scanner(System.in);

    private static final String defaultCurrency = "USD";


    public static void main(String[] args) {
        System.out.println("==== JMP. Module 5. Classic Model and Concurrency. ====");

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

            switch (menuItem) {
                case WAITING:
                    break;
                case CREATE_BANK:
                    createBank();
                    MainMenu.showMenu();
                    break;
                case CREATE_PERSON:
                    createPerson();
                    MainMenu.showMenu();
                    break;
                case CREATE_ACCOUNT:
                    createAccounts();
                    MainMenu.showMenu();
                    break;
                case CHOOSE_BANK:
                    chooseBank();
                    showCurrentBank();
                    MainMenu.showMenu();
                    break;
                case SHOW_CURRENT_BANK:
                    showCurrentBank();
                    break;
                case SHOW_ALL_ACCOUNTS:
                    showAllAccounts();
                    MainMenu.showMenu();
                    break;
                case RUN_MONEY_TRANSFER:
                    runMoney();
                    MainMenu.showMenu();
                    break;
                case EXIT:
                    break;
            }
        }
        scanner.close();
    }

    private static void runMoney() {
        if (null != currentBank) {
            LOGGER.info("Please choose person:");
            Map<Integer, Person> persons = getPersonMap();
            Person person = persons.get(scanner.nextInt());
            // move carets
            scanner.nextLine();
            if (null != person) {
                // TODO main logik
                for (int i = 0; i < person.getAccounts().size(); i++){
                    new TransferThread(person.getAccounts()).start();
                }
                Thread daemonThread = new TransferThread(person.getAccounts());
                daemonThread.setDaemon(true);
                daemonThread.start();
            } else {
                LOGGER.info("Person was not choosed.Please retry");
            }
        } else {
            LOGGER.info("Please firstly choose bank");
        }
    }

    private static void createBank() {
        LOGGER.info("Please enter bank name:");
        Bank bank = new Bank(scanner.nextLine());
        if (validateBankName(bank.getName())) {
            banks.add(bank);
            LOGGER.info("'" + bank.getName() + "' bank was created");
        }
    }

    private static void createPerson() {
        if (null != currentBank) {
            LOGGER.info("Please enter person name:");
            Person person = new Person(scanner.nextLine());
            currentBank.getClients().add(person);
            LOGGER.info("Person with name " + person.getName() + " was added to bank " + currentBank.getName());
        } else {
            LOGGER.info("Please firstly choose bank");
        }
    }

    private static void createAccounts() {
        if (null != currentBank) {
            Map<Integer, Person> persons = getPersonMap();
            if (persons.isEmpty()) {
                LOGGER.info("Please create a person");
                return;
            }
            LOGGER.info("Please choose person:");
            int i = scanner.nextInt();
            // move carets
            scanner.nextLine();
            Person person = persons.get(i);
            if (null != person) {
                LOGGER.info("Please enter number of accounts:");
                int num = scanner.nextInt();
                // move carets
                scanner.nextLine();
                LOGGER.info("Please enter fixed amount of money:");
                final BigDecimal fullSum = scanner.nextBigDecimal();
                BigDecimal sum, remainder;
                // move carets
                scanner.nextLine();
                sum = fullSum.divide(new BigDecimal(num), 2, RoundingMode.HALF_UP);
                remainder = fullSum.subtract(sum.multiply(new BigDecimal(num)));
                int j = 1;
                while(j <= num) {
                    Account account = new Account(String.valueOf(person.getAccounts().size() + 1));
                    if(j != num) {
                        account.setAmount(new Amount(sum, defaultCurrency));
                    } else {
                        account.setAmount(new Amount(sum.add(remainder), defaultCurrency));
                    }
                    person.getAccounts().add(account);
                    LOGGER.info("Account " + account.getName() + ": " + account.getAmount().getMoney()
                            + " " + account.getAmount().getCurrency());
                    j++;
                }
            } else {
                LOGGER.info("Enter valid number!");
            }

        } else {
            LOGGER.info("Please firstly choose bank");
        }
    }

    private static void chooseBank() {
        LOGGER.info("Please choose bank:");
        Map<Integer, Bank> b = getBankMap();
        if (b.isEmpty()) {
            LOGGER.info("Current bank is empty!");
            return;
        }
        currentBank = b.get(scanner.nextInt());
        // move carets
        scanner.nextLine();
    }

    private static void showAllAccounts() {
        if (null != currentBank) {
            LOGGER.info("Please choose person:");
            Map<Integer, Person> persons = getPersonMap();
            Person person = persons.get(scanner.nextInt());
            // move carets
            scanner.nextLine();
            if (null == person) {
                LOGGER.info("Please choose person from list. Try again");
            }
            for (Account account : person.getAccounts()) {
                LOGGER.info(account.getName() + ": " + account.getAmount().getMoney() + " " + account.getAmount().getCurrency());
            }
        } else {
            LOGGER.info("Please firstly choose bank");
        }
    }

    private enum MainMenu {
        WAITING(0, "Menu:"),
        CREATE_BANK(1, "1. Create bank"),
        CREATE_PERSON(2, "2. Create person"),
        CREATE_ACCOUNT(3, "3. Create account"),
        CHOOSE_BANK(4, "4. Choose bank"),
        SHOW_CURRENT_BANK(5, "5. Show current bank"),
        SHOW_ALL_ACCOUNTS(6, "6. Show all accounts"),
        RUN_MONEY_TRANSFER(7, "7. Run transfer money"),
        EXIT(8, "8. Exit");

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

    public static Map<Integer, Bank> getBankMap() {
        int i = 1;
        Map<Integer, Bank> results = new HashMap<Integer, Bank>();
        for (Bank bank : banks) {
            LOGGER.info(i + ". " + bank.getName());
            results.put(new Integer(i), bank);
            i++;
        }
        return results;
    }

    public static void showCurrentBank() {
        if (null != currentBank) {
            LOGGER.info("Current bank is '" + currentBank.getName() + "'");
        } else {
            LOGGER.info("Current bank is empty!");
        }
    }

    private static Map<Integer,Person> getPersonMap() {
        int i = 1;
        Map<Integer, Person> results = new HashMap<Integer, Person>();
        for (Person person : currentBank.getClients()) {
            LOGGER.info(i + ". " + person.getName());
            results.put(new Integer(i), person);
            i++;
        }
        return results;
    }

    private static boolean validateBankName(String name) {
        for (Bank bank : banks) {
            if (name.equals(bank.getName())) {
                LOGGER.info("Bank " + bank.getName() + " is already exist");
                return false;
            }
        }
        return true;
    }
}
