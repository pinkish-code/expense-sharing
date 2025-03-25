package org.pinkcodes.expensesharing.controller;

import org.pinkcodes.expensesharing.ExpenseSharingApp;
import org.pinkcodes.expensesharing.model.ExpenseSharingInput;
import org.pinkcodes.expensesharing.model.UserBalance;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseSharingAppConsoleController {

    ExpenseSharingApp app=new ExpenseSharingApp();

    public List<UserBalance> processCommand(ExpenseSharingInput input) {

        List<UserBalance> userlist;

        if (input.getExpenseSharingCommand().equals("EXPENSE")) {
           app.addExpense(input.getPaidBy(), input.getAmountPaid(),input.getNumberOfUsers(), input.getUserIds(), input.getSplitType(), input.getValues());
            userlist=app.showBalances();
            return userlist;
        } else if (input.getExpenseSharingCommand().equals("SHOW")) {
            userlist=app.showBalances();
            return userlist;
        }

        else{
            System.out.println("Invalid command.");
            return Collections.emptyList();
        }
    }
}
