package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 1. 원격 호출가능한 프로그램으로 등록
/*
 로컬 프로그램 실행 -> 터미널 -> java Main(class) [enter] -> class의 static main()메소드 실행(static이므로 자바인터프리터가 객체생성 필요없이 바로 실행)
 원격(다른 컴퓨터) 프로그램을 구동
  -> 브라우저 + WAS 필요 
  -> 브라우저 URL(111.222.333.8080/ch2/hello) 아이피 주소 + 톰켓 포트번호 + 프로그램과 연결됨 URL 
  1. 프로그램 등록
  2. URL과 프로그램 연결 
 * 현재 내가 공부하고 프로젝트 개발하는건 로컬 컴퓨터(내 컴퓨터)에서 브라우저랑 톰켓으로 작업(그래서 url주소에서 localhost:0808으로 시작) 
 */
@Controller
public class Hello {
	int iv = 10; // 인스턴스 변수
	static int cv = 20; // static 변수
	
	// 2. URL과 메서드를 연결
	@RequestMapping("/hello") // RequestMapping 접근 제어자와 상관없이 호출할 수 있게 되어있다.
	private void main() { // 인스턴스 메소드 - iv, cv를 둘다 사용가능
	// 접근 제어자 private이라도 호출 가능(단, 다른 클래스에서는 사용 불가능)		
		System.out.println("Hello");
		System.out.println("iv"); // 사용 가능
		System.out.println("cv"); // 사용 가능
	}
	
	public static void main2() { // static 메서드 - cv만 사용 가능
	 // System.out.println(iv); // 사용 안됨
		System.out.println(cv); // 사용 가능
				
	}

}
