package com.elasrim.bankaccountmanager.operations;

import com.elasrim.bankaccountmanager.Account;
import com.elasrim.bankaccountmanager.exceptions.DepositException;
import com.elasrim.bankaccountmanager.exceptions.WithdrawalException;

import java.time.LocalDate;

public abstract class Operation {

    private Long historyId;
    private LocalDate date;
    private double amount;
    protected double balance;

    public Operation(double amount) {
        this.amount = amount;
        this.balance = 0;
        this.date = LocalDate.now();
        this.historyId = System.currentTimeMillis();
    }


    public LocalDate getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalance() {
        return balance;
    }



    public abstract double apply(Account account) throws WithdrawalException, DepositException;


    @Override
    public String toString() { // todo time shoud be added to date and add id of operation to print function
        return String.format("Operation : [%s], Date : [%s], amount : [%.2f], balance : [%.2f] \n",
                this.getClass().getSimpleName(), date, this.getAmount(), this.getBalance());
    }
}
