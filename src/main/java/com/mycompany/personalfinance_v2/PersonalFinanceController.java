package com.mycompany.personalfinance_v2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class PersonalFinanceController {
    private ArrayList<Expense> expenses = new ArrayList<>();
    private ArrayList<Budget> budgets = new ArrayList<>();
    private ArrayList<BudgetItem> budgetItems = new ArrayList<>();
    private double salary;
    private File dataFile;
    private File budgetFile;

    public PersonalFinanceController() {
        salary = 0;

        // create data file for expenses
        dataFile = new File("expenses.csv");
        try {
            if (!dataFile.exists()) {
                dataFile.createNewFile();
            } else {
                dataFile.delete();
                dataFile.createNewFile();
            }
        } catch (IOException e) {
        }

        // create data file for budgets
        budgetFile = new File("budgets.csv");
        try {
            if (!budgetFile.exists()) {
                budgetFile.createNewFile();
            } else {
                budgetFile.delete();
                budgetFile.createNewFile();
            }
        } catch (IOException e) {
        }
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public ArrayList<Expense> getExpenses() {
        return expenses;
    }

    public void addExpense(String name, Date date, double amount) {
        int nextId = expenses.size() + 1;
        Expense expense = new Expense(nextId, name, date, amount);
        expenses.add(expense);
        writeExpenseToCSV(expense, dataFile);
    }

    public double getTotalExpenses() {
        double total = 0;
        for (Expense expense : expenses) {
            total += expense.getAmount();
        }
        return total;
    }

    public double getBalance() {
        return salary - getTotalExpenses();
    }

    public void addBudget(String name, double amount) {
        int nextId = budgets.size() + 1;
        Budget budget = new Budget(nextId, name, amount);
        budgets.add(budget);
        writeBudgetToCSV(budget, budgetFile);
    }

    public ArrayList<Budget> getBudgets() {
        return budgets;
    }
    
    public double getTotalBudget() {
        double total = 0;
        for (Budget budget : budgets) {
            total += budget.getAmount();
        }
        return total;
    }
    
    public void addBudgetItem(String name) {
        int nextId = budgetItems.size() + 1;
        BudgetItem budgetItem = new BudgetItem(nextId, name);
        budgetItems.add(budgetItem);
    }
    
    public ArrayList<BudgetItem> getBudgetItems() {
        return budgetItems;
    }
    

    private void writeExpenseToCSV(Expense expense, File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            if (file.length() == 0) {
                writer.write("ID,Expense,Date,Amount");
                writer.newLine();
            }
            writer.write(expense.getId() + "," + expense.getName() + "," + expense.getDate() + "," + expense.getAmount() + "\n");
        } catch (IOException e) {
        }
    }

    private void writeBudgetToCSV(Budget budget, File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            if (file.length() == 0) {
                writer.write("ID,Budget,Amount");
                writer.newLine();
            }
            writer.write(budget.getId() + "," + budget.getName() + "," + budget.getAmount() + "\n");
        } catch (IOException e) {
        }
    }
}
