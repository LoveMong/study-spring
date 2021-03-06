package com.fastcampus.ch2;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import javax.xml.crypto.Data;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/register")
public class RegisterController {

//	GET요청이면(POST X) servlet-context.xml 에서 view-controller로 jsp 매핑 가능
//	@RequestMapping("/register/add")
//	@RequestMapping(value = "/register/save", method = {RequestMethod.GET, RequestMethod.POST)
//	@GetMapping("/register/add")
//	public String register() {
//		return "registerForm";
//	}
	
	@InitBinder
	public void toDate(WebDataBinder binder) {
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		binder.registerCustomEditor(Data.class,	new CustomDateEditor(df, false));
		binder.registerCustomEditor(String[].class,	new StringArrayPropertyEditor("#"));
		binder.setValidator(new UserValidator()); // UserValidator를 WebDataBinder의 로컬 validator로 등록
	}
	
	@GetMapping("/add")
	public String add() {
		return "registerForm";		
	}

	
//	@RequestMapping(value = "/register/save", method = RequestMethod.POST)
	@PostMapping("/save") // 4.3버전 이후 부터 사용 가능
	public String save(@Valid User user, BindingResult result, Model model) throws Exception {
		
		System.out.println("user : " + user);
		
		// 수동 검증 - Validator를 직접 생성하고, validate()를 직접 호출
//		UserValidator userValidator = new UserValidator();
//		userValidator.validate(user, result);
		
		// User객체를 검증한 결과 에러가 있으면, registerForm을 이용해서 에러를 보여줘야 함.		
		if (result.hasErrors()) {
			return "registerForm";			
		}
		
////		1. 유효성 검사
//		
//		if(!isValid(user)) {
//			String msg = "id를 잘못입력하셨습니다.";
//			model.addAttribute("msg", msg);
//			return "redirect:/register/add";
//			
////			String msg = URLEncoder.encode("id를 잘못입력하셨습니다.", "utf-8");
////			return "redirect:/register/add?msg=" + msg; // URL 재작성(ReWriting)
//		}
		
		
//		2. DB에 신규회원 정보를 저장
		return "registerInfo";		
	}

	private boolean isValid(User user) {
		return true;
	}

}
