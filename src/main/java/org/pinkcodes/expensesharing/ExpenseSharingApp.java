package org.pinkcodes.expensesharing;
import org.pinkcodes.expensesharing.model.ExpenseSharingBalanceOutput;
import org.pinkcodes.expensesharing.model.ExpenseSharingInput;
import org.pinkcodes.expensesharing.model.User;
import org.pinkcodes.expensesharing.model.UserBalance;

import java.util.*;

public class ExpenseSharingApp {


    Map<String, Double> userBalance = new HashMap<>();

    public  void addExpense(String payerId, double amount, int numUsers, List<String> userIds, String type, List<Double> values) {


        double share = amount / numUsers;


        if (type.equals("EQUAL")) {

            for (String x : userIds) {
                if (!x.equals(payerId))
                    // userBalance.put(x, userBalance.get(x)+share);
                   userBalance.put(x, userBalance.getOrDefault(x ,0.0)+ share);

                   // userBalance.add(new UserBalance(x, u.getAmount()));
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


            userBalance.put(payerId, userBalance.getOrDefault(payerId,0.0)+ (-totalShare));
        }


    }

    public List<UserBalance> showBalances() {

        List<UserBalance> ublist = new ArrayList<>();

        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, Double> entry : userBalance.entrySet()) {
            String userId = entry.getKey();
            Double balance = entry.getValue();

            ublist.add(new UserBalance(userId, balance));
        }

        return ublist;

    }

}