package org.pinkcodes.expensesharing;

import org.pinkcodes.expensesharing.controller.ExpenseSharingAppConsoleController;
import org.pinkcodes.expensesharing.model.*;
import org.pinkcodes.expensesharing.application.ExpenseSharingApp;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.in;


public class Main {

    public static void main(String[] args) {

        ExpenseSharingApp expenseSharingApp = new ExpenseSharingApp();
        ExpenseSharingAppConsoleController expenseSharingAppConsoleController =
                new ExpenseSharingAppConsoleController();

        Scanner scanner = new Scanner(in);

        while(scanner.hasNext()) {


            try {
                String userInput = scanner.nextLine();
                if(userInput.equalsIgnoreCase("Exit"))
                    break;
                ExpenseSharingInput expenseSharingInput = expenseSharingAppConsoleController.processUserInput(userInput);
                List<UserBalance> userBalances;
                ExpenseSharingBalanceOutput expenseSharingBalanceOutput;
                String output = "";
                switch (expenseSharingInput.getCommand()) {
                    case EXPENSE:
                         userBalances = expenseSharingApp.addExpense(expenseSharingInput.getExpense());
                        expenseSharingBalanceOutput = new ExpenseSharingBalanceOutput(userBalances);
                        output = expenseSharingAppConsoleController.createOutput(expenseSharingBalanceOutput);
                        break;
                    case SHOW_BALANCE, SHOW_BALANCE_FOR_A_USER:
                        userBalances = expenseSharingApp.showBalances();
                        expenseSharingBalanceOutput = new ExpenseSharingBalanceOutput(userBalances);
                        output = expenseSharingAppConsoleController.createOutput(expenseSharingBalanceOutput);
                        break;
                }
                System.out.println(output);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

    }

}