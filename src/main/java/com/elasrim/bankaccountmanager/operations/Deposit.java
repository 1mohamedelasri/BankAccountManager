package com.elasrim.bankaccountmanager.operations;

import com.elasrim.bankaccountmanager.Account;
import com.elasrim.bankaccountmanager.exceptions.DepositException;
import com.elasrim.bankaccountmanager.exceptions.WithdrawalException;

public class Deposit extends Operation {

    public Deposit(double amount) {
        super(amount);
    }
    public double apply(Account account) throws IllegalArgumentException, DepositException {
        if (this.getAmount() <= 0) {
                throw new IllegalArgumentException("Cannot deposit negative amounts!");
        }  else if(this.getAmount()  > 250000) {
            throw new DepositException("You cannot deposit Amount greater than 250,000");
        }
        else {
            this.balance = account.getBalance() + this.getAmount();
            return this.balance;
        }
    }
}
