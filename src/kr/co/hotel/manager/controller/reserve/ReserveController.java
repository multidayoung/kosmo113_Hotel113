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
	
	private int nowPage = 1; // ���� ������ ��
	private int nowBlock = 1; // ���� ��
	private int totalRecord = 0; // �� �Խù� ��   
	private int numPerPage = 9; // �� �������� ������ �Խù� ��
	private int pagePerBlock = 5; // �� ���� ������ ������ ��
	private int totalPage = 0; // ��ü ������ �� => totalRecord/numPerPage
	private int totalBlock = 0; // ��ü �� ��   
	private int beginPerPage = 0; // �� �������� ���� �Խù��� index��
	private int endPerPage = 0; // �� �������� ������ �Խù��� index��   
	
	
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
