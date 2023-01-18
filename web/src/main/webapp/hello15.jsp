<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
 
<!DOCTYPE html>
   <html>
   <head>
      <meta charset="UTF-8">
      <title>Insert title here</title>
   </head>
   
   <body>
   <h1>사원 명부</h1>
      <%
         Connection con = null;
         Statement stmt = null;
         ResultSet rs = null;
      
         try {
            // oracle 드라이버 로드
            Class.forName("oracle.jdbc.OracleDriver");
            
            // 데이터베이스에 접속
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/oraclejava", "hr", "hr");
            
            // Statement ocject생성
            stmt = con.createStatement();
            
            // ResultSet을 얻기 위해 Sql query 실행
            rs = stmt.executeQuery("SELECT employee_id, last_name, first_name FROM employees");
            
            // 결과 출력
            out.println("<ul>");
            while (rs.next()) {
               out.println("<li>" + rs.getString("employee_id") + " " 
                              + rs.getString("last_name") + " "
                              + rs.getString("last_name") + "</li>");
            } 
            out.println("</ul>");
         
            
         } catch (ClassNotFoundException e) {
            System.out.println("Could't load database diver" + e.getMessage());
            
         } catch (SQLException e) {
            System.out.println("SQL에러입니다. 에러메세지: " + e.getMessage());
            
         }   finally {
               try {
                  if (rs != null) { rs.close(); }
                  if (stmt != null) { stmt.close(); }
                  if (con != null) { con.close(); }
                  
               } catch (SQLException ignored) {
                  
               }
         }// finally
      %>
   </body>
</html>