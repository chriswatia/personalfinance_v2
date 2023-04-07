package com.mycompany.personalfinance_v2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.EmptyBorder;

public class PersonalFinanceUI {

    private final JTabbedPane tabbedPane;
    private final JTextField salaryField;
    private JTextField nameField;
    private JTextField dateField;
    private JTextField amountField;
    private final JTable expenseTable;
    private final JTable budgetTable;
    private final JLabel totalExpenseLabel;
    private final JLabel balanceLabel;
    private JTextField budgetNameField;
    private JTextField budgetAmountField;
    private final JLabel totalBudgetValueLabel;
    private final JLabel budgetVarianceValueLabel;
    private final JTable budgetItemTable;

    private PersonalFinanceController controller;

    public PersonalFinanceUI() {
        //Create the frame
        JFrame frame = new JFrame("Personal Finance Tracker");

        // Create the tabbed pane
        tabbedPane = new JTabbedPane();

        //Create two panels for the tabs
        JPanel expensesTab = new JPanel(new GridBagLayout());
        expensesTab.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0, 0, 10, 0);

        JLabel salaryLabel = new JLabel("Monthly Salary:");
        expensesTab.add(salaryLabel, c);

        c.gridx = 1;
        salaryField = new JTextField(35);
        expensesTab.add(salaryField, c);

        c.gridx = 0;
        c.gridy = 1;
        JLabel nameLabel = new JLabel("Expense:");
        expensesTab.add(nameLabel, c);

        c.gridx = 1;
        nameField = new JTextField(35);
        expensesTab.add(nameField, c);

        c.gridx = 0;
        c.gridy = 2;
        JLabel dateLabel = new JLabel("Date (MM/DD/YYYY):");
        expensesTab.add(dateLabel, c);

        c.gridx = 1;
        dateField = new JTextField(35);
        expensesTab.add(dateField, c);

        c.gridx = 0;
        c.gridy = 3;
        JLabel amountLabel = new JLabel("Amount:");
        expensesTab.add(amountLabel, c);

        c.gridx = 1;
        amountField = new JTextField(35);
        expensesTab.add(amountField, c);

        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 10;
        JButton addExpenseButton = new JButton("Add Expense");

        addExpenseButton.addActionListener((ActionEvent e) -> {
            String name1 = nameField.getText();
            String dateString = dateField.getText();
            String amountString = amountField.getText();
            if (name1.equals("") || dateString.equals("") || amountString.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter all fields.");
            } else {
                try {
                    SimpleDateFormat format = new SimpleDateFormat("MM/DD/YYYY");
                    Date date = format.parse(dateString);
                    double amount = Double.parseDouble(amountString);
                    controller.addExpense(name1, date, amount);
                    updateExpenseTable();
                    updateTotalExpenseLabel();
                    updateBalanceLabel();
                    updateBalanceVarianceLabel();
                    nameField.setText("");
                    dateField.setText("");
                    amountField.setText("");
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid date (MM/DD/YYYY).");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid amount.");
                }
            }
        });
        expensesTab.add(addExpenseButton, c);

        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 2;
        expenseTable = new JTable();
        expenseTable.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Name", "Date", "Amount"}
        ));
        expenseTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(expenseTable);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(450, 200));
        expenseTable.setGridColor(Color.BLACK);
        expenseTable.setShowGrid(true);
        expensesTab.add(scrollPane, c);

        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 1;
        JLabel totalExpenseTitleLabel = new JLabel("Total Expenses:");
        expensesTab.add(totalExpenseTitleLabel, c);

        c.gridx = 1;
        totalExpenseLabel = new JLabel("");
        expensesTab.add(totalExpenseLabel, c);

        c.gridx = 0;
        c.gridy = 7;
        JLabel balanceTitleLabel = new JLabel("Balance:");
        expensesTab.add(balanceTitleLabel, c);

        c.gridx = 1;
        balanceLabel = new JLabel("");
        expensesTab.add(balanceLabel, c);

        //BUDGET TAB
        JPanel budgetTab = new JPanel(new GridBagLayout());
        budgetTab.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints c1 = new GridBagConstraints();
        c1.fill = GridBagConstraints.HORIZONTAL;
        
        //Add some components to budgetTab panel 

        c1.gridx = 0;
        c1.gridy = 0;
        c1.insets = new Insets(0, 0, 10, 0);
        budgetTab.add(new JLabel("Budget Item"), c1);

        c1.gridx = 1;
        budgetNameField = new JTextField(10);
        budgetTab.add(budgetNameField, c1);

        c1.gridx = 0;
        c1.gridy = 1;
        budgetTab.add(new JLabel("Amount"), c1);

        c1.gridx = 1;
        budgetAmountField = new JTextField(10);
        budgetTab.add(budgetAmountField, c1);

        c1.gridx = 0;
        c1.gridy = 2;
        c1.gridwidth = 2;
        JButton addBudgetButton = new JButton("Add Budget");
        addBudgetButton.addActionListener((ActionEvent e) -> {
            String budgetName = budgetNameField.getText();
            String budgetAmount = budgetAmountField.getText();
            if (budgetName.equals("") || budgetAmount.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter all fields.");
            } else {
                try {
                    double amount = Double.parseDouble(budgetAmount);
                    controller.addBudget(budgetName, amount);
                    updateBudgetTable();
                    updateTotalBudgetValueLabel();
                    updateBalanceVarianceLabel();
                    budgetNameField.setText("");
                    budgetAmountField.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid amount.");
                } catch (ParseException ex) {
                    Logger.getLogger(PersonalFinanceUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        budgetTab.add(addBudgetButton, c1);
        
        c1.gridx = 0;
        c1.gridy = 3;
        c1.gridwidth = 2;
        budgetTable = new JTable();
        budgetTable.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Budget Item", "Amount"}
        ));
        budgetTable.setFillsViewportHeight(true);
        JScrollPane scrollPane1 = new JScrollPane(budgetTable);
        scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane1.setPreferredSize(new Dimension(450, 200));
        budgetTable.setGridColor(Color.BLACK);
        budgetTable.setShowGrid(true);
        budgetTab.add(scrollPane1, c1);

        c1.gridx = 0;
        c1.gridy = 4;
        JLabel totalBudgetLabel = new JLabel("Total:");
        budgetTab.add(totalBudgetLabel, c1);

        c1.gridx = 1;
        totalBudgetValueLabel = new JLabel("");
        budgetTab.add(totalBudgetValueLabel, c1);

        c1.gridx = 0;
        c1.gridy = 5;
        JLabel budgetVarianceLabel = new JLabel("Variance:");
        budgetTab.add(budgetVarianceLabel, c1);

        c1.gridx = 1;        
        budgetVarianceValueLabel = new JLabel("");
        budgetTab.add(budgetVarianceValueLabel, c1);
        
        //Budget Items tab
        JPanel budgetItemTab = new JPanel(new GridBagLayout());
        budgetItemTab.setBorder(new EmptyBorder(0, 0, 0, 0));
        GridBagConstraints c2 = new GridBagConstraints();
        c2.fill = GridBagConstraints.HORIZONTAL;
        
        c2.gridx = 0;
        c2.gridy = 0;
        c2.insets = new Insets(0, 10, 10, 10);
        budgetItemTab.add(new JLabel("Name:"), c2);

        c2.gridx = 1;
        JTextField budgetItemNameField = new JTextField(35);
        budgetItemTab.add(budgetItemNameField, c2);
        
        c2.gridx = 0;
        c2.gridy = 1;
        c2.gridwidth = 10;
        JButton addBudgetItemButton = new JButton("Add Item");
        
        addBudgetItemButton.addActionListener((ActionEvent e) -> {
            String budgetItemName = budgetItemNameField.getText();
            if (budgetItemName.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter all fields.");
            } else {
                try {
                    controller.addBudgetItem(budgetItemName);
                    updateBudgetItemTable();
                } catch (ParseException ex) {
                    Logger.getLogger(PersonalFinanceUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        budgetItemTab.add(addBudgetItemButton, c2);
        
        c2.gridx = 0;
        c2.gridy = 2;
        c2.gridwidth = 2;
        budgetItemTable = new JTable();
        budgetItemTable.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Budget Item"}
        ));
        budgetItemTable.setFillsViewportHeight(true);
        JScrollPane scrollPane2 = new JScrollPane(budgetItemTable);
        scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane2.setPreferredSize(new Dimension(450, 200));
        budgetItemTable.setGridColor(Color.BLACK);
        budgetItemTable.setShowGrid(true);
        
        budgetItemTab.add(scrollPane2, c2);

        //Add the tab to the tabbed panel
        tabbedPane.addTab("Expenses", expensesTab);
        tabbedPane.addTab("Budget", budgetTab);
        tabbedPane.addTab("Budget Items", budgetItemTab);
        
        
        //Add the tabbed pane to the frame 
        frame.add(tabbedPane);
        // adjust the size of the frame according to its content
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        controller = new PersonalFinanceController();
    }

    private void updateExpenseTable() throws ParseException {
        DefaultTableModel model = (DefaultTableModel) expenseTable.getModel();
        model.setRowCount(0);
        for (Expense expense : controller.getExpenses()) {
            model.addRow(new Object[]{expense.getId(), expense.getName(),
                new SimpleDateFormat("MM/dd/yyyy").format(expense.getDate()), expense.getAmount()});
        }
    }

    private void updateTotalExpenseLabel() {
        double totalExpense = controller.getTotalExpenses();
        totalExpenseLabel.setText(String.format("$%.2f", totalExpense));
    }

    private void updateBalanceLabel() {
        double salary = 0;
        try {
            salary = Double.parseDouble(salaryField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid salary.");
            salaryField.setText("");
        }
        double balance = salary - controller.getTotalExpenses();
        balanceLabel.setText(String.format("$%.2f", balance));
    }

    private void updateBudgetTable() throws ParseException {
        DefaultTableModel model = (DefaultTableModel) budgetTable.getModel();
        model.setRowCount(0);
        for (Budget budget : controller.getBudgets()) {
            model.addRow(new Object[]{budget.getId(), budget.getName(), budget.getAmount()});
        }
    }
    
    private void updateBudgetItemTable() throws ParseException {
        DefaultTableModel model = (DefaultTableModel) budgetItemTable.getModel();
        model.setRowCount(0);
        for (BudgetItem budgetItem : controller.getBudgetItems()) {
            model.addRow(new Object[]{budgetItem.getId(), budgetItem.getName()});
        }
    }

    private void updateTotalBudgetValueLabel() {
        double totalBudget = controller.getTotalBudget();
        totalBudgetValueLabel.setText(String.format("$%.2f", totalBudget));
    }

    private void updateBalanceVarianceLabel() {
        double variance = 0.0;
        variance = controller.getTotalBudget() - controller.getTotalExpenses();
        budgetVarianceValueLabel.setText(String.format("$%.2f", variance));
    }
}
