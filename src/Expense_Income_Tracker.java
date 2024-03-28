import java.sql.*;

public class Expense_Income_Tracker {

    public Expense_Income_Tracker() {
        String url = "jdbc:mysql://localhost:3306/Expense_Income_Tracker";
        String username = "root";
        String password = "";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            // the data insertion is handled in the ExpensesIncomesTracker class

            statement.close();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new ExpensesIncomesTracker();
    }
}
