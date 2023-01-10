package com.elasrim.bankaccountmanager;

import com.elasrim.bankaccountmanager.exceptions.DepositException;
import com.elasrim.bankaccountmanager.exceptions.WithdrawalException;
import com.elasrim.bankaccountmanager.operations.Deposit;
import com.elasrim.bankaccountmanager.operations.Withdrawal;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountOperationTest {

    @Test
    void Should_Decrease_Balance_When_Withdrawal_Is_Made() throws WithdrawalException, DepositException {
        var account = new Account();
        account.setBalance(100);

        var operation = new Withdrawal(100);

        account.execute(operation);

        assertEquals(account.getBalance(),0);

    }

    @Test
    void Should_Increase_Balance_When_Deposit_Is_Made() throws WithdrawalException, DepositException {

        var account = new Account();
        account.setBalance(100);

        var operation = new Deposit(100);

        account.execute(operation);

        assertEquals(account.getBalance(),200);

    }

    @Test
    void Reject_Withdrawal_Greater_Than_Ceiling_Made() {

        var account = new Account();
        account.setBalance(100);
        account.setCeiling(50);

        var operation = new Withdrawal(100);

        assertThrows(WithdrawalException.class,() -> {
            account.execute(operation);
        });

    }


}
