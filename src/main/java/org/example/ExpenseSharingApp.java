package org.example;



import org.example.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseSharingApp {


    List<User> users = new ArrayList<User>();
    Map<String, Double> userBalance = new HashMap<>();

   /* String addUser(String name, String email, String mobile) {
        String userId = UUID.randomUUID().toString(); // Generate unique ID
        users.add(new User(name, email, mobile));
        userBalance.put(userId, 0.0);
        return userId;
    }*/

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

    public void showBalances() {

        for (Map.Entry<String, Double> entry : userBalance.entrySet()) {
            String userId = entry.getKey();
            Double balance = entry.getValue();

            if (balance < 0) {
                System.out.println(userId + "is owed" + balance);
            } else if (balance > 0)
                System.out.println(userId + "owes" + balance);

            else
                System.out.println("No balances");


        }

    }
}