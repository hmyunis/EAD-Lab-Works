package com.ioc.QSevenOnwards;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DisplayBooksServlet")
public class DisplayBooksServlet extends HttpServlet {
  private DBConnectionManager dbConnectionManager; // injected as a dependency

  private static final long serialVersionUID = 2L;
  private String query = "SELECT * FROM books";
  
  public DisplayBooksServlet(DBConnectionManager dbConnectionManager) {
    this.dbConnectionManager = dbConnectionManager;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("text/html");
    PrintWriter out = resp.getWriter();
    
    Connection conn = dbConnectionManager.getConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      ps = conn.prepareStatement(this.query);
      rs = ps.executeQuery();
      out.println("<h1>Books in Store</h1>");
      out.println("<table border='1'>");
      out.println("<tr>");
      out.println("<th>Title</th>");
      out.println("<th>Author</th>");
      out.println("<th>Price</th>");
      out.println("<th>Action</th>");
      out.println("</tr>");
      while (rs.next()) {
        out.println("<tr>");
        out.println("<td>" + rs.getString("title") + "</td>");
        out.println("<td>" + rs.getString("author") + "</td>");
        out.println("<td>" + rs.getDouble("price") + "</td>");
        out.println("<td><form action='DispatcherServlet?action=delete' method='post'><input type='hidden' name='id' value='" + rs.getInt("id") + "'><input type='submit' value='Delete'></form></td>");
        out.println("</tr>");
      }
      out.println("</table>");
    } catch (Exception e) {
      e.printStackTrace();
      out.println("<h1>Failed to display books</h1>");
    } finally {
      try {
        if (rs != null) {
          rs.close();
        }
        if (ps != null) {
          ps.close();
        }
        if (conn != null) {
          conn.close();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
