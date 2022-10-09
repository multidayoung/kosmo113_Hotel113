package kr.co.hotel.mvc.aop;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.hotel.mvc.service.ReserveService;

@Component
@Aspect
public class RvstateChangeAdvice {

	@Autowired
	private ReserveService reserveService ;
	
	//예약하기 페이지와 예약 확인 페이지가 로딩되기 전,
	// rvstart <= 오늘 날짜 <=rvend 이면 rvstate=3(이용중)으로 변경
	// rvend < 오늘날짜 이면 rvstate=2(이용완료)으로 변경
	@Before("execution(* kr.co.hotel.*.controller.reserve.ReserveController.*F(..))")
	public void rvstateChange() {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		String today = f.format(new Date());
		reserveService.rvstateChangeService(today);		
	}
}
