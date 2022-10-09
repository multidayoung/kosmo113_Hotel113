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
	
	private int nowPage = 1; // 현재 페이지 값
	private int nowBlock = 1; // 현재 블럭
	private int totalRecord = 0; // 총 게시물 수   
	private int numPerPage = 9; // 한 페이지당 보여질 게시물 수
	private int pagePerBlock = 5; // 한 블럭당 보여질 페이지 수
	private int totalPage = 0; // 전체 페이지 수 => totalRecord/numPerPage
	private int totalBlock = 0; // 전체 블럭 수   
	private int beginPerPage = 0; // 각 페이지별 시작 게시물의 index값
	private int endPerPage = 0; // 각 페이지별 마지막 게시물의 index값   
	
	// 추가
	@GetMapping(value = "/addroom")
	public String addRoom(int num, Model m) {
		m.addAttribute("num", num);
		return "manager/room/addroom";
	}
	
	// 파일 업로드 처리
	@PostMapping(value = "/addform")
	public String uploadFile(Model m, RoomVO vo, HttpServletRequest request) {
		String img_path = "resources\\roomimg";
		
		// request를 가지고 이미지의 경로를 받아서 출력
		String r_path = request.getRealPath("/");
		System.out.println(r_path);
		// 업로드 된 이미지의 이름을 받아서 이미지를 복사
		String oriFn = vo.getMfile().getOriginalFilename();
		System.out.println(oriFn);
		// 이미지를 업로드 하지 않을 경우 noimage가 기본으로 저장된다.
		if(oriFn.length() == 0) {
			oriFn = "noimage.jpg";
			vo.setRimg(oriFn);
		} else {
			// 메모리상(임시저징장소)에 파일을 우리가 설정한 경로에 복사
			StringBuffer path = new StringBuffer();
			path.append(r_path).append(img_path).append("\\");
			path.append(oriFn);
			System.out.println(path);
			// 추상경로 (이미지를 저장할 경로) File 객체로 생성
			File f = new File(path.toString());
			
			// 임시 메모리에 담긴 업로드한 파일의 값 -> File 클래스의 경로로 복사
			try {
				vo.getMfile().transferTo(f);
				// 이미지 이름을 DB에 저장하기 위해서 VO에 값을 재설정
				vo.setRimg(oriFn);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		roomDaoInter.addRoom(vo);
		
		return "redirect:/manager/room/listroom?cPage=1";
	}
	
	// 방번호 체크
	@GetMapping(value="/checkroom")
	public String checkRoom(Model m, @RequestParam("num") int rnum) {
	   int rcheck = roomDaoInter.checkRoom(rnum);
	   m.addAttribute("rcheck", rcheck);
	   return "checkroom";
	}
	
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
		mav.setViewName("manager/room/listroom");
		return mav;
	}
	
	// 상세
	@GetMapping(value = "/detailroom")
	public ModelAndView detailRoom(int rnum) {
		ModelAndView mav = new ModelAndView();
		RoomVO vo = roomDaoInter.detailRoom(rnum);
		mav.addObject("vo", vo);
		mav.addObject("nowPage", nowPage);
		mav.setViewName("manager/room/detailroom");
		return mav;
	}
	
	// 수정
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
	
	// 삭제
	@GetMapping(value = "/deleteroom")
	public String deleteRoom(int rnum) {
		roomDaoInter.deleteRoom(rnum);
		return "redirect:/manager/room/listroom?cPage="+nowPage;
	}
}
