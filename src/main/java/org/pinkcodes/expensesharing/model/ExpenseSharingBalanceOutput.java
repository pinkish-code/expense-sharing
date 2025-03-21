package org.pinkcodes.expensesharing.model;

import java.util.List;

public class ExpenseSharingBalanceOutput {


    private String message;

    public ExpenseSharingBalanceOutput(String message) {
        this.message = message;
    }

    public String format() {
        return message;
    }
}
