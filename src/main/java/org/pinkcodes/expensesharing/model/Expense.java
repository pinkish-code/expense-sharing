package org.pinkcodes.expensesharing.model;
import java.util.List;

public class Expense {
    private final String paidBy;
    private final double amountPaid;
    private final List<String> userIds;
    private final String splitType;
    private final List<Double> values;

    public Expense(String paidBy, double amountPaid, int numberOfUsers, List<String> userIds, String splitType, List<Double> values) {
        this.paidBy = paidBy;
        this.amountPaid = amountPaid;
        this.userIds = userIds;
        this.splitType = splitType;
        this.values = values;
    }

    // Getters
    public String getPaidBy() { return paidBy; }
    public double getAmountPaid() { return amountPaid; }
    public List<String> getUserIds() { return userIds; }
    public String getSplitType() { return splitType; }
    public List<Double> getValues() { return values; }
}

