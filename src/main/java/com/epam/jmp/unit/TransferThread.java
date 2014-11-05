package com.epam.jmp.unit;

import com.epam.jmp.unit.model.Account;
import com.epam.jmp.unit.service.AccountService;
import com.epam.jmp.unit.service.AccountServiceImpl;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

/**
 * Created by Siarhei_Komlik on 10/31/14.
 */
public class TransferThread extends Thread {

    public static final Logger LOGGER = Logger.getLogger(TransferThread.class);

    private final Random rnd;
    private final List<Account> accounts;
    private int count_num_attempt = 0;
    private AccountService accountService;

    public TransferThread(List<Account> accounts, AccountService accountService) {
        this.rnd = new Random();
        this.accounts = accounts;
        this.accountService = accountService;
    }

    @Override
    public void run() {
        if (Thread.currentThread().isDaemon()) {
            int count = 0;
            while(true) {
                try {
                    LOGGER.info("DAEMON THREAD: Balance of accounts is " + getAccountsBalance());
                    Thread.currentThread().sleep(1000);
                    if(count > 5) {
                        synchronized (accounts) {
                            accountService.writeSource(accounts);
                            LOGGER.info("DAEMON THREAD: Data were wrote to file");
                        }
                        count = 0;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
                count++;
            }
        } else {
            LOGGER.info(getThreadName() + " is start working");
            for (int i=0; i < accounts.size(); i++) {
                int numberAccounds = accounts.size();
                int fromAcct = rnd.nextInt(numberAccounds);
                int toAcct = rnd.nextInt(numberAccounds);
                BigDecimal amount = new BigDecimal(rnd.nextInt(accounts.get(fromAcct).getBalance().intValue()));
                try {
                    transferMoney(accounts.get(fromAcct),
                            accounts.get(toAcct), amount);
                } catch (Exception e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
            LOGGER.info(getThreadName() + " have finished work");
        }
    }

    public void transferMoney(final Account fromAcct, final Account toAcct, final BigDecimal money) throws Exception {
        class Helper {
            public void transfer() throws Exception {
                if (fromAcct.getBalance().compareTo(money) < 0){
                    LOGGER.info(getThreadName() + " . Balance from account is " + fromAcct.getBalance());
                    LOGGER.info("Transfer money is " + money);
                    if (count_num_attempt < 5) {
                        LOGGER.info(getThreadName() + " sleep 3 sec");
                        LOGGER.info("Num of attempt is " + count_num_attempt);
                        Thread.currentThread().sleep(3000);
                        count_num_attempt++;
                    } else {
                        LOGGER.info("Number of attempt is finish");
                    }
                    //transfer();
                }
                else {
                    LOGGER.info(getThreadName() + ". Transfer " + money + " " + fromAcct.getAmount().getCurrency()
                            + " from Account#" + fromAcct.getName() + " to Account#" + toAcct.getName());
                    fromAcct.debit(money);
                    toAcct.credit(money);
                }
            }
        }
        int fromHash = System.identityHashCode(fromAcct);
        int toHash = System.identityHashCode(toAcct);
        if (fromHash < toHash) {
            synchronized (fromAcct) {
                synchronized (toAcct) {
                    new Helper().transfer();
                }
            }
        } else if (fromHash > toHash) {
            synchronized (toAcct) {
                synchronized (fromAcct) {
                    new Helper().transfer();
                }
            }
        }
    }

    private static String getThreadName() {
        return Thread.currentThread().getName();
    }

    private BigDecimal getAccountsBalance() {
        BigDecimal sum = new BigDecimal(0);
        synchronized (accounts) {
            for (Account account : accounts) {
                sum = sum.add(account.getBalance());
            }
            return sum;
        }
    }
}
