package org.pinkcodes.expensesharing.model;

import java.util.List;

public class ExpenseSharingBalanceOutput {


    private final String message;

    public ExpenseSharingBalanceOutput(String message) {
        this.message = message;
    }

    public String format() {
        return message;
    }

    public static void printOutput(List<UserBalance> ub) {

        for(UserBalance u:ub){
            if(u.getAmount()<0)
                System.out.println(u.getUserId()+"is owed " +u.getAmount());
            else
                System.out.println(u.getUserId()+"owes " +u.getAmount());
        }
    }


}
