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
		Object obj[] = jp.getArgs(); //메서드의 인자값 들고와 배열에 넣기
		HttpSession session = (HttpSession) obj[0];
		String mid = (String) session.getAttribute("sessionid"); //로그인했을 때 세션에 저장해둔 id를 가져온다.
		int rnum = (int) obj[1]; //객실번호
		ClickVO cvo = new ClickVO();
		int age = 0;
		if (mid != null) { //회원일 경우 
			age = clickDaoInter.getAge(mid);
			System.out.println(mid+"의 age=>"+age);
		}else { //비회원일 경우
			mid = "nologin";
			age = 1;
		}
		cvo.setRnum(rnum); //객실번호
		cvo.setMid(mid); //아이디
		cvo.setAge(age); //연령
		cvo.setCnt(1); //클릭수	
		clickDaoInter.addClick(cvo);
	}
}
