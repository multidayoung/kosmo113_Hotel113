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
	
	//�����ϱ� �������� ���� Ȯ�� �������� �ε��Ǳ� ��,
	// rvstart <= ���� ��¥ <=rvend �̸� rvstate=3(�̿���)���� ����
	// rvend < ���ó�¥ �̸� rvstate=2(�̿�Ϸ�)���� ����
	@Before("execution(* kr.co.hotel.*.controller.reserve.ReserveController.*F(..))")
	public void rvstateChange() {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		String today = f.format(new Date());
		reserveService.rvstateChangeService(today);		
	}
}
