package com.oraclejava;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Insert2")

public class Insert2 extends HttpServlet {
	
	@Override
	// 페이지를 새로고침하면 이부분이 실행되는 것임
	// goGet이라는 이 부분이 화면에 나오는 것임!
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 이걸 작성해줘야 한글 안깨짐
		resp.setContentType("text/html; charset=utf-8");
		// 여기에 작성한 내용이 화면에 출력됨
		PrintWriter out = resp.getWriter();
		
		out.println("<html><head><title>JDBC Insert 예제</title></head><body>");
		out.println("<h1> 정보를 입력하세요 </h1>");
		out.println("<form name =\"myform\" action =\"/web/Insert2\" method = \"post\">");
		
		out.println("이름 : <input type =\"text\" name=\"name\"><br>");
		out.println("e-mail : <input type =\"text\" name=\"email\"><br>");
		
		out.println("<input type=\"submit\" value=\"저장\">");
		out.println("</form></body></html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		// 한글 안깨지게..
        resp.setContentType("text/html; charset = utf-8");
        req.setCharacterEncoding("utf-8");
        
        try {
			// 오라클 드라이버를 Load 한다
			Class.forName("oracle.jdbc.OracleDriver");

			// 데이타 베이스에 접속을 한다.
			// 오라클에서 확인해서 @호스트이름 포트 서비스이름 에 넣어주면 됨.
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/oraclejava", "hr", "hr");
			con.setAutoCommit(false);
			
			String name = req.getParameter("name");
			String email = req.getParameter("email");
			
			stmt = con.createStatement();
			
			String query = "insert into employees(employee_id, last_name, email, hire_date, job_id) values ("
			+ "employees_seq.nextval,?,?,sysdate, 'IT_PROG')";
			
			// 보안에 취약하므로 PreparedStatement를 활용하여 ?로 입력값을 받는 것이 좋다.
			// 입력값을 그냥 받는 것은 해킹에 취약함
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			
			pstmt.executeUpdate();
			
			con.commit();
			stmt.close();
			
			resp.sendRedirect("/web/Insert2");
        } catch (ClassNotFoundException e) {
			System.out.println("Couldn't load database driver: " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("SQLException caught: " + e.getMessage());
		} finally {
			// 언제나 데이타 베이스 연결을 종료한다.
			try {
				if (con != null)
					con.close();
			} catch (SQLException ignored) {
			}
		}
        
        
	}
}
