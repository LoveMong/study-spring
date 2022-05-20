package com.fastcampus.ch2;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class ExceptionController {
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 200 -> 500
	public String catcher(Exception ex, Model model) {
//		model.addAttribute("ex", ex); error.jsp에 isErrorPage="true" 추가하면 예외 객체를 모델에 담아 보내지 않아도 된다.		
		return "error"; // error 페이지가 리턴되면서 상태 코드 200번이되지만 @ResponseStatus 로 500번으로 바꿔줄 수 있다.		
	}

	@ExceptionHandler(NullPointerException.class)
	public String catcher2(Exception ex, Model model) {
//		model.addAttribute("ex", ex);
		return "error";
	}
	
	
	@RequestMapping("/ex")
	public String main() throws Exception {
		throw new Exception("예외가 발생했습니다.");		
	}
	@RequestMapping("/ex2")
	public String main2() throws Exception {
		throw new NullPointerException("예외가 발생했습니다.");	
	}

}
