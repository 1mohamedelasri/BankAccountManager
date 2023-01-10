package com.elasrim.bankaccountmanager.operations;

import com.elasrim.bankaccountmanager.Account;
import com.elasrim.bankaccountmanager.exceptions.WithdrawalException;

public class Withdrawal extends Operation {

    public Withdrawal(double amount) {
        super(amount);
    }

    @Override
    public double apply(Account account) throws IllegalArgumentException, WithdrawalException {

        if (this.getAmount() <= 0) {
            throw new IllegalArgumentException("Cannot withdraw negative amounts!");
        }else if(this.getAmount() > account.getBalance()) {
            throw new WithdrawalException("Insufficient balance in account to\n" +
                    "withdraw specified amount!");
        } else if(this.getAmount()  > account.getCeiling()) {
            throw new WithdrawalException("withdrawal Amount must not be greater than" + account.getCeiling());
        }
        else {
            this.balance = account.getBalance() - this.getAmount();
            return this.balance;
        }
    }



}
