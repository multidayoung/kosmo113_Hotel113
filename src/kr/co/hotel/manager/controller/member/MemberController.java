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

// 사원이 자신의 업무 상태를 체크하고 대응하기 위한 컨트롤러
@Controller
@RequestMapping(value = "/member")
public class MemberController {
	
	@Autowired
	private MemberDaoInter memberDaoInter;
	@Autowired
	private RoomServiceService roomServiceService;
	@Autowired
	private MsgDaoInter msgDaoInter;
	
	
	// 회원 리스트 출력 (AOP로 페이징 처리)
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
	
	// 검색 결과 리스트 출력 ( AOP 페이징 처리 사용 안한 것)
		@GetMapping(value = "/memberSearch")
		public String memberSearch(Model m,@RequestParam(defaultValue = "1")int cPage,String searchName,String searchType) {
			   int nowPage = 1;//현재 페이지 값
			   int nowBlock = 1;//현재 블럭
			   int totalRecord = 0;//총 게시물 수
			   int numPerPage = 10;//한 페이지 당 보여질 게시물 수
			   int pagePerBlock = 10;//한 블럭 당 보여질 페이지 수. 5 이상이면 다음 버튼 있음
			   int totalPage = 0; //전체페이지 수 => totalRecord/numPerPage
			   int beginPerPage = 0; //각 페이지 별 시작 게시물의 index 값
			   int endPerPage = 0; //각 페이지 별 마지막 게시물의 index 값
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

	// 사원이 자신의 업무 상세 정보를 보기 위한 메서드 
	// 해당 사원의 정보를 봐야하기에 session으로 sessionscope에 속성값으로 저장된 id를 받아옴
	@RequestMapping("/detail")
	public ModelAndView memberDetail(HttpSession session) {
		System.out.println("test");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("manager/member/detail");
		// 로그인떄 저장한 속성인 sessionid값을 가져옴와서 String값으로 캐스팅 
		String id = (String) session.getAttribute("sessionid");
		// 해당 사원의 상세정보 출력을 위해 AdminVO 객체에 DB에서 id값으로 정보를 가져옴
		AdminVO vo = roomServiceService.getAdminDetail(id);
		// 해당 사원의 작업목록과 그 룸서비스의 정보을 가져오기 위해 해당 사원의 작업목록을 foreach문으로 
		// 각각의 WorkList에 해당하는  룸서비스 정보를 저장함
		for(WorkList e : vo.getWorklist()) {
			e.setRoomservice(roomServiceService.getDetail(e.getServicenum()));
		}
		// 해당 사원의 작업목록과 작업목록에 해당하는 룸서비스의 정보를 넣은 객체를 forward 방식으로 view페이지에 출력을 위해 
		// admin이라는 속성 키로 request객체에 값을 넣어서 보냄
		mv.addObject("admin", vo);

		return mv;
	}
	
	// 해당 사원이 사원 detailPage에서 업무 수락을 처리하기 위한 메서드
	// 메게 변수로 해당 업무만의 정보를 받아옴
	@PostMapping("/confirm")
	public ModelAndView workConfirmToService(WorkList vo) {
		ModelAndView mv = new ModelAndView();
		System.out.println("servicenum:"+vo.getServicenum());
		mv.setViewName("redirect:/manager/member/detail");
		// 수락한 업무정보를 db에 확인했음을 수정하기 위해 db에 저장
		roomServiceService.workConfirm(vo);
		// msg알림을 위해 룸서비스 객체의 정보를 받아온후 모델에 저장
		RoomServiceVO rsvo= roomServiceService.getDetail(vo.getServicenum());
		vo.setRoomservice(rsvo);
		return mv;
	}
	// 해당 사원이 사원 detailPage에서 업무 거부 처리하기 위한 메서드
	// 메게 변수로 해당 업무만의 정보를 받아옴
	@PostMapping("/deny")
	public ModelAndView workDenyToService(WorkList vo) {
		ModelAndView mv = new ModelAndView();

		mv.setViewName("redirect:/manager/member/detail");
		// 업무정보를 거부했기에 db에  제거하기 위한 구문
		roomServiceService.workDeny(vo);
		System.out.println("test");
		// msg알림을 위해 룸서비스 객체의 정보를 받아온후 모델에 저장
		RoomServiceVO rsvo= roomServiceService.getDetail(vo.getServicenum());
		vo.setRoomservice(rsvo);
		return mv;
	}
	// 해당 사원이 사원 detailPage에서 업무 취소 처리하기 위한 메서드
	// 메게 변수로 해당 업무만의 정보를 받아옴
	@PostMapping("/cancel")
	public ModelAndView workCancelToService(WorkList vo) {
		ModelAndView mv = new ModelAndView();
		// 업무정보를 취소했기에 db에 해당업무를 업데이트하기 위한구문
		mv.setViewName("redirect:/manager/member/detail");
		roomServiceService.workCancel(vo);
		// msg알림을 위해 룸서비스 객체의 정보를 받아온후 모델에 저장
		RoomServiceVO rsvo= roomServiceService.getDetail(vo.getServicenum());
		vo.setRoomservice(rsvo);
		return mv;
	}
	// 해당 사원이 사원 detailPage에서 업무 완료 처리하기 위한 메서드
	// 메게 변수로 해당 업무만의 정보를 받아옴	
	@PostMapping("/complete")
	public ModelAndView workCompleteToService(WorkList vo) {
		ModelAndView mv = new ModelAndView();
		// 수락한 업무정보를 완료했기에 db에 해당업무를 업데이트하기 위한구문
		mv.setViewName("redirect:/manager/member/detail");
		roomServiceService.workComplete(vo);
		// msg알림을 위해 룸서비스 객체의 정보를 받아온후 모델에 저장
		RoomServiceVO rsvo= roomServiceService.getDetail(vo.getServicenum());
		vo.setRoomservice(rsvo);
		return mv;
	}
	// 사원이 자신에게 온 msg를 볼수 있게하기윈한 메서드 
	// 매게변수로 session을 지정하여 해당 session의 id에 해당하는 사원의 msg를 출력
	@RequestMapping("/msgbox")
	public ModelAndView msgBox(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		// 로그인떄 저장한 속성인 sessionid값을 가져옴와서 String값으로 캐스팅 
		String id = (String) session.getAttribute("sessionid");
		// 해당 사원의 메세지목록을 list로 받아온 후 request객체에 저장 forward 방식으로 보냄
		List<MsgVO> msglist = msgDaoInter.getMsgBox(id);

		mv.addObject("msglist", msglist);
		mv.setViewName("manager/member/msgbox");
		return mv;
	}
	// 사원이 msgBox에서 본 메세지의 상세정보를 보기위한 메서드
	// 해당 메세지 번호 메게변수로 받아옴
	@RequestMapping("/msgdetail")
	public ModelAndView msgBox(int msgnum ) {
		ModelAndView mv = new ModelAndView();
		// 메세지 내용을 db로 부터 번호를 통해 받아옴
		MsgVO vo = msgDaoInter.getMsgDetail(msgnum);
		// 메세지를 객체로 받아온 후 request객체에 저장 forward 방식으로 보냄
		mv.addObject("vo", vo);
		mv.setViewName("manager/member/msgdetail");
		return mv;
	}
}
