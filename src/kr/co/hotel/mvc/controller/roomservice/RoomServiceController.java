package kr.co.hotel.mvc.controller.roomservice;


import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.co.hotel.mvc.dao.RoomServiceDaoInter;
import kr.co.hotel.vo.ReserveVO;
import kr.co.hotel.vo.RoomServiceVO;

// 사용자가 룸서비스의 등록을 위한 컨트롤러
@Controller
@RequestMapping("/roomservice")
public class RoomServiceController {
	// 룸서비스 테이블에 정보를 저장하기 위해 
	@Autowired
	private RoomServiceDaoInter roomServiceDaoInter;
	
	//룸서비스의 기본적인 정보를 보기 위한 메서드
	@RequestMapping("/info")
	public ModelAndView roomServiceInfo() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/roomservice/info");
		return mv;
	}
	
	// 룸서비스 등록을 위한 폼에 가기위한 메서드
	// 매게변수로 해당하는 예약번호를 받아옴
	@RequestMapping("/form")
	public ModelAndView serviceReserveDate(int rvnum) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/roomservice/form");
		// 해당 예약정보를 받아오는 메서드
		ReserveVO vo= roomServiceDaoInter.getReserve(rvnum);
		System.out.println("rvnum : Test:::"+vo.getRvnum());
		// 날짜를 셀렉트로 출력하기위해 aop를 이용 
		// 예약 시작날짜와 마지막날짜를 model에 저장
		mv.addObject("snum", vo.getRvstart());
		mv.addObject("lnum", vo.getRvend());
		mv.addObject("vo", vo);
		
		return mv;
	}
	//해당 룸서비스를 이미 예약했는지 체크하는 메서드
	@RequestMapping("/chk")
	public ModelAndView roomserviceChk(RoomServiceVO vo) {
		ModelAndView m = new ModelAndView();
		m.setViewName("roomservice/chk/chk");
		System.out.println(vo.getRvnum());
		System.out.println(vo.getServicedate());
		//체크값을 인트로 받아옴 
		m.addObject("chk", roomServiceDaoInter.sChk(vo));
		
		return m;
	}
	// 룸서비스 폼에서 예약시 roomservice 테이블에 저장하기 위한 메서드
	// 메게변수로 RoomService의 기본정보를 post방식으로 받아옴
	@RequestMapping(value = "/reserve", method = RequestMethod.POST)
	public ModelAndView reserveService(RoomServiceVO vo) {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("redirect:/member/roomservice/form?rvnum="+vo.getRvnum());
		// 받아온 룸서비스정보를 Dao를 통해 DB에 insert하기위한 구문
		roomServiceDaoInter.reserveService(vo);

		return mv;
	}
}
