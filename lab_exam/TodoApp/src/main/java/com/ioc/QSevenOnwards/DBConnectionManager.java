package com.ioc.QSevenOnwards;

import java.sql.Connection;
import java.sql.DriverManager;

import lombok.Data;

@Data
public class DBConnectionManager {
  private String url;
  private String username;
  private String password;

  public Connection getConnection() {
    Connection connection = null;
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      connection = DriverManager.getConnection(this.url, this.username, this.password);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return connection;
  }

  public void closeConnection(Connection connection) {
    try {
      connection.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
