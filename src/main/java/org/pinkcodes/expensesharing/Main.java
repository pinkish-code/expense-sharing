package org.pinkcodes.expensesharing;

import org.pinkcodes.expensesharing.controller.ExpenseSharingAppConsoleController;
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

        while(scanner.hasNext()) {


            try {
                String s1= scanner.nextLine();
                if(s1.equalsIgnoreCase("Exit"))
                    break;
                ExpenseSharingInput input = ExpenseSharingInput.parse(s1);

                ExpenseSharingAppConsoleController ec=new ExpenseSharingAppConsoleController();
                List<UserBalance> list = ec.processCommand(input);
                ExpenseSharingBalanceOutput.printOutput(list);

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

    }

}