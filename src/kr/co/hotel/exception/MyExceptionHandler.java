package kr.co.hotel.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//���ܸ� ��Ʈ���ϱ� ���ؼ� @ControllerAdvice�����صд�.
@ControllerAdvice
public class MyExceptionHandler {
	
	//��� ���ܸ� ��´�.
	@ExceptionHandler(Exception.class)
	public String myHandlerExeption(Model m, Exception e) {
		e.printStackTrace();
		System.out.println("���ܸ޼��� : "+e.getMessage());
	
		if (e instanceof UnsatisfiedServletRequestParameterException) {
			m.addAttribute("emsg", "�Ķ���Ͱ��� �ùٸ��� �ۼ��Ͻÿ�");
			return "error/paramException";
		}else if (e instanceof MissingServletRequestParameterException) {
			m.addAttribute("emsg", "�Ķ���Ͱ��� �ùٸ��� �ۼ��Ͻÿ�2");
			return "error/paramException";	
		}else {
			m.addAttribute("emsg", "���ܰ� �߻� �ǳ���?");
			return "error/paramException";
		}
		
	}
}