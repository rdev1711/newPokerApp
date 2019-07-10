
import java.sql.*;

public class SQLWriter {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/poker_odds?useTimezone=true&serverTimezone=UTC";
            Connection connection = DriverManager.getConnection(url, "root", "Com29dus");
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO poker_odds     " +
                    "VALUES (1001, 'Simpson', 'Mr.', 'Springfield', 2001)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
