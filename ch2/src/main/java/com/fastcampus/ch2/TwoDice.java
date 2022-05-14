package com.fastcampus.ch2;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 실행 할때마다 두개의 다른 주사위 이미지 출력(동적 리소스 출력)
// 정적 리소스(이미지, js, css, html ...)
@Controller 
public class TwoDice {
	@RequestMapping("/rollDice")
	public void main(HttpServletResponse response) throws IOException {
		// Math.random() : 0 - 1 (1은미포함) 구간 부동소수점 난수생성 
		int idx1 = (int)(Math.random()*6)+1;
		int idx2 = (int)(Math.random()*6)+1;
		
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("</head>");
		out.println("<body>");
		out.println("<img src ='resources/img/dice" + idx1 + ".jpg'>");
		out.println("<img src ='resources/img/dice" + idx2 + ".jpg'>");
		out.println("<body>");
		out.println("</html>");				
	}

}
