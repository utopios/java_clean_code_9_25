package com.codurance.srp;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.stream.Collectors.toCollection;

public class AccountService {

    private static final String STATEMENT_HEADER = "DATE | AMOUNT | BALANCE";
    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final String AMOUNT_FORMAT = "#.00";

    private TransactionRepository transactionRepository;
    private Clock clock;
    private Console console;

    private final PrintStatementService printStatementService;

    public AccountService(TransactionRepository transactionRepository, Clock clock, Console console, PrintStatementService printStatementService) {
        this.transactionRepository = transactionRepository;
        this.clock = clock;
        this.console = console;
        this.printStatementService = printStatementService;
    }

    public void deposit(int amount) {
        transactionRepository.add(transactionWith(amount));
    }


    public void withdraw(int amount) {
        transactionRepository.add(transactionWith(-amount));
    }

    public void printStatement() {
        List<Transaction> transactions = transactionRepository.all();
        printStatementService.printStatement(transactions);
    }

    private Transaction transactionWith(int amount) {
        return new Transaction(clock.today(), amount);
    }


    
}