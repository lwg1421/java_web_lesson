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


@WebServlet("/MovieInfo")
public class MovieInfo extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        // 한글 안깨지게..
        resp.setContentType("text/html; sharset = utf-8");
        PrintWriter out = resp.getWriter();
        
        try {
        	Class.forName("oracle.jdbc.OracleDriver");
        	
        	con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/oraclejava", "movie", "movie");
        	
        	stmt = con.createStatement();
        	
        	rs = stmt.executeQuery("select movie_id, movie_name,year_of_release  from movie");
        	
        	out.println("<html><head><title>MovieInfo</title></head>");
        	out.println("<body><h1>Movie List</h1>");
        	out.println("<ul>");
        	while (rs.next()) {
        		out.println("<li>" + rs.getString("movie_id")+ " " + rs.getString("movie_name")+ " " + rs.getString("year_of_release"));
        		
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