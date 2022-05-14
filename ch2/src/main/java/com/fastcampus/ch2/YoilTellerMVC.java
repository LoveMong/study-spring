package com.fastcampus.ch2;


import java.io.IOException;
import java.util.Calendar;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// 년월일을 입력하면 요일을 알려주는 프로그램
@Controller
public class YoilTellerMVC {
	
	@RequestMapping("/getYoilMVC") // 반환타입 void로 하면 매핑할 때에 지정한 URL.jsp로 자동 리턴(예: getYoilMVC.jsp)
	public String main(int year, int month, int day, Model model) throws IOException {
		
		// 1. 유효성 검
		if(!isValid(year, month, day)) {			
			return "yoilError";			
		}		
		
		// 2. 작업
		char yoil = getYoil(year, month, day);
		
		// 3. 계산한 결과를 model에 저장
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		model.addAttribute("day", day);
		model.addAttribute("yoil", yoil);
		
		return "yoil"; // /WEB-INF/views/yoil.jsp

				
	}

	private boolean isValid(int year, int month, int day) {
		// TODO Auto-generated method stub
		return true;
	}

	private char getYoil(int yyyy, int mm, int dd) {
		Calendar cal = Calendar.getInstance();
		cal.set(yyyy, mm - 1, dd);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 1: 일요일, 2:월요일 ...
		return " 일월화수목금토".charAt(dayOfWeek);
	}


}
