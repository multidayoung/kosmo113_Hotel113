package kr.co.hotel.manager.controller.roomservice;

import java.util.ArrayList;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



import kr.co.hotel.manager.service.RoomServiceService;
import kr.co.hotel.vo.ReserveVO;
import kr.co.hotel.vo.RoomServiceVO;
import kr.co.hotel.vo.WorkList;

//�뼭�� ������ ��Ʈ�ѷ� 
@Controller
@RequestMapping("/roomservice")
public class RoomServiceController {

	//�뼭�� DB�� �����ϱ� ���� ���� �ʵ忡 ����� DI
	@Autowired
	private RoomServiceService roomServiceService;

	//�����ڰ� ���� �����/�Ϸ��/�������� �뼭�� ����Ʈ�� �������� �޼���
	//pageing ó�� advice�� ����ϱ����� �ŰԺ����� ������� Model���� HttpServletRequest���� ����, ��ȯ���� String(view���)������ ����
	//session�� �޾ƿͼ� �����ڸ� �����ִ� Page�̱⿡ DM���� �ش� ���̵��� ������ ������ �信�� ������ ��¿��θ� ����
	@RequestMapping("/list")
	public String roomServiceList(Model m,@RequestParam(value = "cPage",defaultValue = "1") String cPage, HttpServletRequest request,HttpSession session) {
		// �α��΋� ������ �Ӽ��� sessionid���� �����ȿͼ� String������ ĳ����
		String id = (String) session.getAttribute("sessionid");
		// forward ������� view�������� request��ü�� adminVO ��ü�� �Ӽ����� �Ѱ���
		m.addAttribute("admin", roomServiceService.getAdminDetail(id));
		return "manager/roomservice/list";
	}
	
	//�ش� �뼭�񽺸� �󼼺��� ���� �޼���
	//�ش� �޼��� ���� ���� ����� �������� ����Ʈ ����� ���� list�� ���� paging ó�� aop�� ����ϱ� ���� �ŰԺ����� ��ȯ���� ������
	//���񽺳ѹ� �޼��带 list���������� Ŭ����  �޾ƿͼ� db�� �ش� ���񽺳ѹ��� �ش��ϴ� �ڷḦ roomservice��ü�� �޾ƿ�
	//session�� �޾ƿͼ� �����ڸ� �����ִ� Page�̱⿡ DM���� �ش� ���̵��� ������ ������ �信�� ������ ��¿��θ� ����
	@RequestMapping("/detail")
	public String adminList(Model m,@RequestParam(value = "cPage",defaultValue = "1") String cPage, HttpServletRequest request,Integer servicenum,HttpSession session) {
		System.out.println(servicenum);
		RoomServiceVO vo = roomServiceService.getDetail(servicenum);
		// �α��΋� ������ �Ӽ��� sessionid���� �����ȿͼ� String������ ĳ����
		String id = (String) session.getAttribute("sessionid");
		// forward ������� view�������� request��ü�� adminVO ��ü�� �Ӽ����� �Ѱ���
		m.addAttribute("admin", roomServiceService.getAdminDetail(id));
		// forward ������� view�������� request��ü�� detail������ RoomserviceVO ��ü�� �Ӽ������� �Ѱ���
		m.addAttribute("detail", vo);

		return "manager/roomservice/detail";
	}
	
	
	// detailpage���� �ش� �뼭�񽺿� �ο��� ����� ���� �޼��� 
	// detailpage���� ������ �ο��� üũ�ڽ��� �޾ƿ��⿡ �迭�� ��ȯ�ޱ� ���Ͽ� �ŰԺ����� HttpServletRequest�� ������
	// msg Advice�� �ش� �뼭���� ������ �ǳ��ֱ����� RoomServiceVO ��ü��, ���ȣ�� �޾ƿ�
	@PostMapping("/update")
	public ModelAndView addAdminToService(HttpServletRequest request,RoomServiceVO vo,Integer rnum) {
		
		ModelAndView mv = new ModelAndView();
		// üũ�ڽ��� �޾ƿ� ������� id�� sawons��� ������ String �迭�� �޾ƿ�
		String[] sawons = request.getParameterValues("chklist");
		// �뼭���� �Ӽ����� ���ȣ�� ���⿡ reserveVO ��ü�� �����ؼ� ���ȣ���� setter������ RoomServiceVO��ü�� setter
		ReserveVO rvo = new ReserveVO();
		rvo.setRnum(rnum);
		vo.setReserve(rvo);
		// ������ ����� �۾��� �����ϱ����� WorkList ��ü�� ���׸����� ���� list ��ü�� ���� ������ sawonlist��� ������ �ּҰ��� ����
		ArrayList<WorkList> sawonlist = new ArrayList<>();
		//foreach���� ���� �޾ƿ� ����� ���̵�ŭ WorkList ��ü�� ������ setter�� ���� ������ ����(���̵��,���񽺳ѹ�)���� ������ List�� ����
		for(String e : sawons) {
			WorkList a = new WorkList();
			a.setServicenum(vo.getServicenum());
			a.setAid(e);
			sawonlist.add(a);
		}
		//����鿡�� msg�˸��� ���������� ����Ʈ�� ����
	    mv.addObject("sawonlist", sawonlist);

		//����� �޾ƿ��� ���� ��찡 �ƴ϶�� DB�� ������ ���� �ش� list��ü�� ���ڰ����� �Ѱ���
		if(sawonlist.size()!=0)roomServiceService.addWorklist(sawonlist);
		mv.setViewName("redirect:/manager/roomservice/detail?servicenum="+vo.getServicenum());
		return mv;
	}
	
	// detailpage���� �ش� �뼭�񽺿� �ο��� ������ ���� �޼��� 
	// detailpage���� ������ �ο��� üũ�ڽ��� �޾ƿ��⿡ �迭�� ��ȯ�ޱ� ���Ͽ� �ŰԺ����� HttpServletRequest�� ������
	// msg Advice�� �ش� �뼭���� ������ �ǳ��ֱ����� RoomServiceVO ��ü��, ���ȣ�� �޾ƿ�
	@PostMapping("/delupdate")
	public ModelAndView delAdminToService(HttpServletRequest request,RoomServiceVO vo,Integer rnum){
		ModelAndView mv = new ModelAndView();
		// üũ�ڽ��� �޾ƿ� ������� id�� sawons��� ������ String �迭�� �޾ƿ�
		String[] sawons = request.getParameterValues("dellist");
		// �뼭���� �Ӽ����� ���ȣ�� ���⿡ reserveVO ��ü�� �����ؼ� ���ȣ���� setter������ RoomServiceVO��ü�� setter
		ReserveVO rvo = new ReserveVO();
		rvo.setRnum(rnum);
		vo.setReserve(rvo);
		//foreach���� ���� �޾ƿ� ����� ���̵�ŭ WorkList ��ü�� ������ setter�� ���� ������ ����(���̵��,���񽺳ѹ�)���� ������ List�� ����
		ArrayList<WorkList> sawonlist = new ArrayList<>();
		//foreach���� ���� �޾ƿ� ����� ���̵�ŭ WorkList ��ü�� ������ setter�� ���� ������ ����(���̵��,���񽺳ѹ�)���� ������ List�� ����
		for(String e : sawons) {
			WorkList vo2 = new WorkList();
			vo2.setAid(e);
			vo2.setServicenum(vo.getServicenum());
			sawonlist.add(vo2);
		}
		//����鿡�� msg�˸��� ���������� ����Ʈ�� ����
	    mv.addObject("sawonlist", sawonlist);
		
		//����� �޾ƿ��� ���� ��찡 �ƴ϶�� DB�� ������ ���� �ش� list��ü�� ���ڰ����� �Ѱ���
		if(sawonlist.size()!=0)	roomServiceService.delWorklist(sawonlist);
		mv.setViewName("redirect:/manager/roomservice/detail?servicenum="+vo.getServicenum());
		return mv;
	}

}
