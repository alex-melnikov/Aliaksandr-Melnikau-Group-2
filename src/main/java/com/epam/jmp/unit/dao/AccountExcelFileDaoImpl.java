package com.epam.jmp.unit.dao;

import com.epam.jmp.unit.model.Account;
import com.epam.jmp.unit.model.Amount;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Siarhei_Komlik on 11/4/14.
 */
public class AccountExcelFileDaoImpl implements AccountDao {

    public static final Logger LOGGER = Logger.getLogger(AccountExcelFileDaoImpl.class);

    private List<Account> accounts;
    private String filePath;

    public AccountExcelFileDaoImpl(String filePath) {
        this.accounts = new ArrayList<Account>();
        this.filePath = filePath;
        readSource();
    }

    @Override
    public List<Account> getAccounts() {
        return accounts;
    }

    @Override
    public List<Account> readSource() {
        List accountList = new ArrayList();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filePath);

            // Using XSSF for xlsx format, for xls use HSSF
            Workbook workbook = new XSSFWorkbook(fis);

            int numberOfSheets = workbook.getNumberOfSheets();

            //looping over each workbook sheet
            for (int i = 0; i < numberOfSheets; i++) {
                Sheet sheet = workbook.getSheetAt(i);
                Iterator rowIterator = sheet.iterator();

                //iterating over each row
                while (rowIterator.hasNext()) {

                    Account account = new Account();
                    Row row = (Row) rowIterator.next();
                    Iterator cellIterator = row.cellIterator();

                    //Iterating over each cell (column wise)  in a particular row.
                    while (cellIterator.hasNext()) {

                        Cell cell = (Cell) cellIterator.next();
                        //The Cell Containing String will is name.
                        if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
                            account.setName(cell.getStringCellValue());

                            //The Cell Containing numeric value will contain marks
                        } else if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {

                            //Cell with index 1 contains amount
                            if (cell.getColumnIndex() == 1) {
                                Amount amount = new Amount(BigDecimal.valueOf(cell.getNumericCellValue()), "USD");
                                account.setAmount(amount);
                            }
                        }
                    }
                    //end iterating a row, add all the elements of a row in list
                    accountList.add(account);
                }
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.accounts = accountList;
        return accountList;
    }

    @Override
    public void writeSource(List<Account> accounts) {
        // Using XSSF for xlsx format, for xls use HSSF
        Workbook workbook = new XSSFWorkbook();

        Sheet accountsSheet = workbook.createSheet("Accounts");

        int rowIndex = 0;
        for(Account account : accounts){
            Row row = accountsSheet.createRow(rowIndex++);
            int cellIndex = 0;
            //first place in row is name
            row.createCell(cellIndex++).setCellValue(account.getName());

            //second place in row is amount
            row.createCell(cellIndex++).setCellValue(account.getBalance().doubleValue());
        }

        //write this workbook in excel file.
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            workbook.write(fos);
            fos.close();

            LOGGER.info(filePath + " is successfully written");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
