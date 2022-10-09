package kr.co.hotel.mvc.aop;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.hotel.mvc.dao.ClickDaoInter;
import kr.co.hotel.vo.ClickVO;

@Component
@Aspect
public class ClickAdvice {
	
	@Autowired
	private ClickDaoInter clickDaoInter;
	
	@After("execution(* kr.co.hotel.mvc.controller.room.RoomController.detail*(..))")
	public void clickModule(JoinPoint jp) {
		Object obj[] = jp.getArgs(); //�޼����� ���ڰ� ���� �迭�� �ֱ�
		HttpSession session = (HttpSession) obj[0];
		String mid = (String) session.getAttribute("sessionid"); //�α������� �� ���ǿ� �����ص� id�� �����´�.
		int rnum = (int) obj[1]; //���ǹ�ȣ
		ClickVO cvo = new ClickVO();
		int age = 0;
		if (mid != null) { //ȸ���� ��� 
			age = clickDaoInter.getAge(mid);
			System.out.println(mid+"�� age=>"+age);
		}else { //��ȸ���� ���
			mid = "nologin";
			age = 1;
		}
		cvo.setRnum(rnum); //���ǹ�ȣ
		cvo.setMid(mid); //���̵�
		cvo.setAge(age); //����
		cvo.setCnt(1); //Ŭ����	
		clickDaoInter.addClick(cvo);
	}
}
