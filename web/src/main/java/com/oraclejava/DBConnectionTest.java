package com.oraclejava;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/DBConnectionTest")
public class DBConnectionTest extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        // 한글 안깨지게..
        resp.setContentType("text/html; sharset = utf-8");
        PrintWriter out = resp.getWriter();
        
        try {
        	Class.forName("oracle.jdbc.OracleDriver");
        	
        	con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/oraclejava", "hr", "hr");
        	
        	stmt = con.createStatement();
        	
        	rs = stmt.executeQuery("select employee_id, first_name from employees");
        	
        	out.println("<html><head><title>Phonebook</title></head>");
        	out.println("<body>");
        	out.println("<ul>");
        	while (rs.next()) {
        		out.println("<li>" + rs.getString("employee_id")+ " " + rs.getString("first_name"));
        		
        	}
        	out.println("</ul>");
        	out.println("</body></html>");
        }catch (ClassNotFoundException e){
			System.out.println("Could not load database driver : "+ e.getMessage());
			
		} catch(SQLException e) {
			System.out.println("SQLException caught : " + e.getMessage());
		} finally {
			// 반드시 finally를 해주어야함
			// 무조건 언제나 DB 연결을 종료해주도록!
			try {
				if (con != null) {
					con.close(); }
			} catch (SQLException ignored) {
				
			}
		}
    }

}
