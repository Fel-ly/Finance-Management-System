import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Expense_Income_Tracker {

    public static void main(String[] args) {

        new ExpensesIncomesTracker().setLocationRelativeTo(null);
        String url  = "jdbc:mysql://localhost:3306/Expense_Income_Tracker";
        String username = "root";
        String password = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from Tracker");

            while (resultSet.next()){
                System.out.println(resultSet.getDate(1) + " " + resultSet.getString(2) + " " + resultSet.getDouble(3));
            }

            connection.close();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
