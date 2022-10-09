package kr.co.hotel.mvc.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import kr.co.hotel.mvc.dao.RoomDaoInter;

@Component
@Aspect
public class RoomCountAdvice {
	@Autowired
	private RoomDaoInter roomDaoInter; // getCnt() 메서드를 사용하기 위해 선언
	
	private int roomcnt = 0; // 총 게시물 수를 받아오기 위해 선언
	
	// 컨트롤러에 있는 메서드 중 list를 앞에 포함한 메서드에 적용
	@Before("execution(* kr.co.hotel.mvc.controller.*.*Controller.list*(..))")
	public void beforeCount(JoinPoint jp) {
		Object[] args = jp.getArgs(); // 적용될 메서드의 인자값을 받아오기 위해 JoinPoint를 사용하여 getArgs()를 배열로 받아온다. 
		Model m = (Model) args[0]; // 적용될 메서드의 첫번째 인자값을 모델을 선언하여 받아온다.
		roomcnt = roomDaoInter.getCnt();
		m.addAttribute("roomcnt", roomcnt); // JSTL로 뷰에 출력하기 위해 모델을 사용해서 값을 보내준다.
		// System.out.println("총 객실 수 : "+roomcnt);
	}
}



