package kr.co.hotel.mvc.controller.room;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.co.hotel.mvc.dao.RoomDaoInter;
import kr.co.hotel.vo.RoomVO;

@Controller
@RequestMapping(value = "/room")
public class RoomController {
	@Autowired
	private RoomDaoInter roomDaoInter;
	
	private int nowPage = 1; // 현재 페이지 값
	private int nowBlock = 1; // 현재 블럭
	private int totalRecord = 0; // 총 게시물 수   
	private int numPerPage = 9; // 한 페이지당 보여질 게시물 수
	private int pagePerBlock = 5; // 한 블럭당 보여질 페이지 수
	private int totalPage = 0; // 전체 페이지 수 => totalRecord/numPerPage
	private int totalBlock = 0; // 전체 블럭 수   
	private int beginPerPage = 0; // 각 페이지별 시작 게시물의 index값
	private int endPerPage = 0; // 각 페이지별 마지막 게시물의 index값   

	// 목록
	@RequestMapping(value = "/listroom")
	public ModelAndView listRoom(Model m, @RequestParam("cPage") int s_page) {
		ModelAndView mav = new ModelAndView();
		
		totalRecord = roomDaoInter.getCnt();
	    totalPage = (int) Math.ceil(totalRecord/(double)numPerPage);
	    totalBlock = (int) Math.ceil((double) totalPage/pagePerBlock);
	    if(s_page != 0){
	    	nowPage = s_page;
	    }

	    beginPerPage = (nowPage - 1) * numPerPage + 1;
	    endPerPage = (beginPerPage-1) + numPerPage;
	    
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("begin", beginPerPage);
		map.put("end", endPerPage);
		List<RoomVO> listroom = roomDaoInter.listRoom(map);
		mav.addObject("listroom", listroom);
		
		int startPage = (int)((nowPage-1)/pagePerBlock)*pagePerBlock+1;
		int endPage = startPage + pagePerBlock - 1;
		if(endPage > totalPage){
			endPage = totalPage;
		}
		
		mav.addObject("startPage", startPage);
		mav.addObject("endPage", endPage);
		mav.addObject("nowPage", nowPage);
		mav.addObject("pagePerBlock", pagePerBlock);
		mav.addObject("totalPage",totalPage);
		mav.setViewName("member/room/listroom");
		return mav;
	}
	
	// 상세
	@GetMapping(value = "/detailroom")
	public ModelAndView detailRoom(HttpSession session, @RequestParam("rnum") int rnum) {
		ModelAndView mav = new ModelAndView();
		RoomVO vo = roomDaoInter.detailRoom(rnum);
		mav.addObject("vo", vo);
		mav.addObject("nowPage", nowPage);
		mav.setViewName("member/room/detailroom");
		return mav;
	}

}
