package org.pinkcodes.expensesharing;



import org.pinkcodes.expensesharing.model.ExpenseSharingBalanceOutput;
import org.pinkcodes.expensesharing.model.ExpenseSharingInput;
import org.pinkcodes.expensesharing.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseSharingApp {


    List<User> users = new ArrayList<User>();
    Map<String, Double> userBalance = new HashMap<>();



    public ExpenseSharingBalanceOutput processCommand(ExpenseSharingInput input) {
        if (input.getExpenseSharingCommand().equals("EXPENSE")) {
            addExpense(input.getPaidBy(), input.getAmountPaid(),input.getNumberOfUsers(), input.getUserIds(), input.getSplitType(), input.getValues());
            return new ExpenseSharingBalanceOutput("Expense added successfully.");
        } else if (input.getExpenseSharingCommand().equals("SHOW")) {
            return new ExpenseSharingBalanceOutput(showBalances());
        }
        return new ExpenseSharingBalanceOutput("Invalid command.");
    }

    public void addExpense(String payerId, double amount, int numUsers, List<String> userIds, String type, List<Double> values) {


        double share = amount / numUsers;


        if (type.equals("EQUAL")) {

            for (String x : userIds) {
                if (!x.equals(payerId))
                    // userBalance.put(x, userBalance.get(x)+share);
                    userBalance.put(x, userBalance.getOrDefault(x ,0.0)+ share);

            }

            userBalance.put(payerId,userBalance.getOrDefault(payerId,0.0)+ share - amount);

        } else if (type.equals("EXACT")) {

            double totalShare = 0;

            for (double value : values) {
                totalShare += value;
            }
            for (int x = 0; x < userIds.size(); x++) {
                String userId = userIds.get(x);
                if (!userIds.get(x).equals(payerId))
                    userBalance.put(userId,userBalance.getOrDefault(payerId,0.0) + values.get(x));


            }

            userBalance.put(payerId, userBalance.getOrDefault(payerId,0.0) - (totalShare));

        }
        else if (type.equals("PERCENT")) {

           System.out.println("Percent mode");
            double totalShare = 0;


            for (int x = 0; x < userIds.size(); x++) {
                String userId = userIds.get(x);
                if (!userIds.get(x).equals(payerId))
                {
                    userBalance.put(userId, userBalance.getOrDefault(userId,0.0) + values.get(x)/100 *amount);
                    totalShare += (values.get(x)/ 100) * amount;
                }


            }


            userBalance.put(payerId, userBalance.getOrDefault(payerId,0.0)+ (totalShare-amount));
        }


    }

    public String showBalances() {
        if (userBalance.isEmpty()) {
            return "No balances";
        }

        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, Double> entry : userBalance.entrySet()) {
            String userId = entry.getKey();
            Double balance = entry.getValue();

            if (balance < 0) {
                result.append(userId).append(" is owed ").append(-balance).append("\n");
            } else if (balance > 0) {
                result.append(userId).append(" owes ").append(balance).append("\n");
            }
        }

        return result.isEmpty() ? "No balances" : result.toString().trim();
    }

}