package kr.co.hotel.mvc.aop;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

@Component
@Aspect

public class TileAop {


	@Before("execution(* kr.co.hotel.mvc.controller.*.*Controller.*list(..))")
	public void beforeToday(JoinPoint jp) {
		System.out.println("Name"+jp.getTarget().getClass().getName());
		System.out.println("Name"+jp.getSignature().getName());
		Object[] args = jp.getArgs(); 
		
		for(Object e : args) {
			if(e instanceof Model) {
				
				System.out.println("1=>"+((Model)e).toString());
				Model m =(Model) e;
				m.addAttribute("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				
				
			}else {
				System.out.println(e);

			}
		}
	}

	/*public ModelAndView afterToday(JoinPoint jp, ModelAndView mav) {
		mav.addObject("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		return mav;
	}*/
}
