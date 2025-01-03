package com.ioc.QSevenOnwards;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DispatcherServlet")
public class DispatcherServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String action = req.getParameter("action");
    RequestDispatcher dispatcher = null;
    switch (action) {
      case "add":
        dispatcher = req.getRequestDispatcher("/BookRegistrationServlet");
        break;
      case "display":
        dispatcher = req.getRequestDispatcher("/DisplayBooksServlet");
        break;
      case "delete":
        dispatcher = req.getRequestDispatcher("/DeleteBookServlet");
        break;
      case "search":
        dispatcher = req.getRequestDispatcher("/SearchBooksServlet");
        break;
      default:
        resp.getWriter().println("Invalid action");
        return;
    }
    dispatcher.forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doGet(req, resp);
  }
}
