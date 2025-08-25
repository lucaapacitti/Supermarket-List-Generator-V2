package listgenerator;

import java.sql.*;

public class Test
{
    public static void main(String[] args)
    {
        String url = "jdbc:mysql://127.0.0.1:3306/mydb";
        String user = "administrator";
        String password = "jean-philippe-mateta";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS users (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50))");
            stmt.executeUpdate("INSERT INTO users (name) VALUES ('Charlie')");

            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " → " + rs.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}