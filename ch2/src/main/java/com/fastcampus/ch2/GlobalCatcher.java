package com.fastcampus.ch2;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // 모든 패키지 적용
//@ControllerAdvice("com.fastcampus.ch2") 지정된 패키지에서 발생한 예외만 처리
public class GlobalCatcher {
	
//	@ExceptionHandler(Exception.class)
//	public String catcher(Exception ex, Model model) {
//		model.addAttribute("ex", ex);
//		return "error";
//	}

//	@ExceptionHandler(NullPointerException.class)
//	public String catcher2(Exception ex, Model model) {
//		model.addAttribute("ex", ex);
//		return "error";
//	}

}
