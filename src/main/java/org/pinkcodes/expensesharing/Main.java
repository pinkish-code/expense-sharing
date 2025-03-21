package org.pinkcodes.expensesharing;

import org.pinkcodes.expensesharing.model.ExpenseSharingBalanceOutput;
import org.pinkcodes.expensesharing.model.ExpenseSharingInput;
import java.util.Scanner;

import static java.lang.System.in;


public class Main {

    public static void main(String[] args) {

        ExpenseSharingApp app = new ExpenseSharingApp();

        Scanner scanner = new Scanner(in);

        ExpenseSharingInput input=ExpenseSharingInput.parse(scanner.nextLine());
        ExpenseSharingBalanceOutput output = app.processCommand(input); // Process command

        System.out.println(output.format()); // Print formatted output


    }

}