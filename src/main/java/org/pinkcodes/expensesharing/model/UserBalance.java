package org.pinkcodes.expensesharing.model;

public class UserBalance {

    private final String userId;
    private final Double amount;


    public UserBalance(String userId, Double amount){
       this.userId=userId;
       this.amount=amount;
    }
    public Double getAmount() {
        return amount;
    }

    public String getUserId() {
        return userId;
    }
}
