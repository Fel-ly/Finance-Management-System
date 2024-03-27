import java.sql.*;
import java.util.Date;

public class Expense_Income_Tracker {

    public Record record;

    public Expense_Income_Tracker() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/Expense_Income_Tracker";
        String username = "root";
        String password = "";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            // SQL statement to insert data into the Tracker table
            String insertQuery = "INSERT INTO tracker (date, description, amount, type) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            String date = null;
            String description = null;
            double amount = 0;
            boolean type = false;

            preparedStatement.setString(1, date);
            preparedStatement.setString(2, description);
            preparedStatement.setDouble(3, amount);
            preparedStatement.setBoolean(4, type);

            // execution of the insert statement
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Data successfully inserted =) ");
            } else {
                System.out.println("Insertion failed =( ");
            }

            connection.commit();
            statement.close();
            connection.close();

        } catch (SQLException e ){
            throw new RuntimeException(e);
        }
    }

        public static void main(String[] args){

            new ExpensesIncomesTracker();


        }


}
