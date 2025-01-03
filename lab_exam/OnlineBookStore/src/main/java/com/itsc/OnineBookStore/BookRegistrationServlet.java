package com.itsc.OnineBookStore;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/BookRegistrationServlet")
public class BookRegistrationServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private String query = "INSERT INTO books (title, author, price) VALUES (?, ?, ?)";
  
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String title = req.getParameter("title");
    String author = req.getParameter("author");
    double price = Double.parseDouble(req.getParameter("price"));
    
    resp.setContentType("text/html");
    PrintWriter out = resp.getWriter();

    // validate the input from the client
    if (title == null || title.trim().isEmpty() || author == null || author.trim().isEmpty()) {
      out.println("<h1>Invalid input</h1>");
    } else {
      // estabilish DB connection if valid inputs are received
      Connection conn = DBConnectionManager.getConnection();
      PreparedStatement ps = null;
      try {
        ps = conn.prepareStatement(this.query);
        ps.setString(1, title);
        ps.setString(2, author);
        ps.setDouble(3, price);
        ps.executeUpdate();
        out.println("<h1>Book registered successfully</h1>");
      } catch (Exception e) {
        e.printStackTrace();
        out.println("<h1>Book registration failed</h1>");
      } finally {
        try {
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
}
