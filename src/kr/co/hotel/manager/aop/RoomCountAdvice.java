package kr.co.hotel.manager.aop;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import kr.co.hotel.manager.dao.RoomDaoInter;

@Component
@Aspect
public class RoomCountAdvice {
	@Autowired
	private RoomDaoInter roomDaoInter; // getCnt() �޼��带 ����ϱ� ���� ����
	
	private int roomcnt = 0; // �� �Խù� ���� �޾ƿ��� ���� ����
	
	// ��Ʈ�ѷ��� �ִ� �޼��� �� list�� �տ� ������ �޼��忡 ����
	@Before("execution(* kr.co.hotel.manager.controller.*.*Controller.list*(..))")
	public void beforeCount(JoinPoint jp) {
		Object[] args = jp.getArgs(); // ����� �޼����� ���ڰ��� �޾ƿ��� ���� JoinPoint�� ����Ͽ� getArgs()�� �迭�� �޾ƿ´�. 
		Model m = (Model) args[0]; // ����� �޼����� ù��° ���ڰ��� ���� �����Ͽ� �޾ƿ´�.
		roomcnt = roomDaoInter.getCnt();
		m.addAttribute("roomcnt", roomcnt); // JSTL�� �信 ����ϱ� ���� ���� ����ؼ� ���� �����ش�.
		// System.out.println("�� ���� �� : "+roomcnt);
	}
}



