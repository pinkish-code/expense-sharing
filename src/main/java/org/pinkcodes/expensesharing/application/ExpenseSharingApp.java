package org.pinkcodes.expensesharing.application;
import org.pinkcodes.expensesharing.model.Expense;
import org.pinkcodes.expensesharing.model.SplitType;
import org.pinkcodes.expensesharing.model.UserBalance;

import java.util.*;

public class ExpenseSharingApp {


    Map<String, Double> userBalanceMap = new HashMap<>();

    public  List<UserBalance> addExpense(Expense expense) {
        SplitType splitType = SplitType.valueOf(expense.getSplitType().toUpperCase());

        switch (splitType) {
            case SplitType.EQUAL:
                this.addExpenseForEqualSplitType(expense);
                break;
            case SplitType.EXACT:
                this.addExpenseForExactSplitType(expense);
                break;
            case SplitType.PERCENT:
                this.addExpenseForPercentSplitType(expense);
                break;
        }

        return this.showBalances();

    }

    private void addExpenseForEqualSplitType(Expense expense) {
        double share = expense.getAmountPaid() / expense.getUserIds().size();
        double paidByUserShare = 0.0;

        // u1 1000  u2, u3
        for (String userId : expense.getUserIds()) {

            // Ignore the paidBy UserId
            if (userId.equals(expense.getPaidBy())) {
                paidByUserShare = share;
            } else {
                double existingUserBalance = userBalanceMap.getOrDefault(userId, 0.0);
                userBalanceMap.put(userId, existingUserBalance + share);
            }
        }
        // Add expense for paidBy UserId
        double paidByUserExistingUserBalance = userBalanceMap.getOrDefault(expense.getPaidBy(), 0.0);
        userBalanceMap.put(expense.getPaidBy(), paidByUserExistingUserBalance + paidByUserShare - expense.getAmountPaid());
    }

    public void addExpenseForExactSplitType(Expense expense) {
        double paidByUserShare = 0.0;
        //u1  900  u2 u3  700 200

        for (int index = 0; index < expense.getUserIds().size(); index++) {

            String userId = expense.getUserIds().get(index);

            //ignore the paidby userid
            if (userId.equals(expense.getPaidBy())) {
                paidByUserShare = expense.getValues().get(index);
            } else {
                double existingUserBalance = userBalanceMap.getOrDefault(userId, 0.0);
                userBalanceMap.put(userId, existingUserBalance + expense.getValues().get(index));

            }
            double paidByUserExistingBalance = userBalanceMap.getOrDefault(expense.getPaidBy(), 0.0);
            userBalanceMap.put(expense.getPaidBy(), paidByUserExistingBalance + paidByUserShare);

        }
    }

        public void addExpenseForPercentSplitType(Expense expense){

        //u1 1000 u1 u2 u3  40 40 20

            double paidByUserShare=0.0;
            for (int index = 0; index < expense.getUserIds().size(); index++) {
                String userId = expense.getUserIds().get(index);

                //ignore paidBy userid
                if (expense.getUserIds().get(index).equals(expense.getPaidBy())) {
                    paidByUserShare=expense.getValues().get(index)* expense.getAmountPaid()/100;

                } else {
                      double existingUserBalance=userBalanceMap.getOrDefault(userId,0.0);
                      userBalanceMap.put(userId,existingUserBalance+expense.getValues().get(index)* expense.getAmountPaid()/100 );
                }

            }
            double paidByUserExistingBalance=userBalanceMap.getOrDefault(expense.getPaidBy(), 0.0);
            userBalanceMap.put(expense.getPaidBy(),paidByUserExistingBalance+paidByUserShare);

        }

    public List<UserBalance> showBalances() {

        List<UserBalance> ublist = new ArrayList<>();

        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, Double> entry : userBalanceMap.entrySet()) {
            String userId = entry.getKey();
            Double balance = entry.getValue();

            ublist.add(new UserBalance(userId, balance));
        }

        return ublist;

    }

}