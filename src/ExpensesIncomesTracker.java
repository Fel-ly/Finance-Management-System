import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;

public class ExpensesIncomesTracker extends JFrame {

    private final ExpenseIncomeTableModel tableModel;
    private final JTable table;
    private final JTextField dateField;
    private final JTextField descriptionField;
    private final JTextField amountField;
    private final JComboBox<String> typeComboBox;
    private final JButton addButton;
    private final JLabel balanceLabel;
    private double balance; // current balance

    // Constructor that initializes the application and sets up it's look
    public ExpensesIncomesTracker() {

        // apply the FlatDarkLaf look and feel
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        }
        catch (Exception e) {
            System.err.println("Attempt to set FlatDarkLaf LookAndFeel unsuccessful");
        }

        //setting up the color appearance of the form
        UIManager.put("TextField.foreground", Color.BLACK);
        UIManager.put("TextField.background", Color.LIGHT_GRAY);
        UIManager.put("TextField.caretforeground", Color.RED);
        UIManager.put("ComboBox.foreground", Color.ORANGE);
        UIManager.put("ComboBox.selectionForeground", Color.LIGHT_GRAY);
        UIManager.put("ComboBox.selectionBackground", Color.BLACK);
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.background", Color.BLUE);
        UIManager.put("Label.foreground", Color.WHITE);

        //set font
        Font customFont = new Font("Arial", Font.PLAIN,18);
        UIManager.put("Label.font", customFont);
        UIManager.put("TextField.font", customFont);
        UIManager.put("ComboBox.font", customFont);
        UIManager.put("Button.font", customFont);

        // initialize table model and balance variable
        balance = 0.0;
        tableModel = new ExpenseIncomeTableModel();
        //create JTable and set up a scroll  pane to display the data
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        //create input fields and components to add new entries
        dateField = new JTextField(10);
        descriptionField = new JTextField(20);
        amountField = new JTextField(10);
        typeComboBox = new JComboBox<>(new String[]{"Expense","Income"});

        //add actionListener to the "add" button to record new entry
        addButton = new JButton("Add");
        addButton.addActionListener(e -> addEntry());
        balanceLabel = new JLabel("Balance: KES " +balance);

        // create input panel to arrange input components
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Date"));
        inputPanel.add(dateField);

        inputPanel.add(new JLabel("Description"));
        inputPanel.add(descriptionField);

        inputPanel.add(new JLabel("Amount"));
        inputPanel.add(amountField);

        inputPanel.add(new JLabel("Type"));
        inputPanel.add(typeComboBox);

        inputPanel.add(addButton);

        // bottom panel  to display Balance
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(balanceLabel);
        setLayout(new BorderLayout());

        //  positioning the components in the main frame
        add(inputPanel,BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        //title, default close operation and visibility of frame
        setTitle("EXPENSES AND INCOMES TRACKER");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

    }

    // method that handles addition of new entries to the table
    private void addEntry(){
        //get input values from inout fields
        String date  = dateField.getText();
        String description = descriptionField.getText();
        String amountStr = amountField.getText();
        String type = (String) typeComboBox.getSelectedItem();
        double amount;

        // validate amount input value
        if(amountStr.isEmpty()){
            JOptionPane.showMessageDialog(this,"Amount field is required", "Error:", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            amount = Double.parseDouble(amountStr);
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,"Invalid Amount Format", "Error:", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // set expenses as negative values
        if(type.equals("Expense")){
            amount *=-1;
        }

        // create and add a new entry to the table
        ExpenseIncomeEntry entry = new ExpenseIncomeEntry(date, description, amount, type);
        tableModel.addEntry(entry);
        // update balance to display new balance
        balance += amount;
        balanceLabel.setText("Balance: KES " +balance);

        clearInputFields();
    }

    // method to clear input fields for the next entry
    private void clearInputFields(){
        dateField.setText("");
        descriptionField.setText("");
        amountField.setText("");
        typeComboBox.setSelectedIndex(0);
    }


}
