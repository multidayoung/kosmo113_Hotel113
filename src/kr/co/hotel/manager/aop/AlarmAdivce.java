package kr.co.hotel.manager.aop;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import kr.co.hotel.manager.dao.AdminDaoInter;
import kr.co.hotel.manager.dao.MsgDaoInter;
import kr.co.hotel.manager.service.RoomServiceService;
import kr.co.hotel.vo.AdminVO;
import kr.co.hotel.vo.MsgVO;
import kr.co.hotel.vo.RoomServiceVO;
import kr.co.hotel.vo.WorkList;

@Component
@Aspect
public class AlarmAdivce {

	@Autowired
	private MsgDaoInter msgDaoInter;
	@Autowired
	private AdminDaoInter adminDaoInter;

	
	@AfterReturning(pointcut = "execution(* kr.co.hotel.manager.controller.*.*Controller.*ToService*(..))",returning = "mv")
	public ModelAndView sendAlarm(JoinPoint jp, ModelAndView mv) {
		Properties prop=new Properties();
		String path = "D:\\iKosmo113\\spring\\workspace\\semi_second_Cteam_final1\\src\\kr\\co\\hotel\\manager\\aop\\order.properties";
		Object[] obj=jp.getArgs();
		WorkList wvo= null;
		RoomServiceVO rsvo=null;
		List<WorkList> wlist= (List<WorkList>) mv.getModel().get("sawonlist");
		for(Object e: obj) {
			if(e instanceof WorkList) {wvo=(WorkList) e;
			}else if(e instanceof RoomServiceVO ) {
				rsvo=(RoomServiceVO) e;
			}
		}
		

		try {
			prop.load(new FileReader(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String name=jp.getSignature().getName();
		String sub = prop.getProperty(name);
	
		String receiver="";
		String sender="";

		MsgVO vo = new MsgVO();
		vo.setSub(sub);
		if(name.contains("Admin")) {

			sender="admin";
			vo.setSender(sender);
			for(WorkList e: wlist) {
				AdminVO avo = adminDaoInter.getDetail(e.getAid());
				
				receiver=e.getAid();
				vo.setReceiver(receiver);
				vo.setCont(avo.getAjob()+" "+avo.getAname()+"님 "+rsvo.getReserve().getRnum()+"호실에 "+sub+" 이왔습니다. 확인해주세요");
				msgDaoInter.addAlarm(vo);
			}
		}else {
			sender=wvo.getAid();
			rsvo=wvo.getRoomservice();
			vo.setSender(sender);
			vo.setReceiver("admin");
			vo.setCont(sender+"가 "+rsvo.getReserve().getRnum()+"호에 "+sub+"했습니다");
			msgDaoInter.addAlarm(vo);
		}

		
		return  mv;
	}
}
