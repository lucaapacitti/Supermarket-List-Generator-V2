package listgenerator;

import java.sql.*;

public class Test
{
    public static void main(String[] args)
    {
        String url = "jdbc:mysql://127.0.0.1:3306/SupermarketListGeneratorDatabase";
        String user = "administrator";
        String password = "jean-philippe-mateta";

        try (Connection connection = DriverManager.getConnection(url, user, password))
        {
            String query = "INSERT INTO Users (username, forename, surname, passwordHash, email) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query))
            {
                preparedStatement.setString(1, "luca.test");
                preparedStatement.setString(2, "Luca");
                preparedStatement.setString(3, "Test");
                preparedStatement.setString(4, "123456789012345678901234567890123456789012345678901234567890");
                preparedStatement.setString(5, "luca.test@domain.com");
                preparedStatement.executeUpdate();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

// To connect as superuser: docker-compose exec db mysql -u root -p           PW: sturdy-space-doodle
// USE SupermarketListGeneratorDatabase;
// SHOW TABLES;
// DESCRIBE table_name;
// SELECT * FROM table_name;
// INSERT INTO table_name (column1, column2) VALUES ('value1', 'value2');
