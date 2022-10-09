package kr.co.hotel.manager.controller.reserve;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.hotel.manager.dao.ReserveDaoInter;
import kr.co.hotel.vo.ReserveVO;

@Controller
@RequestMapping(value = "/reserve")
public class ReserveController {
	
	@Autowired
	private ReserveDaoInter reserveDaoInter;
	
	private int nowPage = 1; // 현재 페이지 값
	private int nowBlock = 1; // 현재 블럭
	private int totalRecord = 0; // 총 게시물 수   
	private int numPerPage = 9; // 한 페이지당 보여질 게시물 수
	private int pagePerBlock = 5; // 한 블럭당 보여질 페이지 수
	private int totalPage = 0; // 전체 페이지 수 => totalRecord/numPerPage
	private int totalBlock = 0; // 전체 블럭 수   
	private int beginPerPage = 0; // 각 페이지별 시작 게시물의 index값
	private int endPerPage = 0; // 각 페이지별 마지막 게시물의 index값   
	
	
	@RequestMapping(value = "/adminreservelist")
	public String adminreservelistF(Model m, @RequestParam(defaultValue = "1") int cPage) {
		totalRecord = reserveDaoInter.adminreservecnt();
		//System.out.println(totalRecord);
		totalPage = (int) Math.ceil(totalRecord / (double)numPerPage);
		
		nowPage = cPage;
		
		beginPerPage = (nowPage-1) * numPerPage + 1;
		endPerPage = (beginPerPage-1) + numPerPage;
		
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("begin", beginPerPage);
		map.put("end", endPerPage);		
		
		List<ReserveVO> list = reserveDaoInter.adminreservelist(map);		
		
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
		return "manager/reserve/adminreserveList";
		
	}
}
