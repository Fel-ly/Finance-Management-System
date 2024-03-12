// This class contains a representation of a single entry for expenses and incomes.
public class ExpenseIncomeEntry {

    private String date;
    private String description;
    private double amount;
    private String type; // expense or income

    public ExpenseIncomeEntry(String date, String description, double amount, String type){
        this.date = date;
        this.description = description;
        this.amount = amount;
        this.type = type;
    }

    public String getDate() {
        return date;
    }
    public String getDescription() {
        return description;
    }
    public double getAmount() {
        return amount;
    }
    public String getType() {
        return type;
    }
}
