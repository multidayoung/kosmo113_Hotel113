package kr.co.hotel.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//예외를 컨트롤하기 위해서 @ControllerAdvice선언해둔다.
@ControllerAdvice
public class MyExceptionHandler {
	
	//모든 예외를 잡는다.
	@ExceptionHandler(Exception.class)
	public String myHandlerExeption(Model m, Exception e) {
		e.printStackTrace();
		System.out.println("예외메세지 : "+e.getMessage());
	
		if (e instanceof UnsatisfiedServletRequestParameterException) {
			m.addAttribute("emsg", "파라미터값을 올바르게 작성하시오");
			return "error/paramException";
		}else if (e instanceof MissingServletRequestParameterException) {
			m.addAttribute("emsg", "파라미터값을 올바르게 작성하시오2");
			return "error/paramException";	
		}else {
			m.addAttribute("emsg", "예외가 발생 되나요?");
			return "error/paramException";
		}
		
	}
}
