package org.pinkcodes.expensesharing.controller;
import org.pinkcodes.expensesharing.model.*;

import java.util.ArrayList;
import java.util.List;


/**
 * It acts as in interface between user and application
 */
public class ExpenseSharingAppConsoleController {


    public ExpenseSharingInput processUserInput(String command) {
        String[] parts = command.split(" ");
        List<String> userIds = new ArrayList<>();
        String splitType;
        List<Double> values = new ArrayList<>();


        if (parts[0].equals("EXPENSE")) {
            String paidBy = parts[1];
            double amountPaid = Double.parseDouble(parts[2]);
            int numOfUsers = Integer.parseInt(parts[3]);

            for (int i = 0; i < numOfUsers; i++) {
                userIds.add(parts[4 + i]);
            }
            splitType = parts[4 + numOfUsers];

            for (int i = 5 + numOfUsers; i < parts.length; i++) {
                values.add(Double.parseDouble(parts[i]));
            }
            Expense expense = new Expense(paidBy, amountPaid, numOfUsers, userIds, splitType, values);
            return new ExpenseSharingInput(ExpenseSharingCommand.EXPENSE, expense);
        } else if (parts[0].equals("SHOW")) {
            if (parts.length > 1) {
                return new ExpenseSharingInput(ExpenseSharingCommand.SHOW_BALANCE, parts[1]);
            } else {
                return new ExpenseSharingInput(ExpenseSharingCommand.SHOW_BALANCE_FOR_A_USER);
            }

        } else {
            throw new IllegalArgumentException("Invalid command format");
        }
    }

    public String createOutput(ExpenseSharingBalanceOutput expenseSharingBalanceOutput) {
        if (expenseSharingBalanceOutput.getUserBalances() == null) {
            return "";
        }
        StringBuilder output = new StringBuilder();
        for(UserBalance userBalance:expenseSharingBalanceOutput.getUserBalances()){
            if(userBalance.getAmount() < 0) {
                output.append(userBalance.getUserId()).append("is owed ").append(userBalance.getAmount()).append("\n");
            }
            else {
                output.append(userBalance.getUserId()).append("owes ").append(userBalance.getAmount()).append("\n");
            }
        }
        return output.toString();
    }


}
