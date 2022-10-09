package kr.co.hotel.mvc.controller.reserve;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.velocity.tools.config.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.hotel.mvc.dao.ReserveDaoInter;
import kr.co.hotel.mvc.service.ReserveService;
import kr.co.hotel.vo.CannotReserveParaVO;
import kr.co.hotel.vo.CannotReserveVO;
import kr.co.hotel.vo.ReserveDetailParaVO;
import kr.co.hotel.vo.ReserveListParaVO;
import kr.co.hotel.vo.ReserveVO;
import kr.co.hotel.vo.RoomVO;


@Controller
@RequestMapping(value = "/reserve")
public class ReserveController {
	
	@Autowired
	private ReserveDaoInter reserveDaoInter;
		
	private int nowPage = 1; //현재 페이지 값
	private int nowBlock = 1; //현재 블럭
	private int totalRecord = 0; //총 게시물 수
	private int numPerPage = 10; //한 페이지 당 보여질 게시물 수
	private int pagePerBlock = 5; // 한 블럭 당 보여질 페이지 수
	private int totalPage = 0; //전체 페이지 수 => totalRecord 나누기 numPerPage
	private int totalBlock = 0; //전체 블럭 수
	private int beginPerPage = 0; //각 페이지 별 시작 게시물의 index값
	private int endPerPage = 0; //각 페이지 별 마지막 게시물의 index값	
	
	//회원 예약 확인 시 해당 회원의 게시물 받아오고 페이징 처리
	@RequestMapping(value = "/reservelist")
	public String reservelistF(HttpServletRequest request, Model m, @RequestParam(defaultValue = "1") int cPage) {
		//session의 아이디 획득
		HttpSession session = request.getSession(false);
		String mid = (String) session.getAttribute("sessionid");
		if(mid != null) {
			ReserveListParaVO lvo = new ReserveListParaVO();
			lvo.setMid(mid);
			totalRecord = reserveDaoInter.reservecnt(lvo);
			//System.out.println(totalRecord);
			totalPage = (int) Math.ceil(totalRecord / (double)numPerPage);
			
			nowPage = cPage;
			
			beginPerPage = (nowPage-1) * numPerPage + 1;
			endPerPage = (beginPerPage-1) + numPerPage;
						
			lvo.setBegin(beginPerPage);
			lvo.setEnd(endPerPage);
			
			
			List<ReserveVO> list = reserveDaoInter.reservelist(lvo);
			
			
			m.addAttribute("rlist", list);
			
			int startPage = (int)((nowPage-1)/pagePerBlock)*pagePerBlock+1;
			int endPage = startPage + pagePerBlock -1;
			if(endPage > totalPage) {
				endPage = totalPage;
			}
			
			m.addAttribute("startPage", startPage);
			m.addAttribute("endPage", endPage);
			m.addAttribute("nowPage", nowPage);
			m.addAttribute("pagePerBlock", pagePerBlock);
			m.addAttribute("totalPage", totalPage);
			return "member/reserve/reserveList";
		}else {
			return "redirect:/member/";
		}
	}	
	
	//reserveForm에서 넘어온 정보를 reserve테이블에 insert하고 해당 객실의 rstate를 예약 불가능으로 변경
	@PostMapping(value = "/addreserve")
	public String name(ReserveVO vo) {
		reserveDaoInter.addreserve(vo);		
		return "redirect:/member/";
	}
	
	//객실 정보 상세보기에서 예약하기 버튼을 누르면 정보를 받아서 reserveForm으로 연결
	@RequestMapping(value = "/reservepage")
	public String reservepageF(Model m, int rnum, int rprice) {		
		m.addAttribute("rnum", rnum);
		m.addAttribute("rprice", rprice);
		return "member/reserve/reserveForm2";
	}			
	
	@ResponseBody
	@GetMapping(value = "/cannotreserve")
    public List<CannotReserveVO> cannotreservelist(String today, int rnum) {
        CannotReserveParaVO vo = new CannotReserveParaVO();
        vo.setToday(today);
        vo.setRnum(rnum);
        System.out.println(vo.getToday());
        System.out.println(vo.getRnum());
		List<CannotReserveVO> list = reserveDaoInter.cannotreserve(vo);
		//fullcalender의 event에서 마지막 날짜가 표시되는 시점이 하루 전으로 표시되어서
        //임의로 마지막 날짜에 +1하여 던져줌
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        for(CannotReserveVO e : list) {
            Date end2;
            String end3;
            try {
                end2 = df.parse(e.getEnd());
                cal.setTime(end2);
                cal.add(Calendar.DATE, 1);
                end3 = df.format(cal.getTime());
                e.setEnd(end3);
            } catch (ParseException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        return list;
    }
	
	@GetMapping(value = "/reservecancel")
	public String reservecancel(HttpServletRequest request, Model m, int rvnum) {
		HttpSession session = request.getSession(false);
		String mid = (String) session.getAttribute("sessionid");
		int rvnum2 = rvnum;
		if(mid != null) {
			ReserveDetailParaVO vo = new ReserveDetailParaVO();
			vo.setMid(mid);
			vo.setRvnum(rvnum2);
			ReserveVO vo1 = reserveDaoInter.reservedetail(vo);
			m.addAttribute("dlist", vo1);			
			
			return "member/reserve/reserveCancel";
		}else {
			return "redirect:/member/";
		}		
	}
	
	@RequestMapping(value = "/reservercancelcom")
	public String reservecancelcom(ReserveVO vo) {
		reserveDaoInter.reservecancel(vo);		
		return "redirect:/member/";
	}
	
}