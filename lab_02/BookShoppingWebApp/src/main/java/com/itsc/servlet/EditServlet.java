package com.itsc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/editurl")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 4L;
	private static final String query = "update books set BOOKNAME=?,BOOKEDITION=?,BOOKPRICE=? where id=?";
	
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //get PrintWriter
        PrintWriter pw = res.getWriter();
        //set content type
        res.setContentType("text/html");
        
        //get the id of record
        int id = Integer.parseInt(req.getParameter("id"));
        
        //get the edit data we want to edit
        String bookName = req.getParameter("bookName");
        String bookEdition = req.getParameter("bookEdition");
        float bookPrice = Float.parseFloat(req.getParameter("bookPrice"));
        
        //load jdbc driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnf) {
            cnf.printStackTrace();
        }
        //generate the connection
        try (Connection con = DriverManager.getConnection("jdbc:mysql:///bookregister", "root", "3832"); PreparedStatement ps = con.prepareStatement(query);) {
            ps.setString(1, bookName);
            ps.setString(2, bookEdition);
            ps.setFloat(3, bookPrice);
            ps.setInt(4, id);
            
            int count = ps.executeUpdate();
            
            if (count == 1) {
                pw.println("<h2>Record is edited successfully.</h2>");
            } else {
                pw.println("<h2>Record is not edited successfully.</h2>");
            }
        } catch (SQLException se) {
            se.printStackTrace();
            pw.println("<h1>" + se.getMessage() + "</h2>");
        } catch (Exception e) {
            e.printStackTrace();
            pw.println("<h1>" + e.getMessage() + "</h2>");
        }
        pw.println("<a href='Home.html'>Home</a>");
        pw.println("<br>");
        pw.println("<a href='bookList'>Book List</a>");
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}
