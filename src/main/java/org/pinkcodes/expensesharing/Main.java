package org.pinkcodes.expensesharing;

import org.pinkcodes.expensesharing.model.ExpenseSharingBalanceOutput;
import org.pinkcodes.expensesharing.model.ExpenseSharingInput;
import org.pinkcodes.expensesharing.model.UserBalance;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.in;


public class Main {

    public static void main(String[] args) {

        ExpenseSharingApp app = new ExpenseSharingApp();

        Scanner scanner = new Scanner(in);

        try {
            ExpenseSharingInput input = ExpenseSharingInput.parse(scanner.nextLine());

            //ExpenseSharingBalanceOutput output = app.processCommand(input); // Process command
            // System.out.println(output.format()); // Print formatted output

            List<UserBalance> list = app.processCommand(input);
            ExpenseSharingBalanceOutput.printOutput(list);
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }


    }

}