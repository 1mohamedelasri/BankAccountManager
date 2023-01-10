package com.elasrim.bankaccountmanager;

import com.elasrim.bankaccountmanager.exceptions.DepositException;
import com.elasrim.bankaccountmanager.exceptions.WithdrawalException;
import com.elasrim.bankaccountmanager.operations.Deposit;
import com.elasrim.bankaccountmanager.operations.Operation;
import com.elasrim.bankaccountmanager.operations.Withdrawal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Account {

    private Long iban;
    private LocalDate creationDate;
    private double balance;

    private double ceiling;

    private List<Operation> operations;
    public Account() {
        this.operations = new ArrayList<>();
        this.iban  = System.currentTimeMillis();
        this.creationDate = LocalDate.now();
        this.balance = 0;
        this.ceiling = 1000;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getCeiling() {
        return ceiling;
    }

    public void setCeiling(double ceiling) {
        this.ceiling = ceiling;
    }

    public void execute(Operation operation) throws WithdrawalException, DepositException {
        var newBalance = operation.apply(this);
        this.setBalance(newBalance);
        this.operations.add(operation);
    }

    public void printHistory() {

        this.operations.stream().sorted(Comparator.comparing(Operation::getDate)).forEach(System.out::println);
    }

    public List<Operation> getStatements(LocalDate start, LocalDate end) {
        Predicate<Operation> historyFilter = item-> (item.getDate().isAfter(start) && item.getDate().isBefore(end));
        return this.operations.stream().filter(historyFilter).sorted(Comparator.comparing(Operation::getDate)).collect(Collectors.toList());

    }
    public void printAccountStatement(LocalDate start, LocalDate end) {
        var statements = this.getStatements(start,end);
        statements.stream().forEach(System.out::println);
    }


}
