package kr.co.hotel.manager.controller.room;

import java.io.File;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.co.hotel.manager.dao.RoomDaoInter;
import kr.co.hotel.vo.RoomVO;

@Controller
@RequestMapping(value = "/room")
public class RoomController {
	@Autowired
	private RoomDaoInter roomDaoInter;
	
	private int nowPage = 1; // ���� ������ ��
	private int nowBlock = 1; // ���� ��
	private int totalRecord = 0; // �� �Խù� ��   
	private int numPerPage = 9; // �� �������� ������ �Խù� ��
	private int pagePerBlock = 5; // �� ���� ������ ������ ��
	private int totalPage = 0; // ��ü ������ �� => totalRecord/numPerPage
	private int totalBlock = 0; // ��ü �� ��   
	private int beginPerPage = 0; // �� �������� ���� �Խù��� index��
	private int endPerPage = 0; // �� �������� ������ �Խù��� index��   
	
	// �߰�
	@GetMapping(value = "/addroom")
	public String addRoom(int num, Model m) {
		m.addAttribute("num", num);
		return "manager/room/addroom";
	}
	
	// ���� ���ε� ó��
	@PostMapping(value = "/addform")
	public String uploadFile(Model m, RoomVO vo, HttpServletRequest request) {
		String img_path = "resources\\roomimg";
		
		// request�� ������ �̹����� ��θ� �޾Ƽ� ���
		String r_path = request.getRealPath("/");
		System.out.println(r_path);
		// ���ε� �� �̹����� �̸��� �޾Ƽ� �̹����� ����
		String oriFn = vo.getMfile().getOriginalFilename();
		System.out.println(oriFn);
		// �̹����� ���ε� ���� ���� ��� noimage�� �⺻���� ����ȴ�.
		if(oriFn.length() == 0) {
			oriFn = "noimage.jpg";
			vo.setRimg(oriFn);
		} else {
			// �޸𸮻�(�ӽ���¡���)�� ������ �츮�� ������ ��ο� ����
			StringBuffer path = new StringBuffer();
			path.append(r_path).append(img_path).append("\\");
			path.append(oriFn);
			System.out.println(path);
			// �߻��� (�̹����� ������ ���) File ��ü�� ����
			File f = new File(path.toString());
			
			// �ӽ� �޸𸮿� ��� ���ε��� ������ �� -> File Ŭ������ ��η� ����
			try {
				vo.getMfile().transferTo(f);
				// �̹��� �̸��� DB�� �����ϱ� ���ؼ� VO�� ���� �缳��
				vo.setRimg(oriFn);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		roomDaoInter.addRoom(vo);
		
		return "redirect:/manager/room/listroom?cPage=1";
	}
	
	// ���ȣ üũ
	@GetMapping(value="/checkroom")
	public String checkRoom(Model m, @RequestParam("num") int rnum) {
	   int rcheck = roomDaoInter.checkRoom(rnum);
	   m.addAttribute("rcheck", rcheck);
	   return "checkroom";
	}
	
	// ���
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
		mav.setViewName("manager/room/listroom");
		return mav;
	}
	
	// ��
	@GetMapping(value = "/detailroom")
	public ModelAndView detailRoom(int rnum) {
		ModelAndView mav = new ModelAndView();
		RoomVO vo = roomDaoInter.detailRoom(rnum);
		mav.addObject("vo", vo);
		mav.addObject("nowPage", nowPage);
		mav.setViewName("manager/room/detailroom");
		return mav;
	}
	
	// ����
	@GetMapping(value = "/updateroom")
	public ModelAndView updateRoom(int rnum) {
		ModelAndView mav = new ModelAndView();
		RoomVO vo = roomDaoInter.detailRoom(rnum);
		mav.addObject("vo", vo);
		mav.setViewName("manager/room/updateroom");
		return mav;
	}
	
	@PostMapping(value = "/updateform")
	public String modifyRoom(RoomVO vo, HttpServletRequest request, String rimg) {
		String img_path = "resources\\roomimg";
		String r_path = request.getRealPath("/");
		String oriFn = vo.getMfile().getOriginalFilename();
		if(oriFn.length() == 0) {
			oriFn = rimg;
			vo.setRimg(oriFn);
		} else {
			StringBuffer path = new StringBuffer();
			path.append(r_path).append(img_path).append("\\");
			path.append(oriFn);
			File f = new File(path.toString());
			try {
				vo.getMfile().transferTo(f);
				vo.setRimg(oriFn);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		roomDaoInter.updateRoom(vo);
		
		return "redirect:/manager/room/detailroom?rnum="+vo.getRnum();
	}
	
	// ����
	@GetMapping(value = "/deleteroom")
	public String deleteRoom(int rnum) {
		roomDaoInter.deleteRoom(rnum);
		return "redirect:/manager/room/listroom?cPage="+nowPage;
	}
}
