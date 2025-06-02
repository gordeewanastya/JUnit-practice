package org.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfiguration {
  private static final String URL = "jdbc:mysql://localhost:3306/user_schema";
  private static final String USER = "root";
  private static final String PASSWORD = "root123";

  public Connection getConnection() throws SQLException {
    return DriverManager.getConnection(URL, USER, PASSWORD);
  }
}
