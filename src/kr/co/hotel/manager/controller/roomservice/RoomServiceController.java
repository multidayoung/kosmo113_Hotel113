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

//룸서비스 관리자 컨트롤러 
@Controller
@RequestMapping("/roomservice")
public class RoomServiceController {

	//룸서비스 DB에 연결하기 위한 서비스 필드에 선언과 DI
	@Autowired
	private RoomServiceService roomServiceService;

	//관리자가 현재 예약된/완료된/진행중인 룸서비스 리스트를 볼기위한 메서드
	//pageing 처리 advice를 사용하기위해 매게변수를 순서대로 Model부터 HttpServletRequest까지 지정, 반환값을 String(view경로)값으로 설정
	//session을 받아와서 관리자만 볼수있는 Page이기에 DM에서 해당 아이디의 정보를 가져와 뷰에서 페이지 출력여부를 결정
	@RequestMapping("/list")
	public String roomServiceList(Model m,@RequestParam(value = "cPage",defaultValue = "1") String cPage, HttpServletRequest request,HttpSession session) {
		// 로그인떄 저장한 속성인 sessionid값을 가져옴와서 String값으로 캐스팅
		String id = (String) session.getAttribute("sessionid");
		// forward 방식으로 view페이지에 request객체에 adminVO 객체를 속성값을 넘겨줌
		m.addAttribute("admin", roomServiceService.getAdminDetail(id));
		return "manager/roomservice/list";
	}
	
	//해당 룸서비스를 상세보기 위한 메서드
	//해당 메서드 또한 현재 사원의 업무상태 리스트 출력을 위해 list와 같이 paging 처리 aop를 사용하기 위해 매게변수와 반환값을 맞춰줌
	//서비스넘버 메서드를 list페이지에서 클릭시  받아와서 db에 해당 서비스넘버에 해당하는 자료를 roomservice객체로 받아옴
	//session을 받아와서 관리자만 볼수있는 Page이기에 DM에서 해당 아이디의 정보를 가져와 뷰에서 페이지 출력여부를 결정
	@RequestMapping("/detail")
	public String adminList(Model m,@RequestParam(value = "cPage",defaultValue = "1") String cPage, HttpServletRequest request,Integer servicenum,HttpSession session) {
		System.out.println(servicenum);
		RoomServiceVO vo = roomServiceService.getDetail(servicenum);
		// 로그인떄 저장한 속성인 sessionid값을 가져옴와서 String값으로 캐스팅
		String id = (String) session.getAttribute("sessionid");
		// forward 방식으로 view페이지에 request객체에 adminVO 객체를 속성값을 넘겨줌
		m.addAttribute("admin", roomServiceService.getAdminDetail(id));
		// forward 방식으로 view페이지에 request객체에 detail값으로 RoomserviceVO 객체를 속성값으로 넘겨줌
		m.addAttribute("detail", vo);

		return "manager/roomservice/detail";
	}
	
	
	// detailpage에서 해당 룸서비스에 인원의 충원을 위한 메서드 
	// detailpage에서 선택한 인원을 체크박스로 받아오기에 배열로 반환받기 위하여 매게변수에 HttpServletRequest를 가져옴
	// msg Advice에 해당 룸서비스의 정보를 건네주기위해 RoomServiceVO 객체와, 방번호를 받아옴
	@PostMapping("/update")
	public ModelAndView addAdminToService(HttpServletRequest request,RoomServiceVO vo,Integer rnum) {
		
		ModelAndView mv = new ModelAndView();
		// 체크박스로 받아온 사원들의 id를 sawons라는 변수에 String 배열로 받아옴
		String[] sawons = request.getParameterValues("chklist");
		// 룸서비스의 속성에는 방번호는 없기에 reserveVO 객체를 생성해서 방번호값을 setter해준후 RoomServiceVO객체에 setter
		ReserveVO rvo = new ReserveVO();
		rvo.setRnum(rnum);
		vo.setReserve(rvo);
		// 각각의 사원의 작업을 저장하기위해 WorkList 객체를 제네릭으로 갖는 list 객체를 힙에 생성후 sawonlist라는 변수명에 주소값을 지정
		ArrayList<WorkList> sawonlist = new ArrayList<>();
		//foreach문을 통해 받아온 사원의 아이디만큼 WorkList 객체를 생성후 setter를 통해 각각의 정보(아이디와,서비스넘버)값을 저장후 List에 저장
		for(String e : sawons) {
			WorkList a = new WorkList();
			a.setServicenum(vo.getServicenum());
			a.setAid(e);
			sawonlist.add(a);
		}
		//사원들에게 msg알림을 보내기위해 리스트를 보냄
	    mv.addObject("sawonlist", sawonlist);

		//사원을 받아오지 않은 경우가 아니라면 DB에 저장을 위해 해당 list객체를 인자값으로 넘겨줌
		if(sawonlist.size()!=0)roomServiceService.addWorklist(sawonlist);
		mv.setViewName("redirect:/manager/roomservice/detail?servicenum="+vo.getServicenum());
		return mv;
	}
	
	// detailpage에서 해당 룸서비스에 인원의 변경을 위한 메서드 
	// detailpage에서 선택한 인원을 체크박스로 받아오기에 배열로 반환받기 위하여 매게변수에 HttpServletRequest를 가져옴
	// msg Advice에 해당 룸서비스의 정보를 건네주기위해 RoomServiceVO 객체와, 방번호를 받아옴
	@PostMapping("/delupdate")
	public ModelAndView delAdminToService(HttpServletRequest request,RoomServiceVO vo,Integer rnum){
		ModelAndView mv = new ModelAndView();
		// 체크박스로 받아온 사원들의 id를 sawons라는 변수에 String 배열로 받아옴
		String[] sawons = request.getParameterValues("dellist");
		// 룸서비스의 속성에는 방번호는 없기에 reserveVO 객체를 생성해서 방번호값을 setter해준후 RoomServiceVO객체에 setter
		ReserveVO rvo = new ReserveVO();
		rvo.setRnum(rnum);
		vo.setReserve(rvo);
		//foreach문을 통해 받아온 사원의 아이디만큼 WorkList 객체를 생성후 setter를 통해 각각의 정보(아이디와,서비스넘버)값을 저장후 List에 저장
		ArrayList<WorkList> sawonlist = new ArrayList<>();
		//foreach문을 통해 받아온 사원의 아이디만큼 WorkList 객체를 생성후 setter를 통해 각각의 정보(아이디와,서비스넘버)값을 저장후 List에 저장
		for(String e : sawons) {
			WorkList vo2 = new WorkList();
			vo2.setAid(e);
			vo2.setServicenum(vo.getServicenum());
			sawonlist.add(vo2);
		}
		//사원들에게 msg알림을 보내기위해 리스트를 보냄
	    mv.addObject("sawonlist", sawonlist);
		
		//사원을 받아오지 않은 경우가 아니라면 DB에 저장을 위해 해당 list객체를 인자값으로 넘겨줌
		if(sawonlist.size()!=0)	roomServiceService.delWorklist(sawonlist);
		mv.setViewName("redirect:/manager/roomservice/detail?servicenum="+vo.getServicenum());
		return mv;
	}

}
