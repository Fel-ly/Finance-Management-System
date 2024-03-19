import java.sql.*;
import java.util.Scanner;

public class Expense_Income_Tracker {

    public static void main(String[] args) {

        new ExpensesIncomesTracker().setLocationRelativeTo(null);
        String url  = "jdbc:mysql://localhost:3306/Expense_Income_Tracker";
        String username = "root";
        String password = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);

//            Statement statement = connection.createStatement();
//
//            ResultSet resultSet = statement.executeQuery("select * from Tracker");
//
//            while (resultSet.next()){
//                System.out.println(resultSet.getDate(1) + " " + resultSet.getString(2) + " " + resultSet.getDouble(3));
//            }

            // Scanner object for user input
            Scanner sc = new Scanner(System.in);

            // prompt user to enter input
            System.out.println("Enter date (YYYY-MM-DD): ");
            String date = sc.nextLine();

            System.out.println("Enter description: ");
            String description = sc.nextLine();

            System.out.println("Enter amount: ");
            double amount = sc.nextDouble();

            // SQL statement to insert data into the Tracker table
            String insertQuery = "INSERT INTO Tracker (date, description, amount) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, date);
            preparedStatement.setString(2, description);
            preparedStatement.setDouble(3, amount);

            //execution of the insert statement
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Data successfully inserted =) ");
            }
            else {
                System.out.println("Insertion failed =( ");
            }

            preparedStatement.close();
            connection.close();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
