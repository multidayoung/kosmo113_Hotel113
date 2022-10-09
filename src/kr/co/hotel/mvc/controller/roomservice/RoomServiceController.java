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

// ����ڰ� �뼭���� ����� ���� ��Ʈ�ѷ�
@Controller
@RequestMapping("/roomservice")
public class RoomServiceController {
	// �뼭�� ���̺� ������ �����ϱ� ���� 
	@Autowired
	private RoomServiceDaoInter roomServiceDaoInter;
	
	//�뼭���� �⺻���� ������ ���� ���� �޼���
	@RequestMapping("/info")
	public ModelAndView roomServiceInfo() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/roomservice/info");
		return mv;
	}
	
	// �뼭�� ����� ���� ���� �������� �޼���
	// �ŰԺ����� �ش��ϴ� �����ȣ�� �޾ƿ�
	@RequestMapping("/form")
	public ModelAndView serviceReserveDate(int rvnum) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/roomservice/form");
		// �ش� ���������� �޾ƿ��� �޼���
		ReserveVO vo= roomServiceDaoInter.getReserve(rvnum);
		System.out.println("rvnum : Test:::"+vo.getRvnum());
		// ��¥�� ����Ʈ�� ����ϱ����� aop�� �̿� 
		// ���� ���۳�¥�� ��������¥�� model�� ����
		mv.addObject("snum", vo.getRvstart());
		mv.addObject("lnum", vo.getRvend());
		mv.addObject("vo", vo);
		
		return mv;
	}
	//�ش� �뼭�񽺸� �̹� �����ߴ��� üũ�ϴ� �޼���
	@RequestMapping("/chk")
	public ModelAndView roomserviceChk(RoomServiceVO vo) {
		ModelAndView m = new ModelAndView();
		m.setViewName("roomservice/chk/chk");
		System.out.println(vo.getRvnum());
		System.out.println(vo.getServicedate());
		//üũ���� ��Ʈ�� �޾ƿ� 
		m.addObject("chk", roomServiceDaoInter.sChk(vo));
		
		return m;
	}
	// �뼭�� ������ ����� roomservice ���̺� �����ϱ� ���� �޼���
	// �ްԺ����� RoomService�� �⺻������ post������� �޾ƿ�
	@RequestMapping(value = "/reserve", method = RequestMethod.POST)
	public ModelAndView reserveService(RoomServiceVO vo) {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("redirect:/member/roomservice/form?rvnum="+vo.getRvnum());
		// �޾ƿ� �뼭�������� Dao�� ���� DB�� insert�ϱ����� ����
		roomServiceDaoInter.reserveService(vo);

		return mv;
	}
}
