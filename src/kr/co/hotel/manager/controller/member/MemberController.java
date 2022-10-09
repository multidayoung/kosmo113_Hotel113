package kr.co.hotel.manager.controller.member;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.co.hotel.manager.dao.MemberDaoInter;
import kr.co.hotel.manager.dao.MsgDaoInter;
import kr.co.hotel.manager.service.RoomServiceService;
import kr.co.hotel.vo.AdminVO;
import kr.co.hotel.vo.MemberVO;
import kr.co.hotel.vo.MsgVO;
import kr.co.hotel.vo.RoomServiceVO;
import kr.co.hotel.vo.SearchVO;
import kr.co.hotel.vo.WorkList;

// ����� �ڽ��� ���� ���¸� üũ�ϰ� �����ϱ� ���� ��Ʈ�ѷ�
@Controller
@RequestMapping(value = "/member")
public class MemberController {
	
	@Autowired
	private MemberDaoInter memberDaoInter;
	@Autowired
	private RoomServiceService roomServiceService;
	@Autowired
	private MsgDaoInter msgDaoInter;
	
	
	// ȸ�� ����Ʈ ��� (AOP�� ����¡ ó��)
	@RequestMapping(value = "/memberlist")
	public String memberList(Model model,String cPage) {	
		return "manager/member/memberList";
	}
	@RequestMapping(value = "/memberDetail")
	public String memberDetail(Model model,int num) {
		MemberVO vo = memberDaoInter.getDetail(num);
		model.addAttribute("vo",vo);
		return "manager/member/memberDetail";
	}
	@GetMapping(value = "/memberUpForm")
    public ModelAndView memberUpForm(@RequestParam("mnum") int num) {
        ModelAndView mav = new ModelAndView();
        MemberVO vo = memberDaoInter.getDetail(num);
        mav.addObject("vo", vo);
        mav.setViewName("manager/member/memberUpdate");
        return mav;
    }
	@PostMapping(value = "/memberUpdate")
	public String memberUpdate(MemberVO vo) {
		memberDaoInter.upMember(vo);
		return "redirect:/manager/member/memberlist";
	}
	@GetMapping(value = "/memberDelete")
	public String memberDelete(int num) {
		memberDaoInter.delMember(num);
		return "redirect:/manager/member/memberlist";
	}	
	
	// �˻� ��� ����Ʈ ��� ( AOP ����¡ ó�� ��� ���� ��)
		@GetMapping(value = "/memberSearch")
		public String memberSearch(Model m,@RequestParam(defaultValue = "1")int cPage,String searchName,String searchType) {
			   int nowPage = 1;//���� ������ ��
			   int nowBlock = 1;//���� ��
			   int totalRecord = 0;//�� �Խù� ��
			   int numPerPage = 10;//�� ������ �� ������ �Խù� ��
			   int pagePerBlock = 10;//�� �� �� ������ ������ ��. 5 �̻��̸� ���� ��ư ����
			   int totalPage = 0; //��ü������ �� => totalRecord/numPerPage
			   int beginPerPage = 0; //�� ������ �� ���� �Խù��� index ��
			   int endPerPage = 0; //�� ������ �� ������ �Խù��� index ��
			   SearchVO vo = new SearchVO();
			   	  vo.setSearchName(searchName);
			      vo.setSearchType(searchType);
			      System.out.println("searchName>>>"+searchName);
			      System.out.println("searchType>>>"+searchType);
			   totalRecord = memberDaoInter.getSearchCnt(vo);
			   System.out.println(totalRecord);
			      System.out.println("totalRecord: "+totalRecord);
			      totalPage = (int) Math.ceil(totalRecord/(double)numPerPage);
			      System.out.println("cPage: "+cPage);
			      if(cPage != 0) {
			         nowPage = cPage;
			      }
			      m.addAttribute("nowPage", nowPage);
			      beginPerPage = (nowPage -1) * numPerPage + 1;
			      endPerPage = (beginPerPage -1) + numPerPage;
			      int startPage = (int) ((nowPage-1)/pagePerBlock)*pagePerBlock+1;
			      m.addAttribute("startPage", startPage);
			      int endPage = startPage + pagePerBlock - 1;
			      System.out.println("endPage1: "+endPage);
			      if(endPage > totalPage){
			         endPage = totalPage;
			      }
			      System.out.println("endPage2: "+endPage);
			      m.addAttribute("endPage", endPage);
			      m.addAttribute("totalPage", totalPage);
			      m.addAttribute("searchType", searchType);
			      
			      System.out.println(searchType);
			      vo.setBegin(beginPerPage);
			      vo.setEnd(endPerPage);
			List<MemberVO> list = memberDaoInter.getSearchList(vo);
			m.addAttribute("list",list);
			m.addAttribute("searchName",searchName);
			return "manager/member/memberSearchList";
		}

	// ����� �ڽ��� ���� �� ������ ���� ���� �޼��� 
	// �ش� ����� ������ �����ϱ⿡ session���� sessionscope�� �Ӽ������� ����� id�� �޾ƿ�
	@RequestMapping("/detail")
	public ModelAndView memberDetail(HttpSession session) {
		System.out.println("test");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("manager/member/detail");
		// �α��΋� ������ �Ӽ��� sessionid���� �����ȿͼ� String������ ĳ���� 
		String id = (String) session.getAttribute("sessionid");
		// �ش� ����� ������ ����� ���� AdminVO ��ü�� DB���� id������ ������ ������
		AdminVO vo = roomServiceService.getAdminDetail(id);
		// �ش� ����� �۾���ϰ� �� �뼭���� ������ �������� ���� �ش� ����� �۾������ foreach������ 
		// ������ WorkList�� �ش��ϴ�  �뼭�� ������ ������
		for(WorkList e : vo.getWorklist()) {
			e.setRoomservice(roomServiceService.getDetail(e.getServicenum()));
		}
		// �ش� ����� �۾���ϰ� �۾���Ͽ� �ش��ϴ� �뼭���� ������ ���� ��ü�� forward ������� view�������� ����� ���� 
		// admin�̶�� �Ӽ� Ű�� request��ü�� ���� �־ ����
		mv.addObject("admin", vo);

		return mv;
	}
	
	// �ش� ����� ��� detailPage���� ���� ������ ó���ϱ� ���� �޼���
	// �ް� ������ �ش� �������� ������ �޾ƿ�
	@PostMapping("/confirm")
	public ModelAndView workConfirmToService(WorkList vo) {
		ModelAndView mv = new ModelAndView();
		System.out.println("servicenum:"+vo.getServicenum());
		mv.setViewName("redirect:/manager/member/detail");
		// ������ ���������� db�� Ȯ�������� �����ϱ� ���� db�� ����
		roomServiceService.workConfirm(vo);
		// msg�˸��� ���� �뼭�� ��ü�� ������ �޾ƿ��� �𵨿� ����
		RoomServiceVO rsvo= roomServiceService.getDetail(vo.getServicenum());
		vo.setRoomservice(rsvo);
		return mv;
	}
	// �ش� ����� ��� detailPage���� ���� �ź� ó���ϱ� ���� �޼���
	// �ް� ������ �ش� �������� ������ �޾ƿ�
	@PostMapping("/deny")
	public ModelAndView workDenyToService(WorkList vo) {
		ModelAndView mv = new ModelAndView();

		mv.setViewName("redirect:/manager/member/detail");
		// ���������� �ź��߱⿡ db��  �����ϱ� ���� ����
		roomServiceService.workDeny(vo);
		System.out.println("test");
		// msg�˸��� ���� �뼭�� ��ü�� ������ �޾ƿ��� �𵨿� ����
		RoomServiceVO rsvo= roomServiceService.getDetail(vo.getServicenum());
		vo.setRoomservice(rsvo);
		return mv;
	}
	// �ش� ����� ��� detailPage���� ���� ��� ó���ϱ� ���� �޼���
	// �ް� ������ �ش� �������� ������ �޾ƿ�
	@PostMapping("/cancel")
	public ModelAndView workCancelToService(WorkList vo) {
		ModelAndView mv = new ModelAndView();
		// ���������� ����߱⿡ db�� �ش������ ������Ʈ�ϱ� ���ѱ���
		mv.setViewName("redirect:/manager/member/detail");
		roomServiceService.workCancel(vo);
		// msg�˸��� ���� �뼭�� ��ü�� ������ �޾ƿ��� �𵨿� ����
		RoomServiceVO rsvo= roomServiceService.getDetail(vo.getServicenum());
		vo.setRoomservice(rsvo);
		return mv;
	}
	// �ش� ����� ��� detailPage���� ���� �Ϸ� ó���ϱ� ���� �޼���
	// �ް� ������ �ش� �������� ������ �޾ƿ�	
	@PostMapping("/complete")
	public ModelAndView workCompleteToService(WorkList vo) {
		ModelAndView mv = new ModelAndView();
		// ������ ���������� �Ϸ��߱⿡ db�� �ش������ ������Ʈ�ϱ� ���ѱ���
		mv.setViewName("redirect:/manager/member/detail");
		roomServiceService.workComplete(vo);
		// msg�˸��� ���� �뼭�� ��ü�� ������ �޾ƿ��� �𵨿� ����
		RoomServiceVO rsvo= roomServiceService.getDetail(vo.getServicenum());
		vo.setRoomservice(rsvo);
		return mv;
	}
	// ����� �ڽſ��� �� msg�� ���� �ְ��ϱ����� �޼��� 
	// �ŰԺ����� session�� �����Ͽ� �ش� session�� id�� �ش��ϴ� ����� msg�� ���
	@RequestMapping("/msgbox")
	public ModelAndView msgBox(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		// �α��΋� ������ �Ӽ��� sessionid���� �����ȿͼ� String������ ĳ���� 
		String id = (String) session.getAttribute("sessionid");
		// �ش� ����� �޼�������� list�� �޾ƿ� �� request��ü�� ���� forward ������� ����
		List<MsgVO> msglist = msgDaoInter.getMsgBox(id);

		mv.addObject("msglist", msglist);
		mv.setViewName("manager/member/msgbox");
		return mv;
	}
	// ����� msgBox���� �� �޼����� �������� �������� �޼���
	// �ش� �޼��� ��ȣ �ްԺ����� �޾ƿ�
	@RequestMapping("/msgdetail")
	public ModelAndView msgBox(int msgnum ) {
		ModelAndView mv = new ModelAndView();
		// �޼��� ������ db�� ���� ��ȣ�� ���� �޾ƿ�
		MsgVO vo = msgDaoInter.getMsgDetail(msgnum);
		// �޼����� ��ü�� �޾ƿ� �� request��ü�� ���� forward ������� ����
		mv.addObject("vo", vo);
		mv.setViewName("manager/member/msgdetail");
		return mv;
	}
}
