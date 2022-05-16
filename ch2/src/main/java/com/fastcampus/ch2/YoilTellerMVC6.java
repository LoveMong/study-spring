package com.fastcampus.ch2;


import java.io.IOException;
import java.util.Calendar;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
 * @Controller의 매개변수
 *  - @RequestParam : 기본형, String 때에는 기본적으로 생략되어 있다. 
 *  - @ModelAttribute : 매개변수가 참조형일 때는 기본적으로 생략되어 있다.
 */

// 년월일을 입력하면 요일을 알려주는 프로그램
@Controller
public class YoilTellerMVC6 {
	
	@ExceptionHandler(Exception.class)
	public String catcher(Exception ex, BindingResult result) {
		System.out.println("result : " + result);
		FieldError error = result.getFieldError();
		
		System.out.println("code = " +  error.getCode());
		System.out.println("field = " +  error.getField());
		System.out.println("msg = " +  error.getDefaultMessage());

		
		ex.printStackTrace();
		return "yoilError";		
	}
	
	@RequestMapping("/getYoilMVC6") // 반환타입 void로 하면 매핑할 때에 지정한 URL.jsp로 자동 리턴(예: getYoilMVC.jsp)
	public String main(MyDate date, BindingResult result) { 

		// 1. 유효성 검사
		if(!isValid(date)) {			
			return "yoilError";			
		}		
		
		// 2. 작업
		// char yoil = getYoil(date);
		
		// 3. 계산한 결과를 model에 저장
//		model.addAttribute("myDate", date);
//		model.addAttribute("yoil", yoil);
		
		return "yoil"; // /WEB-INF/views/yoil.jsp

				
	}

	private boolean isValid(MyDate date) {
		// TODO Auto-generated method stub
		return true;
	}
	
	private @ModelAttribute("yoil") char getYoil(MyDate date) { // 반환 타입앞에 @ModelAttribute("key값")하면 자동으로 모델에 저장해준다.		
		return getYoil(date.getDay(), date.getMonth(), date.getYear());
		
	}

	private char getYoil(int yyyy, int mm, int dd) {
		Calendar cal = Calendar.getInstance();
		cal.set(yyyy, mm - 1, dd);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 1: 일요일, 2:월요일 ...
		return " 일월화수목금토".charAt(dayOfWeek);
	}


}
