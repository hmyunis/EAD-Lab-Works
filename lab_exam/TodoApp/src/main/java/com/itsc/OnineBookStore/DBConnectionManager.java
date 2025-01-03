package com.itsc.OnineBookStore;

import java.sql.Connection;
import java.sql.DriverManager;

import lombok.Data;

@Data
public class DBConnectionManager {
  private static String url = "jdbc:mysql://localhost:3306/bookstore_db";
  private static String username = "root";
  private static String password = "3832";

  public static Connection getConnection() {
    Connection connection = null;
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      connection = DriverManager.getConnection(url, username, password);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return connection;
  }

  public static void closeConnection(Connection connection) {
    try {
      connection.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
