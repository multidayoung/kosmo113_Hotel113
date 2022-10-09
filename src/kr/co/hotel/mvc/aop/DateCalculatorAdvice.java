package kr.co.hotel.mvc.aop;



import java.time.LocalDate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.text.DateFormatter;

import org.aspectj.lang.JoinPoint;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.format.datetime.joda.LocalDateParser;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
@Aspect
public class DateCalculatorAdvice {
	
	@AfterReturning(pointcut = "execution(* kr.co.hotel.mvc.controller.*.*Controller.*Date(..))",
			returning = "m")
	public ModelAndView dateCalculator(JoinPoint jp,ModelAndView m) {

		

		if(m!=null) {

			LocalDateTime sdt = LocalDateTime.parse((String) m.getModelMap().get("snum"),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
;			LocalDateTime cunum = LocalDateTime.now();
			if(sdt.isBefore(cunum))sdt=cunum;
			
			LocalDateTime ldf = LocalDateTime.parse((String) m.getModelMap().get("lnum"),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

			
			ArrayList<String> dates = new ArrayList<String>();
			ArrayList<String> days = new ArrayList<String>();
			ArrayList<String> daytimes = new ArrayList<String>();
			
			
			for(LocalDateTime date=sdt; date.isBefore(ldf); date=date.plusDays(1)) {
				daytimes.add(date.toString());
				dates.add(date.toLocalDate().toString());
				days.add(date.getDayOfWeek().toString());

			}
			m.addObject("daytimes", daytimes);
			m.addObject("dates", dates);
			m.addObject("days", days);

		}
		return m;
	}
}
