package com.elasrim.bankaccountmanager;

import com.elasrim.bankaccountmanager.exceptions.DepositException;
import com.elasrim.bankaccountmanager.exceptions.WithdrawalException;
import com.elasrim.bankaccountmanager.operations.Deposit;
import com.elasrim.bankaccountmanager.operations.Operation;
import com.elasrim.bankaccountmanager.operations.Withdrawal;

import java.util.Scanner;

public class Bank {

    public static void main(String[] args) throws WithdrawalException {
        var account = new Account();

        Scanner input = new Scanner(System.in);
        int choice;
        double amount;

        do{

            System.out.println("Choose Action: ");
            System.out.println("(1) Withdraw");
            System.out.println("(2) Deposit");
            System.out.println("(3) Check History of operations");
            System.out.println("(4) exit");

            choice = input.nextInt();

            switch(choice){



                //---------WITHDRAW------------//
                case 1 :
                    try {
                        System.out.println("******Withdraw******");
                        System.out.println("Write your amount");
                        amount = input.nextLong();
                        account.execute(new Withdrawal(amount));
                        System.out.println("***************************");

                    } catch (DepositException | WithdrawalException e) {
                        System.out.println(e);
                    }
                    break;

                //---------DEPOSIT------------//
                case 2 :

                    try {
                        System.out.println("******Deposit******");
                        System.out.println("Write your amount");
                        amount = input.nextLong();
                        account.execute(new Deposit(amount));
                        System.out.println("***************************");
                    } catch (DepositException | WithdrawalException e) {
                        System.out.println(e);
                    }



                    break;

                //----------- Check History-------//
                case 3 :

                        System.out.println("****** Check History of operations ******");
                        account.printHistory();
                        System.out.println("***************************");

                    break;

                case 4 :

                    System.out.println("Thank you for choosing our US!");
                    break;

                default :

                    System.out.println("Invalid action.");
                    break;

            }

        }while(choice != 4);

    }
}
