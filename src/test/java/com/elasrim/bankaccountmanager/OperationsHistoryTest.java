package com.elasrim.bankaccountmanager;

import com.elasrim.bankaccountmanager.exceptions.DepositException;
import com.elasrim.bankaccountmanager.exceptions.WithdrawalException;
import com.elasrim.bankaccountmanager.operations.Deposit;
import com.elasrim.bankaccountmanager.operations.Operation;
import com.elasrim.bankaccountmanager.operations.Withdrawal;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OperationsHistoryTest {

    @Test
    void Should_give_Account_Statements_of_date_interval() throws WithdrawalException, DepositException {
        var account = new Account();
        account.setBalance(100);



        account.execute(new Withdrawal(100));
        account.execute(new Deposit(200));
        account.execute(new Deposit(300));
        account.execute(new Withdrawal(200));

        LocalDate first = LocalDate.of(2023, 1, 1);
        LocalDate second = LocalDate.of(2023,1 , 22);

        var statements = account.getStatements(first,second);

        Predicate<Operation> statementInterval = item-> item.getDate().isAfter(first) && item.getDate().isBefore(second);

        assertTrue(statements.stream().allMatch(statementInterval));
    }
}
