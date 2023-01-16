package com.oraclejava;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {
	
	public String nowTime() {
        // 현재 날짜/시간
        LocalDateTime now = LocalDateTime.now();

        // 포맷팅
        String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
 
        // 포맷팅 현재 날짜/시간 출력
        return formatedNow;  // 2021년 06월 17일 06시 43분 21초
	}
	
	@Override
	// 페이지를 새로고침하면 이부분이 실행되는 것임
	// goGet이라는 이 부분이 화면에 나오는 것임!
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("hello world~!!");
    }

    @Override
    // 서버 종료
    public void destroy() {
        System.out.println("Servlet " + this.getServletName() + " has stopped.");
    }

    @Override
    // 요청시 처음에 뜨는 것
    public void init() throws ServletException {
       System.out.println("Servlet " + this.getServletName() + " has started.");
    }

}
