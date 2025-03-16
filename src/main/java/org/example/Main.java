package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        ExpenseSharingApp app = new ExpenseSharingApp();
        //store users
		/*
		String A = app.addUser("Alice", "alice@example.com", "1234567890");
		String B = app.addUser("Bob", "bob@example.com", "0987654321");
		String C = app.addUser("Charlie", "charlie@example.com", "1122334455");
                      */
        Scanner scanner = new Scanner(System.in);

        String command = scanner.nextLine();
        String[] parts = command.split(" ");
        // <userId-who-paid> <amount> <no-of-users> <space-separated-userIds> <EQUAL/EXACT/PERCENT><space-separated-values-for-non-equal>
        //SHOW
        //SHOW u1
        //EXPENSE u1 1000 4 u1 u2 u3 u4 EQUAL
        List<String> userIds = new ArrayList<>();
        List<Double> values = new ArrayList<>();
        if (parts[0].equals("EXPENSE")) {
            String payerId = (parts[1]);
            double amount = Double.parseDouble(parts[2]);
            int numUsers = Integer.parseInt(parts[3]);

            for (int i = 0; i < numUsers; i++) {
                userIds.add((parts[4 + i]));
            }
            String type = parts[4 + numUsers];

            for (int i = 5 + numUsers; i < parts.length; i++) {
                values.add(Double.parseDouble(parts[i]));
            }
            app.addExpense(payerId,amount,numUsers, userIds , "EQUAL",values);
            app.showBalances();
            //app.addExpense(payerId, amount,numUsers, userIds, type, values);
        }

        if (parts[0].equals("SHOW")) {
            if (parts.length == 1) {
                app.showBalances();
            }

        }




    }


}