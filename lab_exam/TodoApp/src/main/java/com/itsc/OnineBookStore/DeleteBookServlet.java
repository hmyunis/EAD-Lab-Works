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

@WebServlet("/DeleteBookServlet")
public class DeleteBookServlet extends HttpServlet {

  private static final long serialVersionUID = 3L;
  private String query = "DELETE FROM books WHERE id = ?";

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String id = req.getParameter("id");

    resp.setContentType("text/html");
    PrintWriter out = resp.getWriter();

    if (id == null || id.trim().isEmpty()) {
      out.println("<h1>Invalid input</h1>");
    } else {
      Connection conn = DBConnectionManager.getConnection();
      PreparedStatement ps = null;
      try {
        ps = conn.prepareStatement(this.query);
        ps.setInt(1, Integer.parseInt(id));
        ps.executeUpdate();
        out.println("<h1>Book deleted successfully</h1>");
      } catch (Exception e) {
        e.printStackTrace();
        out.println("<h1>Book deletion failed</h1>");
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
