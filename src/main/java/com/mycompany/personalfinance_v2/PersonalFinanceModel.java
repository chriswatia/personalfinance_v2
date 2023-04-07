package com.mycompany.personalfinance_v2;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class PersonalFinanceModel extends Observable {
    private double salary;
    private final List<Expense> expenses;
    private final List<Budget> budgets;

    public PersonalFinanceModel(double salary) {
        this.salary = salary;
        expenses = new ArrayList<>();
        budgets = new ArrayList<>();
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
        setChanged();
        notifyObservers();
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
        setChanged();
        notifyObservers();
    }

    public double getTotalExpenses() {
        double total = 0.0;
        for (Expense expense : expenses) {
            total += expense.getAmount();
        }
        return total;
    }

    public double getBalance() {
        return salary - getTotalExpenses();
    }

    public List<Budget> getBudgets() {
        return budgets;
    }

    public void addBudget(Budget budget) {
        budgets.add(budget);
        setChanged();
        notifyObservers();
    }

    public double getTotalBudgets() {
        double total = 0.0;
        for (Budget budget : budgets) {
            total += budget.getAmount();
        }
        return total;
    }

    public double getRemainingBudget() {
        return getTotalBudgets() - getTotalExpenses();
    }
}
