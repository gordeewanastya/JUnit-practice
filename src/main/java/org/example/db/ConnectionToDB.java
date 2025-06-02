package org.example.db;

import java.sql.*;

public class ConnectionToDB {
  private static final String URL = "jdbc:mysql://localhost:3306/user_schema";
  private static final String USER = "root";
  private static final String PASSWORD = "root123";

  public static void main(String[] args) {
    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
      String queryCreateTable =
          """
        CREATE TABLE IF NOT EXISTS users (
        id INT PRIMARY KEY AUTO_INCREMENT,
        name VARCHAR(100),
        email VARCHAR(100)
        );
    """;

      try (Statement statement = connection.createStatement()) {
        statement.execute(queryCreateTable);
        System.out.println("Table has been created or already exists.");
      }

      String insertSQL = "INSERT INTO users (name, email) VALUES (?, ?)";
      try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
        preparedStatement.setString(1, "John");
        preparedStatement.setString(2, "john@gmail.com");
        preparedStatement.execute();

        preparedStatement.setString(1, "Jacob");
        preparedStatement.setString(2, "jacob20@gmail.com");
        preparedStatement.execute();

        preparedStatement.setString(1, "Charlie");
        preparedStatement.setString(2, "charlie@example.com");
        preparedStatement.executeUpdate();

        System.out.println("Data has been inserted.");
      }

      String selectSQL = "SELECT * FROM users";
      try (Statement statement = connection.createStatement()) {
        ResultSet resultSet = statement.executeQuery(selectSQL);
        System.out.println("List of users:");

        while (resultSet.next()) {
          int id = resultSet.getInt("id");
          String name = resultSet.getString("name");
          String email = resultSet.getString("email");

          System.out.printf("ID: %d, Name: %s, Email: %s\n", id, name, email);
        }
      }

    } catch (SQLException e) {
      System.err.println("An exception occured while working with db: " + e.getMessage());
    }
  }
}
